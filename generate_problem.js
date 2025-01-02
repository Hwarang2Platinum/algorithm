import pool from './database.js';
import dotenv from 'dotenv';
import axios from 'axios';

dotenv.config();

const TYPES = JSON.parse(process.env.TYPES);
const SOLVED_AC_PROBLEM_URL = 'https://solved.ac/api/v3/search/problem';

/**
 * IMP : 문제의 Tag를 랜덤으로 가져오는 함수
 * @returns
 */
const getRandomTag = () => {
  const randomIndex = Math.floor(Math.random() * TYPES.length);
  return TYPES[randomIndex];
};

/**
 * IMP : Fetch한 문제가 이미 추천된 문제인지 확인하는 함수
 * @param {*} problemId
 * @returns
 */
export const isProblemAlreadyRecommended = async (problemId) => {
  const query = `SELECT 1 FROM history WHERE problem_id = ?`;
  try {
    const [rows] = await pool.query(query, [problemId]);
    console.log(`Query executed: ${query} with problem_id: ${problemId}`);
    return rows.length > 0;
  } catch (error) {
    console.error(`Error executing query: ${query} with problem_id: ${problemId}`, error.message);
    throw error;
  }
};

/**
 * IMP : Fetch한 문제가 Korean & AcceptedUserCount가 100명 이상인지 확인하는 함수
 * TODO : Korean 문제인 지 확인하는 로직 추가 ( 현재는 AcceptedUserCount만 확인 )
 */
export const isProblemValid = (problem) => {
  const acceptedUserCount = problem.acceptedUserCount;
  return acceptedUserCount >= 100;
};

/**
 * IMP : 추천한 문제를 History에 추가하는 함수
 * @param {*} problem
 */
export const addProblemToHistory = async (problem) => {
  const query = `INSERT INTO history (problem_id, title, level, types, date) VALUES (?, ?, ?, ?, ?)`;
  try {
    await pool.query(query, [
      problem.problemId,
      problem.problemTitle,
      problem.problemLevel,
      problem.problemType,
      problem.date,
    ]);
    console.log(`Problem added to history: ${JSON.stringify(problem)}`);
  } catch (error) {
    console.error(
      `Error executing query: ${query} with problem: ${JSON.stringify(problem)}`,
      error.message
    );
    throw error;
  }
};

/**
 * IMP : Daily Algorithm 문제를 추천하는 함수
 * ! 2025.01.02 => 풀어낸 사람 1000명 이상의 문제만 추천하는 것으로 변경
 * @param {*} type
 * @returns
 */
export const fetchProblemsFromSolvedAc = async () => {
  let type = getRandomTag();
  try {
    const response = await axios.get(SOLVED_AC_PROBLEM_URL, {
      params: {
        query: `tier:B1..P5%20tag:${type}%20solved:>=1000`,
        sort: 'random',
      },
    });
    return response.data.items;
  } catch (error) {
    console.error(`Error fetching problems with tag "${type}":`, error.message);
    return [];
  }
};
