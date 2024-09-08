import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int T, N;
    static int[][] dp;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer up, down;

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            // 위쪽, 아래쪽, 아무것도 안뜯었을때
            dp = new int[2][3];
            up = new StringTokenizer(br.readLine());
            down = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                dp[0] = dp[1].clone();
                dp[1][0] = Math.max(dp[0][1], dp[0][2]) + Integer.parseInt(up.nextToken());
                dp[1][1] = Math.max(dp[0][0], dp[0][2]) + Integer.parseInt(down.nextToken());
                dp[1][2] = Math.max(dp[0][1], Math.max(dp[0][2], dp[0][0]));

            }
            sb.append(Math.max(dp[1][1], Math.max(dp[1][2], dp[1][0]))).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}