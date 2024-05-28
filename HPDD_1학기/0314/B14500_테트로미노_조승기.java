import java.util.*;
import java.io.*;

public class Main {
    static int[][] li;
    static int[] ix = new int[]{0, 0, 1, -1};
    static int[] iy = new int[]{1, -1, 0, 0};
    static boolean[][] isv;
    static int N, M;
    static int[][] iix = new int[][]{{0, -1, 0, 1}, {0, 1, 1, 1}, {0, -1, 0, 1}, {0, -1, -1, -1}};
    static int[][] iiy = new int[][]{{0, 1, 1, 1}, {0, -1, 0, 1}, {0, -1, -1, -1}, {0, -1, 0, 1}};
    static int dfs(int n, int i, int j) {
        if (n == 3) return li[i][j];
        int ans = 0;

        for (int k = 0; k < 4; k++) {
            int x = i + ix[k];
            int y = j + iy[k];
            if (x < 0 || y < 0 || x >= N || y >= M || isv[x][y]) {continue;}
            isv[x][y] = true;
            ans = Math.max(dfs(n + 1, x, y), ans);
            isv[x][y] = false;
        }
        return ans + li[i][j];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        li = new int[N][M];
        isv = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isv[i][j] = true;
                ans = Math.max(dfs(0, i, j), ans);
                isv[i][j] = false;
                for (int k = 0; k < 4; k++) {
                    int local = 0;
                    for (int l = 0; l < iix[0].length; l++) {
                        int x = i + iix[k][l];
                        int y = j + iiy[k][l];
                        if (x < 0 || y < 0 || x >= N || y >= M) {
                            break;
                        }
                        local += li[x][y];
                    }
                    ans = Math.max(local, ans);
                }
            }
        }
        System.out.println(ans);
    }
}