import java.io.*;
import java.util.*;

public class Main {
    static char[][] board;
    static int N,M;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] isv = new boolean[N][M];
        boolean[][] isv2 = new boolean[N][M];
        q.add(new int[]{0,0,0});
        int cnt = 0;
        while (!q.isEmpty()){
            int size = q.size();
            while (--size>=0){
                int[] cur = q.poll();
                if(cur[0]==N-1&&cur[1]==M-1){
                    System.out.println(cnt+1);
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int nextr = cur[0] + dr[i];
                    int nextc = cur[1] + dc[i];

                    int isBreak = cur[2];
                    if(nextr<0||nextr>N-1||nextc<0||nextc>M-1) continue;
                    if(isBreak>0){
                        if(isv2[nextr][nextc]) continue;
                        if(board[nextr][nextc]=='1') continue;
                        isv2[nextr][nextc] = true;
                        q.add(new int[]{nextr,nextc,1});
                    }else{
                        if(isv[nextr][nextc]) continue;
                        if(board[nextr][nextc]=='1'){
                            isv2[nextr][nextc] = true;
                            q.add(new int[]{nextr,nextc,1});
                        }
                        else{
                            isv[nextr][nextc] = true;
                            q.add(new int[]{nextr,nextc,0});
                        }
                    }
                }
            }
            cnt++;
        }
        System.out.println(-1);
    }
}