import java.io.*;
import java.util.*;
public class B16234_인구이동_이찬민 { // 화성이동급 시간복잡도
    //294304kb	4176ms
    static int N,L,R,Team,time;
    static int[] supplyDepot,land;
    static int[][] arr,visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isDone = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        time=0;
        supplyDepot = new int[N*N + 1];
        land = new int[N*N + 1];
        while (true) {
            Team = 1;
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0) {
                        checkDiff(i, j); //visited에 팀 배정
                        Team++;
//                        System.out.println(Team+"팀 ");
//                        printVisit();
                        if(Team==N*N+1){
                            isDone = true;
                        }
                    }
                }
            }
            if(isDone)break;
            move(); //화성 가즈아ㅏㅏㅏ
            time++;
        }
        System.out.println(time);

    }

    public static void move() {
        for (int t = 1; t <= Team - 1; t++) {
//            int supplyDepot =0;
//            int land =0;
//
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (visited[i][j] == t) {
//                        supplyDepot += arr[i][j]; //인구수
//                        land++; //나라수
//                    }
//                }
//            }

            int newPeople = supplyDepot[t] / land[t];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == t) {
                        arr[i][j] = newPeople;
                    }
                }
            }
        }
    }

    public static void checkDiff(int x, int y) {
        ArrayDeque<Point> dq = new ArrayDeque<>();
        dq.add(new Point(x, y));

        visited[x][y] = Team;
        supplyDepot[Team]=arr[x][y];
        land[Team]=1;
        while (!dq.isEmpty()) {
            Point cur = dq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=N)continue;

                int diff = Math.abs(arr[nx][ny]-arr[cur.x][cur.y]); //차이
                if (diff >= L && diff <= R && visited[nx][ny]==0) { //조건만족하고 아직 팀이 없는나라
                    visited[nx][ny] = Team;
                    supplyDepot[Team] += arr[nx][ny];
                    land[Team]++;
                    dq.add(new Point(nx, ny));
                }

            }
        }
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void printArr() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void printVisit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visited[i][j]+" ");
            }
            System.out.println();
        }
    }
}
