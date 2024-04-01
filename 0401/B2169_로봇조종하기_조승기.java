import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 메모리	92612KB	시간 528ms

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] li = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];
        for (int j = 0; j < M; j++) {
            dp[0][j] = (j == 0 ? 0 : dp[0][j-1]) + li[0][j];
        }
        int inf = 1111111111;
        for (int i = 1; i < N-1; i++) {
            int[] l = new int[M];
            int[] r = new int[M];

            for (int j = 0; j < M; j++) {
                l[j] = Math.max((j == 0 ? -inf : (l[j - 1]+ li[i][j])), dp[i - 1][j]+ li[i][j]);
            }
            for (int j = M-1; j >= 0; j--) {
                r[j] = Math.max((j == M - 1 ? -inf : (r[j + 1] + li[i][j])), (dp[i - 1][j] + li[i][j]));
            }
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(l[j], r[j]);
            }
        }
        if (N > 1) {
            for (int j = 0; j < M; j++) {
                dp[N-1][j] = Math.max(j == 0 ? -inf : dp[N-1][j - 1], dp[N-2][j]) + li[N-1][j];
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}