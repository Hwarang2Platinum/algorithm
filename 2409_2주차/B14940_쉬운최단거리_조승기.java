import java.util.*;
import java.io.*;

public class B14940_쉬운최단거리_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] li = new int[N][M];
        int[] ix = new int[]{0, 0, 1, -1};
        int[] iy = new int[]{1, -1, 0, 0};

        Pair s = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
                if (li[i][j] == 2) {
                    s = new Pair(i, j, 0);
                }
            }
        }
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(s);
        int[][] isv = new int[N][M];

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int x = p.x + ix[i];
                int y = p.y + iy[i];

                if (x < 0 || y < 0 || x >= N || y >= M || isv[x][y] != 0 || li[x][y] != 1) continue;
                isv[x][y] = p.w + 1;
                q.add(new Pair(x, y, p.w + 1));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (li[i][j] == 1 && isv[i][j] == 0) {
                    System.out.print(-1 + " ");
                } else {
                    System.out.print(isv[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static class Pair{
        int x, y, w;
        public Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}