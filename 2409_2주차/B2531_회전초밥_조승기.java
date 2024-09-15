import java.util.*;
import java.io.*;

public class B2531_회전초밥_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] li = new int[N];

        for (int i = 0; i < N; i++) {
            li[i] = Integer.parseInt(br.readLine());
        }
        int[] eat = new int[d+1];

        for (int i = 0; i < k; i++) {
            eat[li[i]] += 1;
        }
        int ans = 0;

        for (int i = k; i < k + N; i++) {
            int local = 0;
            for (int j = 0; j < d+1; j++) {
                local += eat[j] > 0 ? 1 : 0;
            }
            ans = Math.max(ans, local + (eat[c] >= 1 ? 0 : 1));

            eat[li[i % N]] += 1;
            eat[li[((i + N) - k) % N]] -= 1;
        }
        System.out.println(ans);
    }
}
