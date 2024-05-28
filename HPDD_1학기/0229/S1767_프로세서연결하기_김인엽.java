import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 110,308 kb 1,213 ms
public class S1767_프로세서연결하기_김인엽 {
  final static int CORE = 1;
  final static int LINE = 2;

  static int maxCoreCnt = 0; // 연결한 코어 개수
  static int minLineSum = Integer.MAX_VALUE; // 최소 전선 길이 합
  static int N; // 보드 크기
  static List<Pair> cores; // 코어 모음집

  // 방향
  static int[] dx = {0,0,-1,1};
  static int[] dy = {-1,1,0,0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for(int test_case = 1; test_case <= T; test_case++) {
      N = Integer.parseInt(br.readLine());

      int[][] arr = new int[N][N]; // 보드

      cores = new ArrayList<>();

      // 보드 채우기
      StringTokenizer st;
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          int tmp = Integer.parseInt(st.nextToken());
          arr[i][j] = tmp;
          // 코어 추가
          if(tmp == CORE) {
            // 만약 테두리에 있다면 넣지 않기
            if(i == 0 || i == N-1 || j == 0 || j == N-1) {
              // 최소 개수 채워주기
              maxCoreCnt++;
            } else {
              cores.add(new Pair(i, j));
            }
          }
        }
      }

      dfs(arr, 0, maxCoreCnt, 0);

      System.out.println("#"+test_case + " " + minLineSum);
    }
  }
  /**
   * 가능한 모든 부분집합에 대해 확인하는 dfs함수
   * @param arr : 보드
   * @param depth : 현재 깊이. cores.size()와 같아지면 return
   * @param cnt : 연결한 코어 개수
   * @param sum : 전선 길이 합
   */
  public static void dfs(int[][] arr, int depth, int coreCnt, int lineSum) {
    // 종료조건
    // 만약 다 탐색했다면,
    if(depth == cores.size()) {
      // 코어 개수가 더 많다면 교체
      if(coreCnt > maxCoreCnt) {
        maxCoreCnt = coreCnt;
        minLineSum = lineSum;
      }
      // 같은데 전선 길이 합이 더 작다면 교체
      else if(coreCnt == maxCoreCnt && minLineSum > lineSum) {
        minLineSum = lineSum;
      }
      return;
    }

    // 현재 코어 선택할 때
    // 갈수있는 방향 알아내기
    boolean[] powerOnDirs = getPowerOnDirs(arr, cores.get(depth));
    // 갈수 있는 방향에 대해서 모두 탐색하기
    for(int i=0; i<powerOnDirs.length; i++) {
      if(!powerOnDirs[i]) continue;
      ArrLineSum als = makeNewArr(arr, cores.get(depth), i);
      dfs(als.arr, depth+1, coreCnt+1, lineSum + als.lineSum);
    }

    // 현재 코어 선택 안 할 때
    dfs(arr, depth + 1, coreCnt, lineSum);
  }
  /**
   * 주어진 방향따라 새 arr만들기
   * @param arr : 보드
   * @param pair : 코어 위치
   * @param dirIdx : 주어진 방향
   * @return ArrLineSum(새 arr: int[][], lineSum: int)
   */
  public static ArrLineSum makeNewArr(int[][] arr, Pair pair, int dirIdx) {
    ArrLineSum result = new ArrLineSum();
    result.arr = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        result.arr[i][j] = arr[i][j]; // 복사하기
      }
    }
    // 선 만들기
    int x = pair.x;
    int y = pair.y;
    // 초기화
    result.lineSum = 0;
    // 선 만들고, 합계도 구해주기
    while(true) {
      x += dx[dirIdx];
      y += dy[dirIdx];
      if(x < 0 || x >= N || y < 0 || y >= N) break;
      result.lineSum++; // 합계 더해주기
      result.arr[x][y] = LINE; // 선 그려주기
    }

    return result;
  }

  /**
   * 전원을 킬 수 있는 방향 배열(boolean으로) 얻기
   * @param arr : 보드
   * @param pair : core 좌표
   * @return 연결 가능한 경우 true / 불가능한 경우 false인 채로 4가지 방향에 대한 boolean 값 배열 반환
   */
  public static boolean[] getPowerOnDirs(int[][] arr, Pair pair) {
    boolean[] result = new boolean[dx.length]; // dx, dy의 인덱스별 가능한 방향

    for(int i=0; i<result.length; i++) {
      // 만약 해당 방향으로 갔을 때 전원 연결이 된다면 true
      if(isValidDir(arr, pair, i)) result[i] = true;
    }

    return result;
  }

  /**
   * 해당 방향으로 갔을 때 전원을 연결할 수 있는지 여부
   * @param arr : 보드
   * @param pair : 코어 좌표
   * @param dirIdx : 방향 인덱스
   * @return 연결가능시 true / 연결 불가능시 false
   */
  public static boolean isValidDir(int[][] arr, Pair pair, int dirIdx) {
    int x = pair.x;
    int y = pair.y;
    while(true) {
      x += dx[dirIdx];
      y += dy[dirIdx];
      // 테두리 넘으면 전원 연결 가능
      if(x < 0 || x >= N || y < 0 || y >= N) return true;
      // 코어가 있거나 선이 있다면 return false
      if(arr[x][y] > 0) return false;

    }
  }

  static class Pair {
    int x, y;

    public Pair(int x, int y) {
      super();
      this.x = x;
      this.y = y;
    }

  }

  static class ArrLineSum {
    int[][] arr;
    int lineSum;
  }
}

