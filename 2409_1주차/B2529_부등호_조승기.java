import java.util.*;
import java.io.*;

public class B2529_부등호_조승기 {
    static int N;
    static boolean[] isLeftSmall;
    static long[] li;
    static boolean[] isv = new boolean[10];
    static long min = 9999999999L;
    static long max = 0L;

    static void find(int n) {
        if (n == N + 1) {
            long tmp = 0;
            for (int i = 0; i < N+1; i++) {
                tmp = tmp * 10 + li[i];
            }
            min = Math.min(min, tmp);
            max = Math.max(max, tmp);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (isv[i]) continue;
            if ((isLeftSmall[n - 1] && li[n - 1] < i) || (!isLeftSmall[n - 1] && li[n - 1] > i)) {
                li[n] = i;
                isv[i] = true;
                find(n + 1);
                isv[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isLeftSmall = new boolean[N];
        li = new long[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            isLeftSmall[i] = st.nextToken().charAt(0) == '<';

        for (int i = 0; i < 10; i++) {
            li[0] = i;
            isv[i] = true;
            find(1);
            isv[i] = false;
        }

        System.out.println(max);
        System.out.println(String.valueOf(min).length() != String.valueOf(max).length() ? ("0" + min) : min);
    }
}
