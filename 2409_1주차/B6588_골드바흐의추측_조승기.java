import java.util.*;
import java.io.*;

public class B6588_골드바흐의추측_조승기 {
    public static void main(String[] args) throws Exception {
        int MAX = 1_000_001;
        boolean[] isp = new boolean[MAX];
        Arrays.fill(isp, true);

        for (int i = 2; i < MAX; i++) {
            if (!isp[i]) continue;

            for (int j = i*2; j < MAX; j+=i) {
                isp[j] = false;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            for (int i = 3; i <= n; i++) {
                if (i % 2 == 1 && (n - i) % 2 == 1 && isp[i] && isp[n-i]) {
                    sb.append(n + " = " + i + " + " + (n - i) + "\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }

}