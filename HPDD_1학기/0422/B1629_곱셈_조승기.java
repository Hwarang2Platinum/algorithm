import java.util.*;
import java.io.*;

public class B1629_곱셈_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        long ans = 1;

        for (int i = 0; i < 32; i++) {
            if ((b & 1<<i) != 0)
                ans = (ans * a) % c;
            a = (a * a) % c;
        }
        System.out.println(ans);
    }
}