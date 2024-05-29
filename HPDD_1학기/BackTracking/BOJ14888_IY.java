import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14888_IY {
  final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  static int N;
  static int[] arr;
  static int minValue = Integer.MAX_VALUE;
  static int maxValue = Integer.MIN_VALUE; // 이거 0으로 했다가 틀림

  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(br.readLine());
    arr = new int[N]; // 숫자들

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] ops = new int[4]; // 연산자들
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<4; i++) {
      ops[i] = Integer.parseInt(st.nextToken());
    }

    dfs(1, ops, arr[0]);

    System.out.println(maxValue);
    System.out.println(minValue);
    br.close();
  }

  private static void dfs(int index, int[] remainOps, int result) {
    if(index == N) { // 모든 숫자 다 돌았으면, 결과값 체크
      maxValue = Math.max(maxValue, result);
      minValue = Math.min(minValue, result);
      return;
    }
    int[] tmpOps;
    for(int i=0; i<remainOps.length; i++) {
      // 쓸수있는 연산자만
      if(remainOps[i] == 0) continue;
      // 배열 복사
      tmpOps = Arrays.copyOf(remainOps, remainOps.length);
      // 횟수 감소 시키기
      tmpOps[i]--;
      switch(i) {
        case 0: // 덧셈
          dfs(index+1, tmpOps, result+arr[index]);
          break;
        case 1: // 뺄셈
          dfs(index+1, tmpOps, result-arr[index]);
          break;
        case 2: // 곱셈
          dfs(index+1, tmpOps, result*arr[index]);
          break;
        case 3: // 나눗셈
          int tmpResult = result>=0?(result/arr[index]):(-result)/arr[index]*(-1);
          dfs(index+1, tmpOps, tmpResult);
          break;
      }
    }
  }

}