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

        int[][] board=  new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j)=='1'?1:0;
            }
        }

        ArrayDeque<int[]> deque = new ArrayDeque<>();
        boolean[][] isv = new boolean[N][M];
        deque.addFirst(new int[]{0,0,0});
        isv[0][0] = true;
        while (!deque.isEmpty()){
            int[] cur = deque.pollFirst();
            if(cur[0]==N-1 && cur[1] ==M-1){
                System.out.println(cur[2]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if(nextR<0||nextR>N-1||nextC<0||nextC>M-1) continue;
                if(isv[nextR][nextC]) continue;
                isv[nextR][nextC] = true;

                if(board[nextR][nextC]==1){
                    deque.addLast(new int[]{nextR,nextC,cur[2]+1});
                }else{
                    deque.addFirst(new int[]{nextR,nextC,cur[2]});
                }
            }

        }


    }
}