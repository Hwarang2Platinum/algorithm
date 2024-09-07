import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] board = new boolean[N][M];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            for (int i = r1; i < r2; i++) {
                for (int j = c1; j < c2; j++) {
                    board[i][j] = true;
                }
            }
        }
        int cnt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j]) continue;
                cnt++;
                pq.add(floodFill(i,j, board));
            }
        }

        System.out.println(cnt);
        while (!pq.isEmpty()){
            System.out.print(pq.poll()+" ");
        }
        System.out.println();
    }

    public static int floodFill(int startR, int startC, boolean[][] board){

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startR,startC});
        board[startR][startC] = true;
        int size = 1;

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];
                if(nextR<0||nextR>N-1||nextC<0||nextC>M-1) continue;
                if(board[nextR][nextC])continue;
                q.add(new int[]{nextR,nextC});
                board[nextR][nextC]= true;
                size++;
            }
        }
        return size;
    }
}