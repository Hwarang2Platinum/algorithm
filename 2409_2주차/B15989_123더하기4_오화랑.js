/**
 * * https://www.acmicpc.net/problem/15989
 * IMP : 합을 이루고 있는 수의 순서만 다른 것은 같은 것으로 간주
 * IMP : 특정 수를 1,2,3의 합으로 나타내는 총 가짓수를 구해야 함.
 * IMP : 합을 나타낼 때 수를 1개 이상 사용해야 함
 * 예)
 * * 1 + 1 + 1 + 1, 2 + 1 + 1, 2 + 2, 3 + 1
 * * 1) 1
 * => 1
 * 2)
 * 1 + 1, 2
 * 3)
 * 1 + 1 + 1, 2 + 1, 3
 * 4)
 * 1 + 1 + 1 + 1, 2 + 1 + 1, 3 + 1 => 2 + 2 ( 1 )
 * 1 + 1 + 2, 2 + 2 , 1 + 3
 * 5)
 * 1 + 1 + 1 + 1 + 1, 2 + 1 + 1 + 1, 3 + 1 + 1, 2 + 2 + 1 => (4)
 * 1 + 1 + 1 + 2, 2 + 1 + 2, 3 + 2, => ( 1 )
 * 1 + 1 + 3, 2 + 3
 * 6) (7)
 * 1 + 1 + 1 + 1 + 1 + 1, 2 + 1 + 1 + 1 + 1, 3 + 1 + 1 + 1, 2 + 2 + 1 + 1, 3 + 2 + 1 ( 5 )
 * 1 + 1 + 1 + 1 + 2, 2 + 1 + 1 + 2, 3 + 1 + 2, 2 + 2 + 2 => 2 + 2 + 2 ( 1 )
 * 1 + 1 + 1 + 3, 2 + 1 + 3, 3 + 3 => 3 + 3 ( 1 )
 *
 * 7 ) 3 + 3 +
 * => 6 : 7
 * => 5 : 2 + 2 + 3
 * => 4 :
 */

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n')
  .map(Number);

const N = input[0];
const countList = Array(10001).fill([0, 0, 0]);
countList[1] = [1, 0, 0];
countList[2] = [2, 1, 0];
countList[3] = [3, 0, 1];
countList[4] = [4, 1, 0];

const getCount = (target) => {
  let result = countList[target];
  if (result[0] > 0) return result;

  let first, second, third;
  first = second = third = 0;
  if (result[0] === 0) {
    first = getCount(target - 1)[0];
    second = getCount(target - 2)[1] + getCount(target - 2)[2];
    if (target % 3 === 0) third = getCount(target - 3)[2];
  }
  countList[target] = [first + second + third, second, third];
  return countList[target];
};

for (let i = 1; i <= N; i++) {
  let target = input[i];
  getCount(target);
  console.log(countList[target][0]);
}
