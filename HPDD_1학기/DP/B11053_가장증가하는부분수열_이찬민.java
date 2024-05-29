import java.io.*;
import java.util.*;

public class B11053_가장증가하는부분수열_이찬민 {

    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if (dp[i] == 0) {
                dp[i] = 1;

                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] < arr[i]) {  // 증가하는 경우
                        dp[i] = Math.max(dp[j] + 1, dp[i]); // dp배열에서 1 추가된다 생각
                    }
                }
            }
        }
        int result = 0;
        for (int x : dp) {
            result = Math.max(result, x);
        }
        System.out.println(result);
    }
}
