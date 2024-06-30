class Stack {
  constructor() {
    this.arr = [];
    this.index = 0;
  }
  push(item) {
    this.arr[this.index++] = item;
  }
  pop() {
    if (this.index < 0) return null;
    const result = this.arr[--this.index];
    return result;
  }
  peek() {
    return this.arr[this.index - 1];
  }
  isEmpty() {
    if (this.index == 0) return true;
    else false;
  }
}

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim();

const result = [];
let target = new Array(input.length);
let visited = new Array(input.length).fill(false);
const indexStack = new Stack();
indexStack.push(0);

while (!indexStack.isEmpty()) {
  let start = indexStack.peek();
  let minWord = '이것은 쓰레기값 입니다';
  let nextIndex = -1;
  for (let i = start; i < input.length; i++) {
    if (visited[i]) continue;
    if (minWord > input[i]) {
      minWord = input[i];
      nextIndex = i;
    }
  }
  if (nextIndex == -1) indexStack.pop();
  else {
    indexStack.push(nextIndex);
    target[nextIndex] = minWord;
    visited[nextIndex] = true;
    result.push(target.join(''));
  }
}

console.log(result.join('\n'));
