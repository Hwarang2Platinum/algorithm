import dotenv from 'dotenv';
import github from '@actions/github';
import { Octokit } from '@octokit/rest';

dotenv.config();

const GIT_TOKEN = process.env.GIT_TOKEN;
const PROJECT_ID = process.env.PROJECT_ID;
const PROJECT_FIELD_ID = process.env.PROJECT_FIELD_ID;
const STATUS_DOING = process.env.STATUS_DOING;
const STATUS_DELETED = process.env.STATUS_DELETED;
const octokit = new Octokit({ auth: GIT_TOKEN });

/**
 * IMP : Main Issue CheckBox를 확인합니다
 * @param {*} target
 * @returns
 */
const parseCheckBoxes = (target) => {
  const regex = /- \[([ xX])\] (.+) @([a-zA-Z0-9-_]+)/g;
  const matches = [...target.matchAll(regex)];
  return matches.map((match) => ({
    checked: match[1].toLowerCase() === 'x',
    name: match[2].trim(),
    assignee: match[3].trim(),
  }));
};

/**
 * IMP : Main Issue CheckBox가 "변경"되었는지 확인합니다
 * @param {*} oldBody
 * @param {*} newBody
 * @returns
 */
const watchChangedCheckbox = (oldBody, newBody) => {
  const oldCheckboxes = parseCheckBoxes(oldBody);
  const newCheckboxes = parseCheckBoxes(newBody);
  const changes = [];
  for (let i = 0; i < newCheckboxes.length; i++) {
    if (newCheckboxes[i].checked !== oldCheckboxes[i].checked) changes.push(newCheckboxes[i]);
  }
  return changes;
};

/**
 * IMP : Sub Issue를 생성합니다.
 * @param {*} parentIssue
 * @param {*} solver
 * @returns
 */
const createSubIssue = async (parentIssue, solver) => {
  const issueTitle = `[Sub-Issue] Solve: ${parentIssue.title} (${solver.name})`;

  const issueBody = `
  ## Parent Issue
  [#${parentIssue.number}](${parentIssue.html_url})

  ## Solver
  Assigned to: @${solver.assignee}

  ## Task
  - [ ] Solve the problem
  - [ ] Create a pull request
  `;

  const { data } = await octokit.issues.create({
    owner: parentIssue.repository.owner.login,
    repo: parentIssue.repository.name,
    title: issueTitle,
    body: issueBody,
    assignees: [solver.assignee],
    labels: ['Solve'],
  });

  console.log(`Sub-Issue created: ${data.html_url}`);
  return data;
};

const closeSubIssue = async (parentIssue, solver) => {
  try {
    const { data: issues } = await octokit.issues.listForRepo({
      owner: parentIssue.repository.owner.login,
      repo: parentIssue.repository.name,
      state: 'open',
      creator: solver.assignee,
    });

    const subIssue = issues.find((issue) =>
      issue.title.includes(`[Sub-Issue] Solve: ${parentIssue.title}`)
    );

    if (subIssue) {
      await assignToProjectAndSetStatus(subIssue, STATUS_DELETED);
      await octokit.issues.update({
        owner: parentIssue.repository.owner.login,
        repo: parentIssue.repository.name,
        issue_number: subIssue.number,
        state: 'closed',
      });

      console.log(`Sub-Issue closed: ${subIssue.html_url}`);
    } else console.log(`No sub-issue found for ${solver.assignee}`);
  } catch (error) {
    console.error('Error closing sub-issue:', error.message);
  }
};

const assignToProjectAndSetStatus = async (subIssue, statusOptionId) => {
  try {
    const addToProjectResponse = await octokit.graphql(
      `
      mutation($projectId: ID!, $issueId: ID!) {
        addProjectV2ItemById(input: { projectId: $projectId, contentId: $issueId }) {
          item {
            id
          }
        }
      }
    `,
      {
        projectId: PROJECT_ID,
        issueId: subIssue.node_id,
      }
    );

    const itemId = addToProjectResponse.addProjectV2ItemById.item.id;

    await octokit.graphql(
      `
      mutation($projectId: ID!, $itemId: ID!, $fieldId: ID!, $optionId: String!) {
        updateProjectV2ItemFieldValue(
          input: {
            projectId: $projectId
            itemId: $itemId
            fieldId: $fieldId
            value: { singleSelectOptionId: $optionId }
          }
        ) {
          projectV2Item {
            id
          }
        }
      }
    `,
      {
        itemId,
        projectId: PROJECT_ID,
        optionId: statusOptionId,
        fieldId: PROJECT_FIELD_ID,
      }
    );

    console.log(`Sub-Issue assigned to project and status set to Doing.`);
  } catch (error) {
    console.error('Error assigning sub-issue to project:', error.message);
  }
};

const main = async () => {
  const { context } = github;
  const issue = context.payload.issue;
  if (context.payload.action === 'edited' && context.payload.changes?.body) {
    const oldBody = context.payload.changes.body.from;
    const newBody = issue.body;

    const changedCheckboxes = watchChangedCheckbox(oldBody, newBody);

    for (const checkbox of changedCheckboxes) {
      if (checkbox.checked) {
        const subIssue = await createSubIssue(issue, checkbox);
        await assignToProjectAndSetStatus(subIssue, STATUS_DOING);
      } else {
        await closeSubIssue(issue, checkbox);
      }
    }
  } else if (context.payload.action === 'opened') {
    const checkboxes = parseCheckBoxes(issue.body);
    for (const checkbox of checkboxes) {
      if (checkbox.checked) {
        const subIssue = await createSubIssue(issue, checkbox);
        await assignToProjectAndSetStatus(subIssue, STATUS_DOING);
      }
    }
  }
};

main().catch((error) => console.error('Error:', error.message));
