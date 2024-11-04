import java.util.*;
import java.io.*;
public class B1600_말이되고픈원숭이_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] li = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] isv = new int[K+1][N][M];
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, 0, 0, K));
        int[] ix = new int[]{1, -1, 0, 0};
        int[] iy = new int[]{0, 0, 1, -1};
        int[] ixx = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] iyy = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};
        if (N == 1 && M == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < K+1; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    isv[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.x == N-1 && p.y == M-1) {
                System.out.println(p.w);
                return;
            }
            if (p.w > isv[p.k][p.x][p.y]) continue;
            for (int i = 0; i < 4; i++) {
                int X = ix[i] + p.x;
                int Y = iy[i] + p.y;
                if (X < 0 || Y < 0 || X >= N || Y >= M || li[X][Y] == 1 || (isv[p.k][X][Y] <= p.w + 1)) continue;
                isv[p.k][X][Y] = p.w + 1;
                q.add(new Pair(X, Y, p.w + 1, p.k));
            }
            if (p.k == 0) continue;

            for (int i = 0; i < ixx.length; i++) {
                int X = ixx[i] + p.x;
                int Y = iyy[i] + p.y;
                if (X < 0 || Y < 0 || X >= N || Y >= M || li[X][Y] == 1 || (isv[p.k-1][X][Y] <= p.w + 1)) continue;
                isv[p.k-1][X][Y] = p.w + 1;
                q.add(new Pair(X, Y, p.w + 1, p.k-1));
            }
        }
        System.out.println(-1);
    }
    static class Pair{
        int x,y,w,k;
        public Pair(int x, int y, int w, int k) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.k = k;
        }
    }
}
