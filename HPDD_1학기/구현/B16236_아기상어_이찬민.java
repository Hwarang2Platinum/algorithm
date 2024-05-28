import java.io.*;
import java.util.*;
public class B16236_아기상어_이찬민 {
    static int N, time, level,eat;
    static int nowX, nowY;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        time =0;
        level = 2;
        eat = 0;
        findMe(); // 현재 내 위치찾기

        while (true) {
            PriorityQueue<Point> q = new PriorityQueue<>();
            q.add(new Point(nowX, nowY, 0));// 현재 x,y, 이동거리
            visit = new boolean[N][N];
            visit[nowX][nowY] = true;
            boolean canEat = false;

            while (!q.isEmpty()) { //먹이 찾기
                Point point = q.poll();

                if (arr[point.x][point.y] < level && arr[point.x][point.y] != 0) {// 먹을거
                    arr[point.x][point.y] = 0;
                    time += point.dist;
                    canEat = true;
                    eat++;
                    nowX = point.x;
                    nowY = point.y;
                    break;// 한번 먹음
                }

                for (int i = 0; i < 4; i++) {
                    int nx = point.x + dx[i];
                    int ny = point.y + dy[i];

                    if(nx<0 || ny<0 || nx>=N || ny>=N)continue;
                    if(arr[nx][ny] > level)continue;
                    if (visit[nx][ny])continue;//안가보고 지나갈수잇는곳

                    q.add(new Point(nx, ny, point.dist + 1));
                    visit[nx][ny] = true;
                }

            }
            if (canEat == false) { //엄마아아아!
                break;
            }
            if (level == eat) { //성장
                level++;
                eat=0;
            }

        }


        System.out.println(time);


    }
    public static class Point implements Comparable<Point>{
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }
    public static void findMe() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 9) {
                    nowX = i;
                    nowY = j;
                    arr[i][j] = 0; //내위치 지워버려
                }
            }
        }
    }
}
