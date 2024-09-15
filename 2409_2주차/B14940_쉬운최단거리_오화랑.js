/**
 * * 지도가 주어지면, 모든 지점에서 목표지점까지의 거리를 구하기
 * IMP : 오직 가로와 세로로 움직일 수 있음
 * IMP : 0 -> Can't go, 1 -> Can go , 2 -> Have to Go
 * * N 과 M ( 세로, 가로 ) => 2 <= N, M <= 1000
 * * 원래 갈 수 없는 땅의 위치는 0 표시
 * * 도달 불가 지역 -> -1 표시
 * * 2번에 모든 지점에 대한 거리를 구하면 되는 문제!
 */

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');
const bfs = (sX, sY) => {
  const queue = [];
  queue.push({ x: sX, y: sY, dist: 0 });
  distMap[sX][sY] = 0;
  visited[sX][sY] = true;

  while (queue.length > 0) {
    let current = queue.shift();
    distMap[current.x][current.y] = current.dist;
    for (const eachMove of move) {
      let nX = current.x + eachMove[0];
      let nY = current.y + eachMove[1];
      if (nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
      if (visited[nX][nY] || map[nX][nY] === 0) continue;
      visited[nX][nY] = true;
      queue.push({ x: nX, y: nY, dist: current.dist + 1 });
    }
  }
  print();
};

const print = () => {
  for (let i = 0; i < N; i++) {
    console.log(distMap[i].join(' '));
  }
};

const [N, M] = input[0].split(' ').map(Number);
let sX, sY;
const map = Array.from({ length: N }, () => Array(M).fill(0));
const distMap = Array.from({ length: N }, () => Array(M).fill(-1));
const visited = Array.from({ length: N }, () => Array(M).fill(false));
const move = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

for (let i = 1; i <= N; i++) {
  for (let j = 0; j < M; j++) {
    map[i - 1][j] = parseInt(input[i].split(' ')[j]);
    if (map[i - 1][j] === 2) {
      sX = i - 1;
      sY = j;
    }
    if (map[i - 1][j] === 0) distMap[i - 1][j] = 0;
  }
}

bfs(sX, sY);
