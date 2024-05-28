import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 	60788	360
public class B16234_인구이동_김인엽 {
  static int N, L, R; // 나라 개수: NxN, 기준1, 기준2
  static int[][] arr; // 나라 인구수들
  static int[] dx = {1,-1,0,0}; // 방향
  static int[] dy = {0,0,1,-1};
  static int[][] visited; // 방문배열
  static int visitNum; // 방문배열에 넣을 숫자
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    // 배열 초기화
    arr = new int[N][N];

    // 배열 채우기
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0;
    // 이동 가능한지 체크
    while (isValid()) {
      unite(); // 합치기
//      System.out.println("arr: ");
//      for (int[] a : arr) {
//        System.out.println(Arrays.toString(a));
//      }
//      System.out.println();
      answer++;
    }

    System.out.println(answer);
  }

  /**
   * visited 배열 보고 합치기
   */
  private static void unite() {
    int[] sum = new int[visitNum]; // 합계 모아놓은 배열(index: 해당 visitNum) 크기가 visitNum 인 이유는 마지막에 +1을 해두기 떄문 -> 즉 마지막 숫자는 안씀
    int[] cnt = new int[visitNum]; // 개수 모아놓은 배열(index: 해당 visitNum)
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        sum[visited[i][j]] += arr[i][j];
        cnt[visited[i][j]]++;
      }
    }

    // 각 visitNum별 평균 구하기
    for(int i=1; i<visitNum; i++) {
      sum[i] /= cnt[i];
    }

    // 평균값 채워넣기
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        arr[i][j] = sum[visited[i][j]];
      }
    }
  }

  /** made by java doc
   * 모든 국가를 돌며 인구 이동이 가능한지 체크
   * @return 인구 이동 가능시 true, 불가능시 false
   */
  public static boolean isValid() {
    visited = new int[N][N]; // 국가별 방문 여부 배열
    visitNum = 1; // 방문 dfs 호출 횟수 & visited 배열에 채울 숫자
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(visited[i][j] > 0) continue; // 방문했으면 패스
        dfs(i, j, visited, visitNum++); // 돌면서, 갈수있는곳은 visitNum으로 채우기(dfs 호출 횟수 세는데에도 사용)
      }
    }

//    System.out.println("visited: ");
//    for (int[] v : visited) {
//      System.out.println(Arrays.toString(v));
//    }
//    System.out.println();

    return (visitNum - 1) != N * N; // 만약 dfs 호출 횟수가 국가 개수와 같다면, 연합X -> 이동X -> false
  }

  /**
   * dfs 함수 : 국가를 돌면서, visited 배열을 visitNum으로 채우기
   *
   * @param x : x좌표
   * @param y : y좌표
   * @param visited : 방문 배열
   * @param visitNum : 방문 배열에 채워질 visitNum
   */
  public static void dfs(int x, int y, int[][] visited, int visitNum) {
    visited[x][y] = visitNum; // 방문처리

    for(int i=0; i<dx.length; i++) {
      int cx = x + dx[i];
      int cy = y + dy[i];
      // 범위를 벗어나면 패스
      if(cx < 0 || cx >= N || cy < 0 || cy >= N) continue;
      // 방문했으면 패스
      if(visited[cx][cy] > 0) continue;
      // 오픈가능하면 dfs 수행
      if(isOpen(x, y, cx, cy)) {
        dfs(cx, cy, visited, visitNum);
      }
    }
  }

  /**
   * 인구 차이가 L과 R 사이인지 확인
   * @param x1 : 국가1의 x좌표
   * @param y1 : 국가1의 y좌표
   * @param x2 : 국가2의 x좌표
   * @param y2 : 국가2의 y좌표
   * @return 국경개방이 가능하면 true, 불가능하면 false 반환
   */
  public static boolean isOpen(int x1, int y1, int x2, int y2) {
    int diff = Math.abs(arr[x1][y1] - arr[x2][y2]);
    return diff >= L && diff <= R;
  }
}