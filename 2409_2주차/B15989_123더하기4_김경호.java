import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[10001][4];

        // dp [수][마지막숫자]
        dp[1][1] = 1; // 1
        dp[2][1] = 1; // 1+1
        dp[2][2] = 1; // 2
        dp[3][1] = 1; // 1+1+1
        dp[3][2] = 1; // 1+2
        dp[3][3] = 1; // 1+3

        // 점화식
        // dp[i][1] = dp[i-1][1]
        // dp[i][2] = dp[i-2][1]+dp[i-2][2]
        // dp[i][3] = dp[i-3][1]+dp[i-3][2]+dp[i-3][3]

        for(int i=4;i<=10000;i++){
             dp[i][1] = dp[i-1][1];
             dp[i][2] = dp[i-2][1]+dp[i-2][2];
             dp[i][3] = dp[i-3][1]+dp[i-3][2]+dp[i-3][3];
        }

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n][1]+dp[n][2]+dp[n][3]);
        }

    }
}
