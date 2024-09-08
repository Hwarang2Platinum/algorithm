import java.util.*;
import java.io.*;

public class B1987_알파벳_조승기 {
    static int R, C;
    static char[][] li;
    static int[] ix = new int[]{0, 0, 1, -1};
    static int[] iy = new int[]{1, -1, 0, 0};
    static int ans = 0;
    static void dfs(int x, int y, int n, int bit) {
        ans = Math.max(n, ans);

        for (int i = 0; i < 4; i++) {
            int X = x + ix[i];
            int Y = y + iy[i];
            if (X < 0 || Y < 0 || X >= R || Y >= C) continue;
            int next = 1 << (li[X][Y] - 'A');
            if ((bit & next) == 0) {
                dfs(X, Y, n+1, bit | next);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        li = new char[R][C];
        for (int i = 0; i < R; i++) {
            li[i] = br.readLine().toCharArray();
        }
        dfs(0, 0, 1, 1 << (li[0][0] - 'A'));
        System.out.println(ans);
    }
}
