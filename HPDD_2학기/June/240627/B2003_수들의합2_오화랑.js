const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);
const numList = input[1].split(' ').map(Number);

let left = 0;
let sum = 0;
let count = 0;
for (let i = 0; i < numList.length; i++) {
  sum += numList[i];
  while (sum > M) {
    sum -= numList[left++];
  }
  if (sum == M) count++;
}
console.log(count);
