import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {0,-1,-1,-1,0,1,1,1};
    static int[] dc = {-1,-1,0,1,1,1,0,-1};
    static int N,M;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<Pos> clouds = new ArrayDeque<>();
        clouds.add(new Pos(N-1,0));
        clouds.add(new Pos(N-1,1));
        clouds.add(new Pos(N-2,0));
        clouds.add(new Pos(N-2,1));
        for(int t=0;t<M;t++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            //비 맞은 땅 저장
            Queue<Pos> grounds = new ArrayDeque<>();

            // 구름 이동 & 비내리기
            boolean[][] isv = new boolean[N][N];
            while(!clouds.isEmpty()){
                Pos cloud = clouds.poll();
                cloud.move(d,s);
                grounds.add(cloud);
                isv[cloud.r][cloud.c] = true;
                board[cloud.r][cloud.c] += 1;
            }

            // 물복사 버그

            while(!grounds.isEmpty()){
                Pos ground = grounds.poll();
                int daegakCnt = 0;
                // 대각선 확인
                for(int i= 1; i<8;i+=2){
                    daegakCnt += ground.waterCopyBug(i);
                }
                board[ground.r][ground.c] += daegakCnt;
            }

            // 구름 생성
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(isv[i][j] || board[i][j]<2) continue;

                    board[i][j] -= 2;
                    clouds.add(new Pos(i,j));

                }
            }
        }
        int ans = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                ans+=board[i][j];
            }
        }
        System.out.println(ans);
    }

    static class Pos{
        int r,c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public void move(int d, int s){
            r = (r+s*dr[d])%N; c = (c+s*dc[d])%N;
            if(r<0) r = N+r;
            if(c<0) c = N+c;
        }
        public int waterCopyBug(int i){
            if(r+dr[i]<0||r+dr[i]>N-1||c+dc[i]<0||c+dc[i]>N-1) return 0;
            if(board[r+dr[i]][c+dc[i]]<1) return 0;
            return 1;
        }
    }
}