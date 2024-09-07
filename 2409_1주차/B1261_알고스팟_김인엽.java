import java.util.*;
import java.io.*;

public class B1261_알고스팟_김인엽 {
    static int N, M;
    static int[][] arr;
    static int[][] dist;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];
        dist = new int[N+1][M+1];

        // 입력받기
        for(int i = 1; i <= N; i++) {
            String line = br.readLine();
            for(int j = 1; j <= M; j++) {
                arr[i][j] = line.charAt(j-1) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dist[N][M]);
    }

    static void bfs() {
        // 벽 개수 로 오름차순
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> a.count - b.count
        );
        pq.add(new Node(1, 1, 0));
        dist[1][1] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int cnt = cur.count;

            if(x == N && y == M) return;

            for(int i = 0; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];

                if(!isInArea(cx, cy)) continue;
                // 새로운 벽 개수
                int newCnt = cnt + arr[cx][cy];
                if(newCnt < dist[cx][cy]) { // 더 적은 벽 개수로 갈 수 있으면 갈아끼기
                    dist[cx][cy] = newCnt;
                    pq.add(new Node(cx, cy, newCnt));
                }
            }
        }
    }

    static boolean isInArea(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= M;
    }

    static class Node {
        int x, y, count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
