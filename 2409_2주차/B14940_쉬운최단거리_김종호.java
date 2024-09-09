import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    
    static int N, M;
    static int sr, sc;
    static int[][] board;
    static int[][] visit;
    final static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    /**
     * Data
     */
    public class Data {
        int row, col, count;
        Data(int r, int c, int cnt){
            row = r;
            col = c;
            count = cnt;
        }
        
    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visit = new int[N][M];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                int k = Integer.parseInt(st.nextToken());
                if (k == 2){
                    sr = i;
                    sc = j;
                    k = 0;
                }
                if (k == 0){
                    visit[i][j] = 1;
                }
                board[i][j] = k;
            }
        }
        visit[sr][sc] = 1;
        Queue<Data> q = new ArrayDeque<>();
        q.add(new Data(sr, sc, 1));
        while (!q.isEmpty()){
            Data d = q.poll();
            int row = d.row;
            int col = d.col;
            int count = d.count;
            for (int i = 0; i < 4; i++){
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nc < 0 || nr < 0 || nr >= N || nc >= M || visit[nr][nc] != 0)
                    continue;
                visit[nr][nc] = count + 1;
                q.add(new Data(nr, nc, count + 1));
            }           
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                sb.append(visit[i][j] - 1).append(" ");
            }
            sb.append("\n");
        }       
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}