import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M, ans;
    static boolean[][] visit;
    static boolean[][] board;
    final static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    /**
     * Data
     */
    public class Data implements Comparable<Data>{
        int row, col, count;

        Data(int r, int c, int cnt){
            row = r;
            col = c;
            count = cnt;
        }

        @Override
        public int compareTo(Main.Data o) {
            return Integer.compare(count, o.count);
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        visit = new boolean[N][M];
        board = new boolean[N][M];
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++){
            String input = br.readLine();
            for (int j = 0; j < M; j++){
                if (input.charAt(j) == '1')
                    board[i][j] = true;
            }
        }
        visit[0][0] = true;
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.add(new Data(0, 0, 0));
        while (!pq.isEmpty()){
            Data d = pq.poll();
            int row = d.row;
            int col = d.col;
            int count = d.count;
            if (row == N - 1 && col == M - 1){
                System.out.println(count);
                return;
            }
            for (int i = 0; i < 4; i++){
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc])
                    continue;
                visit[nr][nc] = true;
                Data next = new Data(nr, nc, count);
                if (board[nr][nc])
                    next.count++;
                pq.add(next);
            }
        }

    
        
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}