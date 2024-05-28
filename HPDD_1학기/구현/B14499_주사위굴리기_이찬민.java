import java.io.*;
import java.util.*;
public class B14499_주사위굴리기_이찬민 {
    static int N,M,nowX,nowY;
    static int[][] arr, dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nowX = Integer.parseInt(st.nextToken());
        nowY = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dice = new int[4][3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int action = Integer.parseInt(st.nextToken());
            move(action); // 1 동 2 서 3 북 4 남
        }

    }

    public static void move(int action) {
        switch (action) {
            case 1: // 동쪽이동
                if(nowY+1 < M){
                    nowY += 1;

                    int temp= dice[3][1]; //바닥면
                    dice[3][1] = dice[1][2];
                    dice[1][2] = dice[1][1];
                    dice[1][1] = dice[1][0];
                    dice[1][0] = temp;

                    if(arr[nowX][nowY]==0){
                        arr[nowX][nowY] = dice[3][1];
                    }else{
                        dice[3][1] = arr[nowX][nowY];
                        arr[nowX][nowY] = 0;
                    }

                    System.out.println(dice[1][1]); //윗면 출력
                }
                break;
            case 2: //서쪽 이동
                if(nowY-1 >= 0){
                    nowY -= 1;

                    int temp= dice[3][1]; //바닥면
                    dice[3][1] = dice[1][0];
                    dice[1][0] = dice[1][1];
                    dice[1][1] = dice[1][2];
                    dice[1][2] = temp;

                    if(arr[nowX][nowY]==0){
                        arr[nowX][nowY] = dice[3][1];
                    }else{
                        dice[3][1] = arr[nowX][nowY];
                        arr[nowX][nowY] = 0;
                    }

                    System.out.println(dice[1][1]);
                }
                break;
            case 3: // 북쪽 이동
                if(nowX-1 >=0){
                    nowX -= 1;

                    int temp= dice[3][1]; //바닥면
                    dice[3][1] = dice[0][1];
                    dice[0][1] = dice[1][1];
                    dice[1][1] = dice[2][1];
                    dice[2][1] = temp;

                    if(arr[nowX][nowY]==0){
                        arr[nowX][nowY] = dice[3][1];
                    }else{
                        dice[3][1] = arr[nowX][nowY];
                        arr[nowX][nowY] = 0;
                    }

                    System.out.println(dice[1][1]); //윗면 출력
                }
                break;
            case 4: //남쪽 이동
                if(nowX+1 < N){
                    nowX += 1;

                    int temp= dice[3][1]; //바닥면
                    dice[3][1] = dice[2][1];
                    dice[2][1] = dice[1][1];
                    dice[1][1] = dice[0][1];
                    dice[0][1] = temp;

                    if(arr[nowX][nowY]==0){
                        arr[nowX][nowY] = dice[3][1];
                    }else{
                        dice[3][1] = arr[nowX][nowY];
                        arr[nowX][nowY] = 0;
                    }

                    System.out.println(dice[1][1]); //윗면 출력
                }
                break;
        }
    }
}
