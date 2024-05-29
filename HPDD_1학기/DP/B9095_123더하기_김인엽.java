import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 	14064	124
public class B9095_123더하기_김인엽 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    int[] dp = new int[11]; // n 최대 10
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    for(int i=4; i<11; i++) {
      dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
    }

    for(int test_case=0; test_case < T; test_case++) {
      int n = Integer.parseInt(br.readLine());
      System.out.println(dp[n]);
    }
  }
}
