import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dr = {1,0,-1,0};
        int[] dc = {0,-1,0,1};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] ans = new int[N][M];
        boolean[][] isv = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2) {
                    q.add(new int[]{i,j,0});
                    isv[i][j] = true;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0) continue;
                ans[i][j] = -1;
            }
        }

        while (!q.isEmpty()){
            int[] cur = q.poll();

            ans[cur[0]][cur[1]] = cur[2];

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if(nextR<0||nextR>N-1||nextC<0||nextC>M-1) continue;
                if(map[nextR][nextC]==0) continue;
                if(isv[nextR][nextC]) continue;
                isv[nextR][nextC] = true;
                q.add(new int[]{nextR,nextC,cur[2]+1});
            }
        }

        for (int[] m: ans){
            for (int i = 0; i < M; i++) {
                System.out.print(m[i]);
                if(i==M-1) System.out.println();
                else System.out.print(" ");
            }
        }
    }
}
