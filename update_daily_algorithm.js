import { Octokit } from '@octokit/rest';
import axios from 'axios';
import dotenv from 'dotenv';

dotenv.config();

const GIT_TOKEN = process.env.GIT_TOKEN;
const PROJECT_ID = process.env.PROJECT_ID;
const SOURCE_OWNER = process.env.SOURCE_OWNER;
const SOURCE_REPO = process.env.SOURCE_REPO;
const SOURCE_PATH = process.env.SOURCE_PATH;
const TARGET_ORG = process.env.TARGET_ORG;
const TARGET_REPO = process.env.TARGET_REPO;
const LEVELS = JSON.parse(process.env.LEVELS);
const SOLVED_AC_BASE_URL = 'https://solved.ac/api/v3/problem/show';

const octokit = new Octokit({ auth: GIT_TOKEN });

const fetchPickedTodayAlgorithm = async () => {
  const { data } = await octokit.repos.getContent({
    owner: SOURCE_OWNER,
    repo: SOURCE_REPO,
    path: SOURCE_PATH,
  });
  return Buffer.from(data.content, 'base64').toString('utf-8');
};

const extractProblemId = (pickedProblems) => {
  const regex = /\[([0-9]+)\]\(https:\/\/www\.acmicpc\.net\/problem\/\1\)/g;
  const ids = [...pickedProblems.matchAll(regex)].map((match) => match[1]);
  return ids;
};

const fetchProblemDetails = async (problemId) => {
  try {
    const { data } = await axios.get(SOLVED_AC_BASE_URL, { params: { problemId: problemId } });

    const difficulty = LEVELS[data.level] || 'Unrated';
    const title = data.titleKo || '제목 없음';
    const types = data.tags.map((tag) => {
      const koDisplayName = tag.displayNames.find((displayName) => displayName.language === 'ko');
      return koDisplayName ? koDisplayName.name : 'Secret';
    });

    return { difficulty, title, types };
  } catch (error) {
    console.error(`Error fetching details for problem ${problemId}:`, error.message);
    return { difficulty: 'Unrated', title: '제목 없음', tags: ['Secret'] };
  }
};

const createIssue = async (problemId, problemTitle, problemLevel, problemType) => {
  const date = new Date().toISOString().split('T')[0].replace(/-/g, '');

  const issueTitle = `[${date}] BOJ ${problemId} ${problemTitle}`;

  let issueLabels = ['백준', 'Daily Update', 'Secret'];
  if (problemLevel === 'Unrated') {
    issueLabels.push('Unratedc');
  } else if (problemLevel.startsWith('Bronze') || problemLevel.startsWith('Silver')) {
    issueLabels.push('Basic');
  } else if (problemLevel.startsWith('Gold')) {
    issueLabels.push('Problem');
  } else if (['Platinum', 'Diamond', 'Ruby'].some((level) => problemLevel.startsWith(level))) {
    issueLabels.push('Challenge');
  }

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
  구현 예정
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
      'Aiden-Jung',
      'seungki-cho',
    ],
  });
  console.log(`Issue created: ${issue.html_url}`);

  try {
    await octokit.graphql(
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
        issueId: issue.node_id,
      }
    );
    console.log(`Issue added to project: ${PROJECT_ID}`);
  } catch (error) {
    console.error(`Error adding issue to project: ${PROJECT_ID}`, error.message);
  }
};

const main = async () => {
  try {
    const pickedProblems = await fetchPickedTodayAlgorithm();

    const problemIds = extractProblemId(pickedProblems);

    for (const problemId of problemIds) {
      const { difficulty, title, types } = await fetchProblemDetails(problemId);
      await createIssue(problemId, title, difficulty, types.join(', '));
    }
  } catch (error) {
    console.error('Error:', error.message);
  }
};

main();
