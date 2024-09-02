import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int[][] maxDp, minDp;
    static int min, max;
    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        maxDp = new int[2][3];
        minDp = new int[2][3];
        N = Integer.parseInt(br.readLine());
        int x, y, z;

        for (int i = 0; i < N; i++) {
            maxDp[0] = maxDp[1].clone();
            minDp[0] = minDp[1].clone();
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            maxDp[1][0] = Math.max(maxDp[0][0], maxDp[0][1]) + x;
            maxDp[1][1] = Math.max(Math.max(maxDp[0][0], maxDp[0][1]), maxDp[0][2]) + y;
            maxDp[1][2] = Math.max(maxDp[0][1], maxDp[0][2]) + z;
            minDp[1][0] = Math.min(minDp[0][0], minDp[0][1]) + x;
            minDp[1][1] = Math.min(Math.min(minDp[0][0], minDp[0][1]), minDp[0][2]) + y;
            minDp[1][2] = Math.min(minDp[0][1], minDp[0][2]) + z;
        }
        min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minDp[1][i]);
            max = Math.max(max, maxDp[1][i]);
        }
        System.out.println(max + " " + min);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}