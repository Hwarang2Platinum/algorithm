import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int T, x1, x2, y1, y2, N, ans;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(br.readLine());
            ans = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int cx, cy, cr;
                cx = Integer.parseInt(st.nextToken());
                cy = Integer.parseInt(st.nextToken());
                cr = Integer.parseInt(st.nextToken());
                boolean in1, in2;
                in1 = (Math.pow((x1 - cx), 2) + Math.pow((y1 - cy), 2)) < Math.pow(cr, 2);
                in2 = (Math.pow((x2 - cx), 2) + Math.pow((y2 - cy), 2)) < Math.pow(cr, 2);
                if (!(in1 && in2) && (in1 || in2))
                    ans++;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}