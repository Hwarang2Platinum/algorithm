import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(arr[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs(int x, int y) {
        int tmpAnswer = 1;
        Queue<int[]> q = new ArrayDeque<>();
        arr[x][y] = 2;
        q.add(new int[]{x, y});

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=0; i<4; i++) {
                int cx = cur[0] + dx[i];
                int cy = cur[1] + dy[i];
                if(cx < 1 || cx > N || cy < 1 || cy > M) continue;
                if(arr[cx][cy] == 1) {
                    arr[cx][cy] = 2;
                    q.add(new int[]{cx, cy});
                    tmpAnswer++;
                }
            }
        }

        answer = Math.max(answer, tmpAnswer);
    }
}
