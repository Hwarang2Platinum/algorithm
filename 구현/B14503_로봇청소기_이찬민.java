import java.io.*;
import java.util.*;

public class B14503_로봇청소기_이찬민 {
    static int N,M;
    static int x,y,dir;
    static int[][] arr,visit;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; //북동남서
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 1; //맨처음 한칸은 청소완료
        clean(x,y,dir);
        System.out.println(result);

    }

    private static void clean(int x, int y, int dir) {
        visit[x][y] = 1; //청소표시
        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4; //반시계방향 탐색

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (arr[nx][ny] == 0 && visit[nx][ny] != 1) { //벽 아니고 청소안된상태
                    result++;
                    clean(nx, ny, dir);
                    return;
                }
            }
        } // 주변 4칸중 청소되지 않은 칸을 못만난경우 후진
        int back_dir = (dir + 2) % 4;
        int nx = x + dx[back_dir];
        int ny = y + dy[back_dir];
        if(nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] != 1){ // 후진하다 벽 만나면 종료
            clean(nx,ny,dir); //방향 유지한채 빠꾸
            return;
        }

    }
}
