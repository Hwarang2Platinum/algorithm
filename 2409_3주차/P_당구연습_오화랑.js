/**
 * ! 개악질 문제..
 * sX와 sY는 고정되어 있음 => 리스트에 있는 공은, 항상 (sX,sY)에서 침
 * IMP : 공의 진행 방향은 항상 입사각과 반사각이 동일함 / 꼭짓점에 부딪힐 경우, 반대 방향으로 진행
 * IMP : 공은 반드시 한 번 이상, 벽을 치고 Target을 맞춰야 함 ( 공의 속도는 무시한다, Target에 부딪히면 끝 )
 * REQ : (m: 가로, n: 세로) <= 1,000 , balls: [x: a, y: b]의 length <= 1000 ( 0 < x, y < m, n )
 * RES : 공의 List에 따라, Target까지 굴러간 거리의 Min Value의 제곱을 배열에 담아 Return한다.
 */
const getDist = (xDelta, yDelta) => {
  return Math.pow(xDelta, 2) + Math.pow(yDelta, 2);
};

const getRoute = (m, n, start, target) => {
  let [sX, sY] = start;
  let [tX, tY] = target;
  let xDelta, yDelta, minResult;
  minResult = 5000000;

  // get top Cushion
  xDelta = Math.abs(sX - tX);
  yDelta = 2 * n - sY - tY;
  if (xDelta !== 0 || sY > tY) minResult = Math.min(getDist(xDelta, yDelta), minResult);

  // get left Cushion
  xDelta = Math.abs(sY - tY);
  yDelta = sX + tX;
  if (xDelta !== 0 || sX < tX) minResult = Math.min(getDist(xDelta, yDelta), minResult);

  // get bottom Cushion
  xDelta = Math.abs(sX - tX);
  yDelta = sY + tY;
  if (xDelta !== 0 || sY < tY) minResult = Math.min(getDist(xDelta, yDelta), minResult);

  // get right Cushion
  xDelta = Math.abs(sY - tY);
  yDelta = 2 * m - sX - tX;
  if (xDelta !== 0 || sX > tX) minResult = Math.min(getDist(xDelta, yDelta), minResult);

  return minResult;
};

function solution(m, n, startX, startY, balls) {
  var answer = [];
  for (let i = 0; i < balls.length; i++) {
    let target = balls[i];
    let eachResult = getRoute(m, n, [startX, startY], [target[0], target[1]]);
    answer.push(eachResult);
  }
  return answer;
}
