import dotenv from 'dotenv';
import pool from './database.js';
import { createIssue, assignToProjectAndSetStatus } from './update_project_issue.js';
import {
  isProblemAlreadyRecommended,
  isProblemValid,
  addProblemToHistory,
  fetchProblemsFromSolvedAc,
} from './generate_problem.js';

dotenv.config();

const LEVELS = JSON.parse(process.env.LEVELS);
const STATUS_PROBLEMS = process.env.STATUS_PROBLEMS;

/**
 * IMP : Main Function
 * * 1. 문제를 추천한다.
 * * 2. 추천된 문제의 중복 여부를 확인한다.
 * * 3. 중복되지 않은 문제를 History에 추가한다.
 * * 4. Issue를 생성한다.
 * * 5. Issue를 Project에 할당하고 상태 "Problems"로 설정한다.
 * * 6. 성공적으로 처리된 문제를 로그로 출력한다.
 */
const main = async () => {
  try {
    let selectedProblems = 0;

    while (selectedProblems < 1) {
      const problems = await fetchProblemsFromSolvedAc();

      for (const problem of problems) {
        // IMP : 중복된 문제인지 확인
        const isDuplicate = await isProblemAlreadyRecommended(problem.problemId);
        if (isDuplicate) {
          console.log(`Problem ${problem.problemId} is already recommended.`);
          continue;
        }

        // IMP : 문제가 유효한지 확인
        const isValid = isProblemValid(problem);
        if (!isValid) {
          console.log(`Problem ${problem.problemId} is not valid.`);
          continue;
        }

        const types = problem.tags
          .map((tag) => {
            const koDisplayName = tag.displayNames.find(
              (displayName) => displayName.language === 'ko'
            );
            return koDisplayName ? koDisplayName.name : 'Secret';
          })
          .join(', ');

        const newProblem = {
          problemId: problem.problemId,
          problemTitle: problem.titleKo,
          problemLevel: LEVELS[problem.level],
          problemType: types,
          date: new Date(),
        };
        console.log('newProblem: ', newProblem);
        await addProblemToHistory(newProblem);

        const issue = await createIssue(newProblem);
        await assignToProjectAndSetStatus(issue, STATUS_PROBLEMS);
        console.log(`Problem ${problem.problemId} processed successfully.`);
        selectedProblems++;
        break;
      }
    }
  } catch (error) {
    console.error('Error in main workflow:', error.message);
  } finally {
    console.log('Closing database connection pool...');
    await pool.end();
    console.log('Database connection pool closed.');
    process.exit(0);
  }
};

main();
