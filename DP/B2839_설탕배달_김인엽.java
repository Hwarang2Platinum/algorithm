import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 그리디 14164, 124
//public class B2839_설탕배달_김인엽 {
//
//  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    int N = Integer.parseInt(br.readLine());
//    int answer = 0;
//    while(N>=0) {
//      if(N%5 == 0) {
//        answer += N / 5;
//        System.out.println(answer);
//        return;
//      }
//      N -= 3;
//      answer++;
//    }
//    System.out.println(-1);
//  }
//}


// DP 14276	124
public class B2839_설탕배달_김인엽 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[5001];

    for(int i=0; i<N+1; i++) dp[i] = 5001; // dp배열을 모두 최댓값으로
    dp[3] = dp[5] = 1;
    for(int i=6; i<N+1; i++) {
      dp[i] = Math.min(dp[i-3], dp[i-5]) + 1;
    }
    System.out.println((dp[N]<5001)?dp[N]:-1);
  }
}
