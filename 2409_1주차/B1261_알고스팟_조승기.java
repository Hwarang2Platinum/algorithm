
import java.util.*;
import java.io.*;

public class B1261_알고스팟_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] isv = new int[N][M];
        int[][] li = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                li[i][j] = line.charAt(j) - '0';
            }
        }

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, 0, 0));
        int[] ix = new int[]{0, 0, 1, -1};
        int[] iy = new int[]{1, -1, 0, 0};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isv[i][j] = Integer.MAX_VALUE;
            }
        }

        isv[0][0] = 0;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (isv[p.x][p.y] < p.w) continue;

            for (int i = 0; i < 4; i++) {
                int x = p.x + ix[i];
                int y = p.y + iy[i];
                if (x < 0 || x >= N || y < 0 || y >= M) continue;
                int w = p.w + li[x][y];
                if (isv[x][y] <= w) continue;
                isv[x][y] = w;
                q.offer(new Pair(x, y, w));
            }
        }
        System.out.println(isv[N - 1][M - 1]);
    }

    static class Pair {
        int x, y, w;
        public Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}
