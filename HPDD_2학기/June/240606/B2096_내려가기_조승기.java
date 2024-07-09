import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] mdp = new int[N][3];
        int[][] dp = new int[N][3];

        String line = br.readLine();
        mdp[0][0] = dp[0][0] = line.charAt(0)-'0';
        mdp[0][1] = dp[0][1] = line.charAt(2)-'0';
        mdp[0][2] = dp[0][2] = line.charAt(4)-'0';

        for (int i = 1; i < N; i++) {
            line = br.readLine();
            mdp[i][0] = dp[i][0] = line.charAt(0)-'0';
            mdp[i][1] = dp[i][1] = line.charAt(2)-'0';
            mdp[i][2] = dp[i][2] = line.charAt(4)-'0';
            mdp[i][0] += Math.min(mdp[i - 1][0], mdp[i - 1][1]);
            mdp[i][1] += Math.min(mdp[i - 1][0], Math.min(mdp[i - 1][1], mdp[i - 1][2]));
            mdp[i][2] += Math.min(mdp[i - 1][1], mdp[i - 1][2]);

            dp[i][0] += Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][2] += Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        System.out.println(Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2])) + " " + Math.min(mdp[N - 1][0], Math.min(mdp[N - 1][1], mdp[N - 1][2])));
    }
}