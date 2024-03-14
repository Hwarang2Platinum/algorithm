import java.util.*;
import java.io.*;

public class Main {
    static int[][][] tetrominos = {
            {{1,1,1,1}},
            {{1},{1},{1},{1}},
            {{1,1},{1,1}},

            {{1,1}, {0,1}, {0,1}},
            {{1,1,1},{1,0,0}},
            {{1,0},{1,0},{1,1}},
            {{0,0,1},{1,1,1}},

            {{1,1}, {1,0}, {1,0}},
            {{1,1,1},{0,0,1}},
            {{0,1},{0,1},{1,1}},
            {{1,0,0},{1,1,1}},

            {{1,0},{1,1},{0,1},},
            {{0,1,1},{1,1,0}},

            {{0,1},{1,1},{1,0},},
            {{1,1,0},{0,1,1}},

            {{1,0},{1,1},{1,0}},
            {{0,1,0}, {1,1,1}},
            {{0,1},{1,1},{0,1}},
            {{1,1,1}, {0,1,0}}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans =0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t=0;t<19;t++){
            int[][] tm =  tetrominos[t];
            int height = tm.length;
            int width = tm[0].length;

            for(int i =0;i<=N-height;i++){
                for(int j=0;j<=M-width;j++){
                    int localSum = 0;
                    for(int y=0;y<height;y++){
                        for(int x=0;x<width;x++){
                            if(tm[y][x] == 0) continue;
                            localSum+=board[y+i][x+j];
                        }
                    }
                    ans = Math.max(ans, localSum);
                }
            }
        }

        System.out.println(ans);

    }

}