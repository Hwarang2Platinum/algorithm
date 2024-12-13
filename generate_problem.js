import pool from './database.js';
import dotenv from 'dotenv';
import axios from 'axios';

dotenv.config();

const TYPES = JSON.parse(process.env.TYPES);
const SOLVED_AC_PROBLEM_URL = 'https://solved.ac/api/v3/problem';

/**
 * IMP : 문제의 Tag를 랜덤으로 가져오는 함수
 * @returns
 */
const getRandomTag = () => {
  console.log(TYPES);
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
  const [rows] = await pool.query(query, [problemId]);
  return rows.length > 0;
};

/**
 * IMP : 추천한 문제를 History에 추가하는 함수
 * @param {*} problem
 */
export const addProblemToHistory = async (problem) => {
  const query = `INSERT INTO history (problem_id, title, level, types, date) VALUES (?, ?, ?, ?, ?)`;
  await pool.query(query, [
    problem.problemId,
    problem.problemTitle,
    problem.problemLevel,
    problem.problemType,
    problem.date,
  ]);
};

/**
 * IMP : Daily Algorithm 문제를 추천하는 함수
 * @param {*} type
 * @returns
 */
export const fetchProblemsFromSolvedAc = async () => {
  try {
    let type = getRandomTag();
    console.log(type);
    const response = await axios.get(SOLVED_AC_PROBLEM_URL, {
      params: {
        query: `tier:B1..P5 tag:${type} solved:>=100`,
        sort: 'random',
      },
    });
    return response.data.items;
  } catch (error) {
    console.error(`Error fetching problems with tag "${type}":`, error.message);
    return [];
  }
};
