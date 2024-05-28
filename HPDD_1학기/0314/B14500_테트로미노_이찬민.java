import java.io.*;
import java.util.*;

public class B14500_테트로미노_이찬민 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] arr;
    static boolean[][] visited;
    static int N, M;
    static int max;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        max = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                create(arr[i][j], 1, i, j);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    static void create( int sum, int depth, int x, int y) {

        if(depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<M && !visited[nx][ny]) {

                if(depth == 2) {
                    visited[nx][ny] = true;
                    create(sum + arr[nx][ny], depth + 1, x, y); // 뻐큐모양, 제자리에서 한번 더 4방탐색
                    visited[nx][ny] = false;
                }

                visited[nx][ny] = true;
                create(sum + arr[nx][ny], depth + 1, nx, ny );
                visited[nx][ny] = false;
            }
        }
    }
}