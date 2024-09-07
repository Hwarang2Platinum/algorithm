import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M, K, S;
    static boolean[][] board;
    static PriorityQueue<Integer> pq;
    final static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public class Data {
        int row, col;
        Data(int r, int c){
            row = r;
            col = c;
        }
    }

    public void bfs(int r, int c){
        int size = 0;
        Queue<Data> q = new ArrayDeque<>();
        q.add(new Data(r, c));
        board[r][c] = true;
        while (!q.isEmpty()){
            Data cur = q.poll();
            int row = cur.row;
            int col = cur.col;
            size++;
            for (int i = 0; i < 4; i++){
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || board[nr][nc])
                    continue;
                board[nr][nc] = true;
                q.add(new Data(nr, nc));
            }
        }
        pq.add(size);
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new boolean[N][M];
        pq = new PriorityQueue<>();

        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x = x1; x < x2; x++){
                for (int y = y1; y < y2; y++){
                    board[y][x] = true;
                }
            }
        }


        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (!board[i][j]){
                    S++;
                    bfs(i, j);
                }
            }
        }
        sb.append(S).append("\n");
        while (!pq.isEmpty()){
            int k = pq.poll();
            sb.append(k).append(" ");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}