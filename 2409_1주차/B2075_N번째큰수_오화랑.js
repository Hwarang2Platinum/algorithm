/**
 * ! readFileSync로 1번에 읽어온다면, Memory 초과의 위험이 있음 => readline으로 읽어내야 함.
 * IMP : N번째 큰 수를 찾으므로, Max Heap에 Insert하고 N번을 pop하면 됨 => Memory 초과
 * IMP : N개의 Heap의 개수 제한을 두어보자
 * * => N번째 큰 수 (A)가 Heap에 남아 있으려면, N개가 넘어갈 때, a보다 작은 수를 빼면 된다.
 * * Min Heap으로 구현하고, size가 N + 1개 넘어갈 때 pop를 해준다.
 * * 전체 수를 Insert하고 나면, a가 최소인 N개의 Heap이 님아있게 됨
 */
class Heap {
  constructor() {
    this.items = [];
  }
  swap(index1, index2) {
    // * Swap함수 for Heap 내 이동
    let temp = this.items[index1];
    this.items[index1] = this.items[index2];
    this.items[index2] = temp;
  }
  parentIndex(index) {
    // * 부모 Index
    return Math.floor((index - 1) / 2);
  }
  leftChildIndex(index) {
    // * 왼쪽 자식 Index
    return index * 2 + 1;
  }
  rightChildIndex(index) {
    // * 오른쪽 자식 Index
    return index * 2 + 2;
  }
  parent(index) {
    // * 부모 Node
    return this.items[this.parentIndex(index)];
  }
  leftChild(index) {
    return this.items[this.leftChildIndex(index)];
  }
  rightChild(index) {
    return this.items[this.rightChildIndex(index)];
  }
  peek() {
    return this.items[0];
  }
  size() {
    return this.items.length;
  }
}

class PriorityQueue extends Heap {
  bubbleUp() {
    // IMP : 부모 (Root 쪽의 Node)가 자식보다 크다면, 그 위치를 바꿔간다.
    // IMP : Root쪽으로 갈수록, 작은 Value가 Root에 위치하기 위함
    let index = this.size() - 1;
    while (this.parent(index) !== undefined && this.parent(index) > this.items[index]) {
      this.swap(index, this.parentIndex(index)); // * 현재 Index와 부모 Index의 Value 교체.
      index = this.parentIndex(index);
      // IMP 현재 Index를 While문을 돌기 위해, 원래 부모의 Index로 교체함.
    }
  }
  bubbleDown() {
    // IMP : 부모 Root의 요소가 Poll()되고, 가장 마지막 원소가 Root로 올라옴으로써 일어나는 정렬과정
    // IMP : heapifyDown 동작이 발생한다.
    let index = 0;
    while (
      this.leftChild(index) !== undefined &&
      (this.leftChild(index) < this.items[index] || this.rightChild(index) < this.items[index])
    ) {
      let smallerIndex = this.leftChildIndex(index);
      if (this.rightChild !== undefined && this.rightChild(index) < this.items[smallerIndex]) {
        smallerIndex = this.rightChildIndex(index);
      }
      this.swap(index, smallerIndex);
      index = smallerIndex;
    }
  }
  offer(item) {
    this.items[this.size()] = item;
    this.bubbleUp();
  }
  poll() {
    // IMP : 마지막 원소를 Root로 옮겨주는 것은 그냥 발생하는 작업
    // IMP : 원소를 옮겼으니, 마지막 원소를 pop()해서 개수를 맞추고 조절 작업에 들어간다.
    let item = this.items[0];
    this.items[0] = this.items[this.size() - 1];
    this.items.pop();
    this.bubbleDown();
    // ! 복사해두었던, 기존의 Root원소를 반납한다.
    return item;
  }
}

/**
 * IMP : readFileSync()와 달리 한 줄씩 읽어낼 수 있는 Code!
 */
const readline = require('readline');
const br = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let n = 0;
let lineCount = -1;
const pq = new PriorityQueue();

/**
 * IMP : 핵심 Point는 N개 제한의 Heap을 두고, 그것보다 많이 들어오게 된다면, 작은 것들 빼내서 등수를 맞춘다.
 * IMP : 'pq.size() > n'으로 제한을 두었기에, N개의 원소만 PQ에 들어오게 되고, 가장 첫번째가 그 등수임.
 */
br.on('line', (line) => {
  if (lineCount === -1) {
    lineCount = parseInt(line);
    n = lineCount;
    return;
  }
  line.split(' ').forEach((value) => {
    pq.offer(parseInt(value));
    if (pq.size() > n) pq.poll();
  });
  lineCount--;
  if (lineCount === 0) br.close();
}).on('close', () => {
  console.log(pq.peek());
  process.exit();
});
