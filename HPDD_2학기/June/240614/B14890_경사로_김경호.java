import java.util.*;
import java.io.*;

/*
*
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int pointer = 0;
            int depth = map[i][0];
            boolean[] isv = new boolean[N];

            boolean flag = true;

            loop: while (pointer<N){
                int curDepth = map[i][pointer];
                if(depth == curDepth){
                    pointer++;
                }else if(depth - curDepth == 1){ // 낮아진 경우
                    for (int j = 0; j < L; j++) {
                        if(pointer+j>N-1 || map[i][pointer+j] != curDepth){
                            flag = false;
                            break loop;
                        }
                        isv[pointer+j] = true;
                    }
                    depth = curDepth;
                    pointer+=L;
                } else if (curDepth - depth == 1) { // 높아진 경우
                    for (int j = 0; j < L; j++) {
                        if(pointer-1-j<0 || map[i][pointer-1-j] != depth || isv[pointer-1-j]){
                            flag = false;
                            break loop;
                        }
                    }
                    depth = curDepth;
                }else{
                    flag = false;
                    break;
                }
            }

            if (flag){
                ans++;
            }
        }

        for (int i = 0; i < N; i++) {
            int pointer = 0;
            int depth = map[0][i];
            boolean[] isv = new boolean[N];

            boolean flag = true;

            loop: while (pointer<N){
                int curDepth = map[pointer][i];
                if(depth == curDepth){
                    pointer++;
                }else if(depth - curDepth == 1){ // 낮아진 경우
                    for (int j = 0; j < L; j++) {
                        if(pointer+j>N-1 || map[pointer+j][i] != curDepth){
                            flag = false;
                            break loop;
                        }
                        isv[pointer+j] = true;
                    }
                    depth = curDepth;
                    pointer+=L;
                } else if (curDepth - depth == 1) { // 높아진 경우
                    for (int j = 0; j < L; j++) {
                        if(pointer-1-j<0 || map[pointer-1-j][i] != depth || isv[pointer-1-j]){
                            flag = false;
                            break loop;
                        }
                    }
                    depth = curDepth;
                }else{
                    flag = false;
                    break;
                }
            }

            if (flag){
                ans++;
            }
        }


        System.out.println(ans);
    }
}