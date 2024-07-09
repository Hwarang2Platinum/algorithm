// Problem
// 어느 시간대에 도서관 열람실 좌석이 널널한지?
// 지난주 일요일에 언제 도서관의 열람실을 이용했는지 댓글을 담
// 시각 0 ~ 1_000_000
// 댓글을 달아준 학생 수 N ( 1 ~ 100_000)
// N개의 줄 : 좌석 배정 시각 S 종료 시각 E ( 1 S E 1_000_000 )
// 특정한 시각의 개수 Q ( 1 ~ 100_000 )
// 입력이 들어오는 대로, 그대로 색칠하듯이 값을 갱신해주면, 10만개 * 10만개의 연산이 되어버림.
// 더 효율적인 방식이 필요한 상태

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const query = input[N + 2].split(' ').map(Number);
const seatCount = new Array(1_000_002).fill(0);
const result = [];

for (let i = 1; i <= N; i++) {
  let [start, end] = input[i].split(' ').map(Number);
  seatCount[start]++;
  seatCount[end + 1]--;
}
for (let t = 1; t <= 1_000_000; t++) seatCount[t] += seatCount[t - 1];

for (let eachQuery of query) result.push(seatCount[eachQuery]);
console.log(result.join('\n'));
