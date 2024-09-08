const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, X] = input[0].split(' ').map(Number);
const visitList = input[1].split(' ').map(Number);
let maxSum = 0;
let currentSum = 0;
let maxCnt = 0;

for (let i = 0; i < X; i++) {
  currentSum += visitList[i];
}
maxSum = currentSum;
maxCnt = 1;

for (let i = X; i < N; i++) {
  currentSum += visitList[i] - visitList[i - X];
  if (currentSum > maxSum) {
    maxSum = currentSum;
    maxCnt = 1;
  } else if (currentSum == maxSum) {
    maxCnt++;
  }
}

if (maxSum == 0) console.log('SAD');
else {
  console.log(maxSum);
  console.log(maxCnt);
}
