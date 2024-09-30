import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][C];
        boolean[][] isv = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String inp = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = inp.charAt(j);
            }
        }

        int wolfCnt = 0;
        int sheepCnt = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(isv[i][j] || board[i][j]=='#') continue;
                int val = bfs(i,j,board,isv,R,C);
                if(val<0) sheepCnt += -val;
                else wolfCnt += val;
            }
        }

        System.out.println(sheepCnt+" "+wolfCnt);
    }

    public static int bfs(int r, int c,char[][] board, boolean[][] isv, int R, int C){
        int[] dr = {1,0,-1,0};
        int[] dc = {0,-1,0,1};

        int wolfCnt = 0;
        int sheepCnt = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r,c});
        if(board[r][c]=='o') sheepCnt++;
        if(board[r][c]=='v') wolfCnt++;
        isv[r][c] = true;

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if(nextR<0||nextR>R-1||nextC<0||nextC>C-1) continue;
                if(isv[nextR][nextC] || board[nextR][nextC]=='#') continue;
                isv[nextR][nextC] = true;

                if(board[nextR][nextC]=='o') sheepCnt++;
                if(board[nextR][nextC]=='v') wolfCnt++;
                q.add(new int[]{nextR,nextC});
            }
        }

        return wolfCnt>=sheepCnt?wolfCnt:-sheepCnt;
    }
}
