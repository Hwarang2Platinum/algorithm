import java.util.*;
import java.io.*;

public class B1052_물병_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = 0;

        while (Integer.bitCount(N) > K) {
            ans += Integer.lowestOneBit(N);
            N += Integer.lowestOneBit(N);
        }

        System.out.println(ans);
    }
}
