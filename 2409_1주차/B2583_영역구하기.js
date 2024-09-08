class Queue {
  constructor() {
    this.items = [];
  }
  offer(value) {
    this.items.push(value);
  }
  poll() {
    return this.items.shift();
  }
  peek() {
    return this.items[0];
  }
  isEmpty() {
    return this.items.length === 0;
  }
  size() {
    return this.items.length;
  }
}

class PriorityQueue {
  constructor() {
    this.items = [];
  }
  offer(value) {
    this.items.push(value);
    this.items.sort((a, b) => a - b);
  }
  poll() {
    return this.items.shift();
  }
  peek() {
    return this.items[0];
  }
  isEmpty() {
    return this.items.length === 0;
  }
  size() {
    return this.items.length;
  }
}

class Pair {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }
}

const target = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

let [N, M, K] = target[0].split(' ').map(Number);
let map = Array.from({ length: M }, () => Array(N).fill(0));
let isChecked = Array.from({ length: M }, () => Array(N).fill(false));
const pq = new PriorityQueue();
const search = [
  [0, -1],
  [-1, 0],
  [0, 1],
  [1, 0],
];

const colorArea = ({ r1, c1, r2, c2 }) => {
  for (let i = r1; i < r2; i++) {
    for (let j = c1; j < c2; j++) {
      map[i][j] = 1;
      isChecked[i][j] = true;
    }
  }
};

const BFS = ({ sX, sY }) => {
  let count = 0;
  eachQueue = new Queue();
  isChecked[sX][sY] = true;
  eachQueue.offer(new Pair(sX, sY));
  let temp, nX, nY;
  while (!eachQueue.isEmpty()) {
    temp = eachQueue.poll();
    count++;
    search.forEach((eachSearch) => {
      nX = temp.x + eachSearch[0];
      nY = temp.y + eachSearch[1];
      if (nX < 0 || nY < 0 || nX >= M || nY >= N) return;
      if (map[nX][nY] === 0 && !isChecked[nX][nY]) {
        isChecked[nX][nY] = true;
        eachQueue.offer(new Pair(nX, nY));
      }
    });
  }
  return count;
};

/**
 * Main Code
 */
for (let i = 1; i <= K; i++) {
  let [r1, c1, r2, c2] = target[i].split(' ').map(Number);
  let eachRect = { r1, c1, r2, c2 };
  colorArea(eachRect);
}

for (let i = 0; i < M; i++) {
  for (let j = 0; j < N; j++) {
    if (map[i][j] === 0 && !isChecked[i][j]) {
      let eachSize = BFS({ sX: i, sY: j });
      pq.offer(eachSize);
    }
  }
}

let size = pq.size();
let result = [];
while (!pq.isEmpty()) {
  result.push(pq.poll());
}
console.log(size);
console.log(result.join(' '));
