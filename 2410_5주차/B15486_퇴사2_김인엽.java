import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 2][2];
        int[] dp = new int[N + 2];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            arr[i][0] = T;
            arr[i][1] = P;
        }

        int max = -1;
        for (int i = 1; i < N + 2; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
            int next = i + arr[i][0];
            if (next < N + 2) {
                dp[next] = Math.max(dp[next],
                        max + arr[i][1]);
            }
        }
        System.out.println(dp[N + 1]);
    }
}