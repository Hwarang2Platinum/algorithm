import dotenv from 'dotenv';
import { getProblemHint } from './generate_hint.js';
import { Octokit } from '@octokit/rest';

dotenv.config();

const GIT_TOKEN = process.env.GIT_TOKEN;
const TARGET_ORG = process.env.TARGET_ORG;
const TARGET_REPO = process.env.TARGET_REPO;
const PROJECT_ID = process.env.PROJECT_ID;
const PROJECT_FIELD_ID = process.env.PROJECT_FIELD_ID;
const octokit = new Octokit({ auth: GIT_TOKEN });

export const createIssue = async (problem) => {
  const { problemId, problemTitle, problemLevel, problemType, date } = problem;
  let issueDate = date.toISOString().split('T')[0].replace(/-/g, '');
  const issueTitle = `[${issueDate}] BOJ ${problemId} ${problemTitle}`;

  let issueLabels = ['백준', 'Daily Update', 'Secret'];
  if (problemLevel === 'Unrated') {
    issueLabels.push('Unrated');
  } else if (problemLevel.startsWith('Bronze') || problemLevel.startsWith('Silver')) {
    issueLabels.push('Basic');
  } else if (problemLevel.startsWith('Gold')) {
    issueLabels.push('Problem');
  } else if (['Platinum', 'Diamond', 'Ruby'].some((level) => problemLevel.startsWith(level))) {
    issueLabels.push('Challenge');
  }

  // IMP : GPT 4.0을 사용하여 힌트를 생성함
  const problemHint = await getProblemHint(problemId, problemTitle, problemType);

  const issueBody = `
  # <a href="https://www.acmicpc.net/problem/${problemId}" target="_blank">📝 백준링크</a>
  
  ## 📌 **Solver**  
  - [ ] 김경호 @kkho9654
  - [ ] 김인엽 @wintiger98
  - [ ] 김종호 @dino9881
  - [ ] 김정규 @bladerunner3201
  - [ ] 송인범 @InbumS
  - [ ] 오화랑 @Hwarang-Oh
  - [ ] 이찬민 @chanmin97
  - [ ] 정현석 @Aiden-Jung
  - [ ] 조승기 @seungki-cho

  ### 📊 **Hint 1 : 난이도**  
  <details>
  <summary>난이도 보기</summary>
  ${problemLevel}
  </details>

  ### 📚 **Hint 2 : 문제 유형**

  <details>
  <summary>유형 보기</summary>
  ${problemType}
  </details>

  ### 🧩 **Hint 3 : 풀이법 ( ChatGPT 4.0 )**  

  <details>
  <summary>풀이법 보기</summary>
  ${problemHint}
  </details>
  `;

  const { data: issue } = await octokit.issues.create({
    owner: TARGET_ORG,
    repo: TARGET_REPO,
    title: issueTitle,
    body: issueBody,
    labels: issueLabels,
    assignees: [
      'kkho9654',
      'wintiger98',
      'dino9881',
      'bladerunner3201',
      'InbumS',
      'Hwarang-Oh',
      'chanmin97',
      'seungki-cho',
    ],
  });
  console.log(`Issue created: ${issue.html_url}`);
  return issue;
};

const assignToProject = async (Issue) => {
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
        issueId: Issue.node_id,
      }
    );
    const itemId = addToProjectResponse.addProjectV2ItemById.item.id;
    console.log(`Issue assigned to project with item ID: ${itemId}`);
    return itemId;
  } catch (error) {
    console.error('Error assigning issue to project:', error.message);
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

export const assignToProjectAndSetStatus = async (Issue, statusOptionId) => {
  try {
    const itemId = await assignToProject(Issue);
    await updateProjectItemStatus(itemId, statusOptionId);
    console.log(`Issue assigned to project and status set to option ID: ${statusOptionId}.`);
  } catch (error) {
    console.error('Error in assigning to project and setting status:', error);
  }
};
