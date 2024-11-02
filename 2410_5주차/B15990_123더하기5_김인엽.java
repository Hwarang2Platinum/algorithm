import java.io.*;
import java.util.*;

public class Main {
    static final int NUM = 1_000_000_009;
    static final int MAX_NUM = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[][] dp = new long[MAX_NUM+1][4];

        // 1 : 1
        dp[1][1] = 1;
        // 2 : 2
        dp[2][2] = 1;
        // 3 : 1 + 2 / 2 + 1 / 3
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= MAX_NUM; i++) {
            // dp[i]의 1로 끝나는 거는 dp[i-1][2] + dp[i-1][3]
            dp[i][1] = (dp[i-1][2]%NUM + dp[i-1][3]%NUM) % NUM;
            // dp[i]의 2로 끝나는 거는 dp[i-2][1] + dp[i-2][3]
            dp[i][2] = (dp[i-2][1]%NUM + dp[i-2][3]%NUM) % NUM;
            // dp[i]의 3로 끝나는 거는 dp[i-3][1] + dp[i-3][2]
            dp[i][3] = (dp[i-3][1]%NUM + dp[i-3][2]%NUM) % NUM;
        }

        for(int test_case = 0; test_case < T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            // n을 1, 2, 3으로 나타내기!
            // 아이디어: dp 배열을 3xn으로 해서 1,2,3 각 개수를 저장하자
            System.out.println((dp[n][1]%NUM + dp[n][2]%NUM + dp[n][3]%NUM)%NUM);
        }
    }
}
