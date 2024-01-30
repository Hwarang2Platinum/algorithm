import java.io.*;
import java.util.*;

public class BOJ16926_CM {
    static int N,M,R;
    static int[][] arr;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int layer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        layer = Math.min(N,M)/2;  // N행 M열중 짧은거를 반으로 나눈 만큼 회전층이 겹쳐있다 생각

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) { // R번 돌림
            spin();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void spin() {
        for (int i = 0; i < layer; i++) {
            int a=0;
            int temp = arr[i][i]; // 모서리부분부터 시작  i 증가하면 대각선으로 이동해서 안쪽레이어 돌림
            int x = i;
            int y = i; //현재좌표
            while (a < 4) {
                int nx = x + dx[a]; //다음좌표
                int ny = y + dy[a];
                if (i <= nx && i <= ny && nx < N - i && ny < M - i) {
                    arr[x][y] = arr[nx][ny];  // 반시계방향으로 메꿔주기
                    x = nx; //다음칸이동
                    y = ny;
                } else {
                    a++; //방향전환
                }
            }
            arr[i+1][i] = temp;  //처음 모서리부분 메꿔주기
        }
    }

}
