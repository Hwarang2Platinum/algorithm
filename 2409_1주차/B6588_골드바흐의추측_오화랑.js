/**
 * * 골드바흐의 추측 => 4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다.
 * * 8 = 3 + 5, 20 = 3 + 17, 42 = 5 + 37 = 11 + 31 = 19 + 23
 * * 100만 이하의 모든 짝수에 대해, 해당 추측을 검증하라
 * * TC의 개수는 100,000를 넘지 않음, 마지막 입력에 대해서는 0이 주어짐
 * * n을 표현하는 두 소수의 합의 형태가 여러개라면, 가장 b - a의 값이 큰 것을 출력함
 * * 만약에 해당하는 두 소수의 합이 형태가 없다면, "Goldbach's conjecture is wrong." 출력
 */

/**
 * IMP : TestCase가 많기 때문에, 미리 소수를 판별하고 가야 함. => 홀수의 합을 구하고, 거기서 소수 판단은 효율 X
 * IMP : 소수를 판단하는 방법을 최적화를 하지 않으면 O(N) => 그나마 하면 O(root(N))
 * IMP => 하지만 O(root(N))으로 해도, 1,000,000 까지의 모든 소수를 찾기에는 무리가 있음
 * IMP => 이를 해결하기 위한 Method -> 에라토스테네스의 체
 * ! 물론 에라토스테네스의 체는 각 소수 여부에 대한 판별에는 큰 효율을 보이지 못한다.
 */
// const primeList2 = isPrime.map((prime, i) => (prime && i >= 2 ? i : null)).filter(n => n !== null);
const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n')
  .map(Number);
const maxN = 1_000_000;
const result = new Array();

const primeSet = new Set();
const isPrime = new Array(maxN).fill(true);
for (let i = 2; i < maxN; i++) {
  if (isPrime[i]) {
    let times = 2;
    while (i * times < maxN) {
      isPrime[i * times] = false;
      times++;
    }
  }
}
isPrime.forEach((prime, i) => {
  if (prime && i >= 3) {
    primeSet.add(i);
  }
});

const getCombination = (target) => {
  for (const eachPrime of primeSet) {
    if (eachPrime > target / 2) {
      result.push("Goldbach's conjecture is wrong.");
      break;
    } else {
      let leftNum = target - eachPrime;
      if (primeSet.has(leftNum)) {
        result.push(`${target} = ${eachPrime} + ${leftNum}`);
        break;
      }
    }
  }
};

input.forEach((target) => {
  if (target === 0) return;
  getCombination(target);
});

console.log(result.join('\n'));
