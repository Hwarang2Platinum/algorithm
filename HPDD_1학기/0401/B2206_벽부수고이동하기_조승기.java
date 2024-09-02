import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int[] ix = new int[]{0, 0, 1, -1};
    static int[] iy = new int[]{1, -1, 0, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] li = new boolean[N][M];
        if (N == 1 && M == 1) {
            System.out.println(1);
            System.exit(0);
        }
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                li[i][j] = line.charAt(j) == '0';
            }
        }

        int[][] isv = new int[N][M];
        int[][] isb = new int[N][M];

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, 0, 1, false));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = p.x + ix[i];
                int y = p.y + iy[i];

                if (x < 0 || y < 0 || x >= N || y >= M) continue;
                if (li[x][y]) {
                    if (p.isb) {
                        if (isb[x][y] == 0) {
                            q.add(new Pair(x, y, p.w + 1, p.isb));
                            isb[x][y] = p.w + 1;
                        }
                    } else {
                        if (isv[x][y] == 0) {
                            q.add(new Pair(x, y, p.w + 1, p.isb));
                            isv[x][y] = p.w + 1;
                        }
                    }
                } else {
                    if (!p.isb && isb[x][y] == 0) {
                        q.add(new Pair(x, y, p.w + 1, true));
                        isb[x][y] = p.w + 1;
                    }
                }
            }
        }
        int ans = Math.min((isv[N - 1][M - 1] == 0 ? 1234578 : isv[N - 1][M - 1]), (isb[N - 1][M - 1] == 0 ? 1234578 : isb[N - 1][M - 1]));
        System.out.println(ans == 1234578 ? -1 : ans);
    }
    static class Pair {
        int x; int y; int w; boolean isb;

        public Pair(int x, int y, int w, boolean isb) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.isb = isb;
        }
    }
}
