import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 27016	400
public class B13424_비밀모임_김인엽 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine()); // 테스트 케이스
    StringTokenizer st;
    for(int testCase = 0; testCase < T; testCase++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken()); // 방 개수
      int M = Integer.parseInt(st.nextToken()); // 간선 개수
      int maxValue = 1_000*100*100; // c 최댓값 * NxN
      int[][] arr = new int[N+1][N+1]; // 인접 행렬 그래프

      for(int i=1; i<=N; i++) {
        for(int j=1; j<=N; j++) {
          if(i==j) arr[i][j] = 0; // i,i는 0으로
          else arr[i][j] = maxValue; // 나머지는 INF로 채우기
        }
      }

      for(int i=0; i<M; i++) { // 간선 정보 입력받기
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        arr[a][b] = arr[b][a] = c; // 그래프 채우기
      }

      int K = Integer.parseInt(br.readLine()); // 친구 수
      int[] friends = new int[K]; // 친구들
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<K; i++) friends[i] = Integer.parseInt(st.nextToken()); // 친구 채우기

      // 플로이드 와샬 레츠고
      for(int k=1; k<=N; k++) {
        for(int i=1; i<=N; i++) {
          for(int j=1; j<=N; j++) {
            arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
          }
        }
      }

      int answerRoomNum = 0;
      int moveSumMin = Integer.MAX_VALUE;
      for(int i=N; i>0; i--) { // 작은 값이 먼저와야되기 때문에 뒤에서부터 확인
        int moveSum = 0;
        // 해당 방에 대한 친구들의 이동 소요 합 구하기
        for(int friend: friends) {
          moveSum += arr[friend][i];
        }
        if(moveSumMin >= moveSum) {
          moveSumMin = moveSum;
          answerRoomNum = i;
        }
      }
      sb.append(answerRoomNum).append("\n");
    }
    System.out.println(sb);
  }
}
