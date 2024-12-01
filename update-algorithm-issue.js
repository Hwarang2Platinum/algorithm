import { Octokit } from '@octokit/rest';
import axios from 'axios';
import dotenv from 'dotenv';

dotenv.config();

const GIT_TOKEN = process.env.GIT_TOKEN;
const SOLVED_AC_BASE_URL = 'https://solved.ac/api/v3/problem/show';
const SOURCE_OWNER = 'Hwarang-Oh';
const SOURCE_REPO = 'baekjoon';
const SOURCE_PATH = 'main/picked.md';
const TARGET_ORG = 'Hwarang2Platinum';
const TARGET_REPO = 'algorithm';

const octokit = new Octokit({ auth: GIT_TOKEN });

const LEVELS = [
  'Unrated',
  'Bronze V',
  'Bronze IV',
  'Bronze III',
  'Bronze II',
  'Bronze I',
  'Silver V',
  'Silver IV',
  'Silver III',
  'Silver II',
  'Silver I',
  'Gold V',
  'Gold IV',
  'Gold III',
  'Gold II',
  'Gold I',
  'Platinum V',
  'Platinum IV',
  'Platinum III',
  'Platinum II',
  'Platinum I',
  'Diamond V',
  'Diamond IV',
  'Diamond III',
  'Diamond II',
  'Diamond I',
  'Ruby V',
  'Ruby IV',
  'Ruby III',
  'Ruby II',
  'Ruby I',
];

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
  const ids = [...markdown.matchAll(regex)].map((match) => match[1]);
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
  # <a href="https://www.acmicpc.net/problem/${problemId}" target="_blank">백준링크</a>
  
  ## Solver
  - [ ] 김경호 @kkho9654
  - [ ] 김인엽 @wintiger98
  - [ ] 김종호 @dino9881
  - [ ] 김정규 @bladerunner3201
  - [ ] 송인범 @InbumS
  - [ ] 오화랑 @Hwarang-Oh
  - [ ] 이찬민 @chanmin97
  - [ ] 정현석 @Aiden-Jung
  - [ ] 조승기 @seungki-cho

  ### Hint 1 : 난이도
  <details>
  <summary>난이도 보기</summary>
  ${problemLevel}
  </details>

  ### Hint 2 : 문제 유형

  <details>
  <summary>유형 보기</summary>
  ${problemType}
  </details>

  ### Hint 3 : 풀이법 ( ChatGPT 4o )

  <details>
  <summary>풀이법 보기</summary>
  구현 예정
  </details>
  `;

  const data = await octokit.issues.create({
    owner: TARGET_ORG,
    repo: TARGET_REPO,
    title: issueTitle,
    body: issueBody,
    labels: issueLabels,
  });
  console.log(`Issue created: ${data.data.html_url}`);
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
