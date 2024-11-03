import java.util.*;
import java.io.*;

public class B15486_퇴사2_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ts = new int[N];
        int[] ps = new int[N];
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ts[i] = Integer.parseInt(st.nextToken());
            ps[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);

            int nd = i + ts[i];
            if (nd <= N) {
                dp[nd] = Math.max(dp[nd], max + ps[i]);
            }
        }

        max = Math.max(max, dp[N]);
        System.out.println(max);
    }
}
