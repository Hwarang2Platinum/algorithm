import java.io.*;
import java.util.*;
public class B14502_연구소_이찬민 {

    static int N,M,result;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j]==1||arr[i][j]==2)continue;
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                }

                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < M; b++) {
                        if(arr[a][b]==1||arr[a][b]==2)continue;
                        if (a < i) {
                            continue;
                        } else if (a == i) {
                            if (b <= j) {
                                continue;
                            }
                        }
                        if (arr[a][b] == 0) {
                            arr[a][b] = 1;
                        }

                        for (int x = 0; x < N; x++) {
                            for (int y = 0; y < M; y++) {
                                if(arr[x][y]==1||arr[x][y]==2)continue;
                                if (x < a) {
                                    continue;
                                } else if (x == a) {
                                    if (y <= b) {
                                        continue;
                                    }
                                }
                                if (arr[x][y] == 0) {
                                    arr[x][y] = 1;
                                    check();
                                    arr[x][y] = 0;
                                }

                            }
                        }

                        arr[a][b] = 0;
                    }
                }

                arr[i][j] = 0;
            }
        }

        System.out.println(result);


    }

    public static void check() {
        int[][] brr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                brr[i][j] = arr[i][j];
            }
        }
        ArrayDeque<Point> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (brr[i][j] == 2) {
                    dq.add(new Point(i, j));
                }
            }
        }

        while (!dq.isEmpty()) {
            Point cur = dq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if (brr[nx][ny] == 0) {
                    brr[nx][ny] = 2;
                    dq.add(new Point(nx, ny));
                }
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(brr[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        int temp =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (brr[i][j] == 0) {
                    temp++;
                }
            }
        }
        result = Math.max(result, temp);

    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
