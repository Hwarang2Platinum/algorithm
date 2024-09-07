/**
 * * 부등호 기호 '<'와 '>'가 K개 나열된 순서열 A
 * IMP => 부등호 기호 앞 뒤에 서로 다른 한 자릿수 숫자를 넣어 부등호 관계 만족
 * * < < < > < < > < >
 * IMP 3 < 4 < 5 < 6 > 1 < 2 < 8 > 7 < 9 > 0 => 3456128790
 * IMP 5 < 6 < 8 < 9 > 0 < 2 < 3 > 1 < 7 > 4 => 5689023174
 * ! K개의 부등호 순서를 만족하는 K + 1 자리의 정수 중에서 최대값과 최소값을 찾아야 함.
 * ! { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9} => 선택된 숫자는 모두 달라야 함.
 *
 * * 최소가 되려면... 최대가 되려면...
 * * => BackTracking이 가장 괜찮은 방법인 것 같음.
 */

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? 'dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const rule = input[1].split(' ');
let eachResult = [];
let isUsed = new Array(10).fill(false);
let isMade = false;

/**
 * 이 함수는 왜 console.log(eachResult)를 실행해도, return해내지 않는것일까?
 * @param {*} step
 * @returns
 */
const getMaxResult = (step) => {
  if (isMade) return;
  if (step > N) {
    console.log(eachResult.join(''));
    isMade = true;
    return;
  }
  for (let i = 9; i >= 0; i--) {
    if (isUsed[i]) continue;
    if (step === 0) {
      isUsed[i] = true;
      eachResult[step] = i;
      getMaxResult(step + 1);
      isUsed[i] = false;
    }
    if (rule[step - 1] === '<' && i > eachResult[step - 1]) {
      isUsed[i] = true;
      eachResult[step] = i;
      getMaxResult(step + 1);
      isUsed[i] = false;
    }
    if (rule[step - 1] === '>' && i < eachResult[step - 1]) {
      isUsed[i] = true;
      eachResult[step] = i;
      getMaxResult(step + 1);
      isUsed[i] = false;
    }
  }
};

const getMinResult = (step) => {
  if (isMade) return;
  if (step > N) {
    console.log(eachResult.join(''));
    isMade = true;
    return;
  }
  for (let i = 0; i <= 9; i++) {
    if (isUsed[i]) continue;
    if (step === 0) {
      isUsed[i] = true;
      eachResult[step] = i;
      getMinResult(step + 1);
      isUsed[i] = false;
    }
    if (rule[step - 1] === '<' && i > eachResult[step - 1]) {
      isUsed[i] = true;
      eachResult[step] = i;
      getMinResult(step + 1);
      isUsed[i] = false;
    }
    if (rule[step - 1] === '>' && i < eachResult[step - 1]) {
      isUsed[i] = true;
      eachResult[step] = i;
      getMinResult(step + 1);
      isUsed[i] = false;
    }
  }
};

getMaxResult(0);
eachResult = [];
isUsed = new Array(10).fill(false);
isMade = false;
getMinResult(0);
