import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 	13044	92
public class B17471_게리맨더링_김인엽 {
  static int N; // 구역 개수
  static int[] arr; // 구역별 인구수
  static List<Integer>[] list; // 구역별 연결된 구역들
  static int answer = Integer.MAX_VALUE; // 인구차이의 최솟값
  static boolean[] visited; // 방문여부
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N+1]; // 편리한 인덱싱을 위해 N+1로 초기화
    visited = new boolean[N+1];
    list = new ArrayList[N+1];
    // list 초기화
    for(int i=1; i<N+1; i++) {
      list[i] = new ArrayList<>();
    }
    // 입력 처리
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=1; i<=N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    // list 채우기
    for(int i=1; i<=N; i++) {
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken());
      for(int j=0; j<num; j++) {
        list[i].add(Integer.parseInt(st.nextToken()));
      }
    }
    // 부분집합 만들기
    makeSubset(1, 0);

    // 만약 불가능하면 -1 출력
    System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
  }
  // 부분집합 만들기
  public static void makeSubset(int depth, int flag) {
    if(depth == N+1) {
      // 한쪽만 선택된게 아닐 때 + 선거구끼리 연결된 상태가 맞을때 정답 확인
      if(!(flag == 0 || flag == ((1 << N+1) - 1)) && isValid(flag)) {
        answer = Math.min(answer, calcPopulationDiff(flag));
      }
      return;
    }
    // 현재꺼 선택X
    makeSubset(depth+1, flag);
    // 현재꺼 선택
    makeSubset(depth+1, flag | 1 << depth);
  }
  // 두 선거구가 모두 서로 연결 가능한지 체크
  private static boolean isValid(int flag) {
    int cnt0 = 0; // 0팀 재귀 횟수
    int cnt1 = 0; // 1팀 재귀 횟수
    visited = new boolean[N+1];
    for(int i=1; i<N+1; i++) {
      // 이미 실패한 거니 바로 false
      if(cnt0 >= 2 || cnt1 >= 2) {
        return false;
      };
      // 방문한 곳은 지나치기
      if(visited[i]) continue;
      // 1팀일 때 재귀하러
      if((flag & (1 << i)) != 0) {
        dfs1(flag, i);
        cnt1++;
      } else {
        dfs0(flag, i);
        cnt0++;
      }
    }
    // 둘다 한번씩만 호출 됐어야함.
    return cnt0 == 1 && cnt1 == 1;
  }
  private static void dfs0(int flag, int current) {
    // 방문처리
    visited[current] = true;

    for(int v : list[current]) {
      // 만약 방문했다면 넘기기
      if(visited[v]) continue;
      // 우리 선거구 아니면 넘기기
      if((flag & (1 << v)) != 0) continue;
      dfs0(flag, v);
    }
  }
  private static void dfs1(int flag, int current) {
    // 방문처리
    visited[current] = true;

    for(int v : list[current]) {
      // 만약 방문했다면 넘기기
      if(visited[v]) continue;
      // 우리 선거구 아니면 넘기기
      if((flag & (1 << v)) == 0) continue;
      dfs1(flag, v);
    }
  }

  // 인구수 차이 계산 함수
  public static int calcPopulationDiff(int flag) {
    int diff = 0;
    for(int i=1; i<N+1; i++) {
      // 만약 1팀이면 더하고
      if((flag & (1 << i)) != 0) diff += arr[i];
      // 0팀이면 빼기
      else diff -= arr[i];
    }
    return Math.abs(diff);
  }
}
