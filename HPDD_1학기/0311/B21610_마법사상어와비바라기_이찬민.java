import java.io.*;
import java.util.*;
public class B21610_마법사상어와비바라기_이찬민 {
    static int N, M;
    static final int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] basket = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                basket[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] cloud = new boolean[N][N];
        cloud[N-1][0] = cloud[N-1][1] = cloud[N-2][0] = cloud[N-2][1] = true;

        for (int move = 0; move < M; move++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            cloud = rainism(cloud, basket, d, s);
        }

        int sum=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum+= basket[i][j];
            }
        }
        System.out.println(sum);
    }



    public static boolean[][] rainism(boolean[][] cloud, int[][] basket, int dir, int dist) {

        boolean[][] newBasket = moveCloud(cloud, basket, dir, dist);
        waterCopyBug(basket, newBasket);
        createCloud(basket, newBasket);

        return newBasket;
    }

    public static void createCloud(int[][] baskets, boolean[][] newBasket) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newBasket[i][j]) newBasket[i][j] = false; //다시생성가능

                else if (!newBasket[i][j] && baskets[i][j] >= 2) {
                    newBasket[i][j] = true; //다음에 생성불가
                    baskets[i][j] -= 2;
                }
            }
        }
    }

    public static void waterCopyBug(int[][] baskets, boolean[][] newBasket) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newBasket[i][j]) {
                    int count = 0;

                    for (int dir = 2; dir <= 8; dir += 2) {
                        int nextRow = i + dx[dir];
                        int nextCol = j + dy[dir];

                        if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N && baskets[nextRow][nextCol] > 0)
                            count++; //대각선꺼 갯수세기
                    }

                    baskets[i][j] += count;
                }
            }
        }
    }

    public static boolean[][] moveCloud(boolean[][] cloud, int[][] baskets, int dir, int dist) {
        boolean[][] nextCloud = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j]) {
                    int nx = i + (dx[dir] * dist);
                    int ny = j + (dy[dir] * dist);

                    if (nx >= N) nx %= N; //연결되어있음
                    else if (nx < 0) {
                        nx = (nx + 1) % N + N - 1;
                    }

                    if (ny >= N) ny %= N;
                    else if (ny < 0) {
                        ny = (ny + 1) % N + N - 1;
                    }

                    nextCloud[nx][ny] = true;

                    baskets[nx][ny] += 1;
                }
            }
        }
        return nextCloud;
    }

}
