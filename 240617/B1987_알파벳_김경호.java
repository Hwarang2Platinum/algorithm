import java.util.*;
import java.io.*;

public class Main {
    public static class Pos{
        int r, c;
        int isv;
        public Pos(int r, int c, int isv) {
            this.r = r;
            this.c = c;
            this.isv = isv;
        }
    }

    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] dr = {0,1,0,-1};
        int[] dc = {1,0,-1,0};
        int[][] board = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j)-'A'+1;
            }
        }
        ans = 0;
        dfs(0,new Pos(0,0,1 << board[0][0]),dr,dc,board,R,C);
        System.out.println(ans+1);
    }

    public static void dfs(int depth, Pos cur, int[] dr, int[] dc, int[][] board, int R, int C){
        ans = Math.max(ans, depth);

        for (int i = 0; i < 4; i++) {
            int nextR = cur.r + dr[i];
            int nextC = cur.c + dc[i];
            if(nextR < 0 || nextR > R-1 || nextC < 0 || nextC > C-1) continue;
            if((cur.isv & (1<<board[nextR][nextC])) == 0)
                dfs(depth+1, new Pos(nextR, nextC, cur.isv | (1 << board[nextR][nextC])), dr,dc,board,R,C);
        }
    }
}