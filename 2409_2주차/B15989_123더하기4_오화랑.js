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
const countList = Array(10001).fill(0);
countList[1] = 1;
countList[2] = 2;
countList[3] = 3;
countList[4] = 4;

const getCount = (target) => {
  let totalCount = countList[target];
  if (totalCount === 0) {
    totalCount += getCount(target - 1) + 1;
    if (target % 2 == 0) totalCount++;
    else if (target % 3 === 0) totalCount++;
  }
  countList[target] = totalCount;
  return totalCount;
};

for (let i = 1; i <= N; i++) {
  let target = input[i];
  getCount(target);
  console.log(countList[target]);
}
