import java.io.*;
import java.util.*;
public class B9095_123더하기_이찬민 {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] dp = new int[11];

        dp[1]=1;
        dp[2]=2;
        dp[3]=4;
        for (int i = 4; i <= 10; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 1; i <= T; i++) {
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
