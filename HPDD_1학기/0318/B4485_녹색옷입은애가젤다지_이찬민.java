import java.io.*;
import java.util.*;
public class B4485_녹색옷입은애가젤다지_이찬민 {

    static int N;
    static int[][] arr, dist;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt =1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();

            sb.append("Problem " + cnt + ": " + dist[N - 1][N - 1]+"\n");
//            System.out.println("Problem "+cnt+":"+dist[N-1][N-1]);
            cnt++;
        }

        System.out.println(sb);


    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,arr[0][0]));
        dist[0][0] = arr[0][0];

        boolean[][] visit = new boolean[N][N];
        visit[0][0] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(cur.weight>= dist[nx][ny]) continue;
                if (dist[cur.x][cur.y] + arr[nx][ny] < dist[nx][ny] && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    dist[nx][ny] = cur.weight + arr[nx][ny];
                    pq.add(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    public static void printArr(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }
    }
}
