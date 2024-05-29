import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14889_IY {
  final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int N;
  static int[][] arr;
  static int answer = Integer.MAX_VALUE;
  static Stack<Integer> result = new Stack<>(); // 조합의 결과 담아둠

  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(br.readLine());
    arr = new int[N][N];
    StringTokenizer st;
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    dfs(0);
    System.out.println(answer);
  }

  private static void dfs(int start) {
    // 팀 인원 맞추면 점수계산
    if(result.size() == N/2) {
      calcScore();
      return;
    }

    for(int i=start; i<N; i++) {
      if(!result.contains(i)) {
        result.add(i);
        dfs(i+1);
        result.pop();
      }
    }
  }

  private static void calcScore() {
    // 맞춘 팀 점수
    int score = 0;
    for(int i: result) {
      for(int j: result) {
        score += arr[i][j];
      }
    }

    // 해당 팀이 아닌 팀 점수
    List<Integer> nonResult = new ArrayList<>();
    for(int i=0; i<N; i++) {
      if(!result.contains(i)) nonResult.add(i);
    }
    for(int i: nonResult) {
      for(int j: nonResult) {
        score -= arr[i][j];
      }
    }

    answer = Math.min(answer, Math.abs(score));
  }

}