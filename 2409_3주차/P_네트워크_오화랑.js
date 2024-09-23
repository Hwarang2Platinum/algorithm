/**
 * 직간접적으로 연결되어 있는 모든 네트워크의 개수를 구해야 함.
 * IMP : A - B / B - C 라면 A - C는 연결되어 있는 상태
 * REQ : n -> 컴퓨터의 개수 (1 <= n <= 200), 연결에 대한 2차원 배열 [[1,0, ...], [1, 0, ...], ] ( computer[i][i] 는 항상 1 )
 * REQ : 컴퓨터는 0번 ~ n - 1의 정수로 표현함.
 * RES : 네트워크의 개수 Return
 * ! : JavaScript의 forOf와 forIn의 차이로 인하여, 많이 털렸다. => 정확한 분리와 이해가 중요하다.
 * IMP : 조금 더 고민해서, Union Find로도 풀 가치가 있는 문제라고 생각함.
 */

const getNetwork = (queue, visited, connection) => {
  while (queue.length > 0) {
    let curr = queue.shift();

    for (const next of connection[curr]) {
      if (visited[next]) continue;
      queue.push(next);
      visited[next] = true;
    }
  }
};

function solution(n, computers) {
  var answer = 0;
  let visited = Array(n).fill(false);
  let connection = [];
  let queue = [];
  let visitCount = 0;

  for (let i = 0; i < n; i++) {
    let eachConnection = [];
    for (let j = 0; j < n; j++) if (computers[i][j] === 1) eachConnection.push(j);
    connection.push(eachConnection);
  }

  for (let i = 0; i < n; i++) {
    if (!visited[i]) {
      queue.push(i);
      getNetwork(queue, visited, connection);
      answer++;
    }
  }
  return answer;
}
