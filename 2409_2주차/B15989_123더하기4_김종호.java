import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    
    static int dp[][];
    static int ans[];

    



    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp = new int[10000 + 1][3 + 1];
        ans = new int[10000 + 1];
        
        ans[1] = 1;
        ans[2] = 2;
        ans[3] = 3;
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 2;
        dp[3][3] = 1;
        for (int i = 4; i <= 10000; i++){
            dp[i][1] = ans[i - 1];
            dp[i][2] = dp[i - 2][2] + dp[i - 2][3];
            dp[i][3] = dp[i - 3][3];
            ans[i] = dp[i][1] + dp[i][2] + dp[i][3];
        }
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0){
            int k = Integer.parseInt(br.readLine());
            sb.append(ans[k]).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}