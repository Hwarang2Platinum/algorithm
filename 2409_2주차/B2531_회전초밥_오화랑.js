/**
 * * 백준 회전초밥
 * * https://www.acmicpc.net/problem/2531
 * * 1. 초밥의 종류를 번호로 표시한다 -> In 회전 초밥 벨트
 * IMP : 회전 초밥은, 벨트의 임의의 한 위치부터 K개의 접시를 연속해서 먹을 경우 -> 할인 가격
 * IMP : 고객에게 초밥의 종류가 하나 쓰인 쿠폰 발행 -> 1번 행사에 참가 시, 이 쿠폰에 적힌 초밥 하나 무료 제공 ( 반드시 제공 )
 * ! 가장 많은 종류의 초밥을 먹을 수 있는 방법을 찾기
 * * 예) K = 4 , 30번 초밥 쿠폰
 * * 7 9 7 30 2 7 9 25 7( 처음 )
 * * => 가장 많은 종류의 초밥 가짓수를 먹으려면... 2 7 9 25를 먹고 1번 행사에 참가해서 30번 초밥까지 먹어야 함
 * * Point : 가장 많이 먹을 수 있는 가짓수는 K + 1개 ( 어떤 경우든 )
 * * Point : 무료 Event가 있어도, 굳이 쓰지 않을 이유가 생길 수도 있을까?
 * REQ
 * * 1. 초밥 벨트에 놓인 접시의 수 : 2 <= N <= 30,000
 * * 2. 초밥의 전체 가짓수 : 2 <= d <= 3000
 * * 3. 연속해서 먹는 접시의 수 : 2 <= K <= 3000 ( K <= N )
 * * 4. 쿠폰 번호 : 1 <= C <= d
 * RES
 * * 1. Window Sliding이 생각나긴 한다. => 30,000에 끝나도 큰 무리 없는 방법
 * * => 입력의 방식이 1번에 List로 오는 것이 아닌, 하나씩 들어옴! => 이것을 처리하는 방법은 Queue 정도 있을 듯
 * ! Problem : 이것은 Circle 형태임 -> 이에 대한 고려를 해줘야 함 ( 이러면 초밥 종류를 제공한 이유를 알 수 있음 )
 *
 */
const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, D, K, C] = input[0].split(' ').map(Number);
let chovyCheck = Array(D + 1).fill(0);
let chovyList = [];
let chovyCount = 0;
let maxChovyCount = 0;

for (let i = 1; i <= N; i++) {
  let chovy = parseInt(input[i]);
  if (chovyCheck[chovy] === 0) chovyCount++;
  chovyCheck[chovy]++;
  chovyList.push(chovy);

  if (chovyList.length > K) {
    let target = chovyList[chovyList.length - K - 1];
    chovyCheck[target]--;
    if (chovyCheck[target] === 0) chovyCount--;
  }
  if (chovyCheck[C] === 0) maxChovyCount = Math.max(chovyCount + 1, maxChovyCount);
  else maxChovyCount = Math.max(chovyCount, maxChovyCount);
}
if (chovyList.length > K) {
  for (let i = 0; i < K - 1; i++) {
    let duplicateChovy = chovyList[i];
    if (chovyCheck[duplicateChovy] === 0) chovyCount++;
    chovyCheck[duplicateChovy]++;
    chovyList.push(duplicateChovy);

    if (chovyList.length > K) {
      let target = chovyList[chovyList.length - K - 1];
      chovyCheck[target]--;
      if (chovyCheck[target] === 0) chovyCount--;
      if (chovyCheck[C] === 0) maxChovyCount = Math.max(chovyCount + 1, maxChovyCount);
      else maxChovyCount = Math.max(chovyCount, maxChovyCount);
    }
  }
}

console.log(maxChovyCount);
