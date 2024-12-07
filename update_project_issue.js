import dotenv from 'dotenv';
import github from '@actions/github';
import { Octokit } from '@octokit/rest';

dotenv.config();

const GIT_TOKEN = process.env.GIT_TOKEN;
const TARGET_ORG = process.env.TARGET_ORG;
const TARGET_REPO = process.env.TARGET_REPO;
const PROJECT_ID = process.env.PROJECT_ID;
const PROJECT_FIELD_ID = process.env.PROJECT_FIELD_ID;
const STATUS_DOING = process.env.STATUS_DOING;
const STATUS_DELETED = process.env.STATUS_DELETED;
const STATUS_PROBLEMS = process.env.STATUS_PROBLEMS;
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
    owner: TARGET_ORG,
    repo: TARGET_REPO,
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
      owner: TARGET_ORG,
      repo: TARGET_REPO,
      state: 'open',
      assignee: solver.assignee,
    });

    const subIssue = issues.find((issue) =>
      issue.title.includes(`[Sub-Issue] Solve: ${parentIssue.title}`)
    );

    if (subIssue) {
      await updateProjectItemStatus(subIssue, STATUS_DELETED);
      await octokit.issues.update({
        owner: TARGET_ORG,
        repo: TARGET_REPO,
        issue_number: subIssue.number,
        state: 'closed',
      });
      console.log(`Sub-Issue closed: ${subIssue.html_url}`);
    } else console.log(`No sub-issue found for ${solver.assignee}`);
  } catch (error) {
    console.error('Error closing sub-issue:', error.message);
  }
};

const assignToProject = async (subIssue) => {
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
    console.log(`Sub-Issue assigned to project with item ID: ${itemId}`);
    return itemId;
  } catch (error) {
    console.error('Error assigning sub-issue to project:', error.message);
  }
};

const updateProjectItemStatus = async (itemId, statusOptionId) => {
  try {
    await octokit.graphql(
      `
      mutation($projectId: ID!, $itemId: ID!, $fieldId: ID!, $optionId: String!) {
        updateProjectV2ItemFieldValue(
          input: {
            projectId: $projectId,
            itemId: $itemId,
            fieldId: $fieldId,
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
    console.log(`Project item status updated to option ID: ${statusOptionId}`);
  } catch (error) {
    console.error('Error updating project item status:', error.message);
  }
};

const assignToProjectAndSetStatus = async (subIssue, statusOptionId) => {
  try {
    const itemId = await assignToProject(subIssue);
    await updateProjectItemStatus(itemId, statusOptionId);
    console.log(`Sub-Issue assigned to project and status set to option ID: ${statusOptionId}.`);
  } catch (error) {
    console.error('Error in assigning to project and setting status:', error);
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
  } else if (context.payload.action === 'opened')
    await assignToProjectAndSetStatus(issue, STATUS_PROBLEMS);
};

main().catch((error) => console.error('Error:', error.message));
