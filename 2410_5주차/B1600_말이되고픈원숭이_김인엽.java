import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int N, M;
    static int[][] arr;
    static boolean[][][] visited;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[] hx = {-2,-2,-1,-1,1,1,2,2};
    static int[] hy = {-1,1,-2,2,-2,2,-1,1};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M][K+1];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0, 0);
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }

    static void bfs(int x, int y) {
        Queue<Position> q = new ArrayDeque<>();
        q.add(new Position(x, y, 0, 0));
        visited[x][y][0] = true;

        while(!q.isEmpty()) {
            Position cur = q.poll();
            if(cur.x == N-1 && cur.y == M-1) {
                answer = Math.min(answer, cur.goCnt);
                continue;
            }

            if(cur.horseCnt < K) {
                for (int i = 0; i < hx.length; i++) {
                    int cx = cur.x + hx[i];
                    int cy = cur.y + hy[i];
                    if(!isInArea(cx, cy)) continue;
                    if(arr[cx][cy] == 1) continue;
                    if(visited[cx][cy][cur.horseCnt+1]) continue;
                    visited[cx][cy][cur.horseCnt+1] = true;
                    q.add(new Position(cx, cy, cur.horseCnt+1, cur.goCnt+1));
                }
            }

            for(int i=0; i<dx.length; i++) {
                int cx = cur.x + dx[i];
                int cy = cur.y + dy[i];
                if(!isInArea(cx, cy)) continue;
                if(arr[cx][cy] == 1) continue;
                if(visited[cx][cy][cur.horseCnt]) continue;
                visited[cx][cy][cur.horseCnt] = true;
                q.add(new Position(cx, cy, cur.horseCnt, cur.goCnt+1));
            }
        }
    }

    static boolean isInArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Position {
        int x, y;
        int horseCnt;
        int goCnt;
        public Position(int x, int y, int horseCnt, int goCnt) {
            this.x = x;
            this.y = y;
            this.horseCnt = horseCnt;
            this.goCnt = goCnt;
        }
    }
}
