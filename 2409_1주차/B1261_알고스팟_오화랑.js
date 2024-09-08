/**
 * IMP : 모든 벽을 부수면서 갈 수 있는데, 부수는 개수를 최소화해야 함.
 * * visited Array : 해당 칸에 몇번의 부수기를 통해 왔는가?
 * * 부수기 횟수가 적은 순으로 탐색한다면, 탐색된 칸이 visited가 된 상태라면 더 탐색할 이유가 없어짐
 * IMP => 'visited[x, y] !== -1' 이고 'miro[nX][nY] === 0' 이라면 부수는 횟수가 늘지 않고 탐색 가능
 * IMP => 이러한 이유로, Queue의 앞 부분에 넣어준다. ( unshift() )
 * IMP => 'visited[x, y] !== -1' 이고 'miro[nX][nY] === 1' 이라면 부숫는 횟수가 늘면서 탐색해야함.
 * IMP => 이러한 이유로, Queue의 뒷 부분에 넣어줘야 한다. ( push() )
 * ! 결국, Case를 나눠서 자연스럽게 PriorityQueue의 성격을 지니게 할 수 있음
 * ! 의문 : JavaScript의 Array는 이러한 unshift(), shift() 연산에 대한 최적화가 되어 있는가?
 */
const input = require('fs').readFileSync('./input.txt').toString().trim().split('\n');
let [N, M] = input[0].split(' ').map(Number);
let miro = input.slice(1).map((line) => line.trim().split('').map(Number));
let visited = Array.from({ length: M }, () => Array(N).fill(-1));
const move = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

const checkRouteBFS = (sX, sY) => {
  let queue = [];
  queue.push([sX, sY]);
  visited[sX][sY] = 0;

  while (queue.length !== 0) {
    let [cX, cY] = queue.shift(); // shift() -> 앞 부분에서 빼기
    for (const [dx, dy] of move) {
      const [nX, nY] = [cX + dx, cY + dy];
      if (nX < 0 || nY < 0 || nX >= M || nY >= N) continue;
      if (visited[nX][nY] !== -1) continue;
      if (miro[nX][nY] === 0) {
        visited[nX][nY] = visited[cX][cY];
        queue.unshift([nX, nY]); // unshift() -> 앞 부분에 넣기
      } else {
        visited[nX][nY] = visited[cX][cY] + 1;
        queue.push([nX, nY]); // push() -> 뒷 부분에 넣기
      }
    }
  }
};

checkRouteBFS(0, 0);
console.log(visited[M - 1][N - 1]);
