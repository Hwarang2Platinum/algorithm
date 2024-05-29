import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4485_녹색옷입은애가젤다지_김인엽 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int testCase = 1;

    int[][] arr;
    int N;
    int answer;

    final int INF = 225000; // 대충 150x150x10

    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};

    while(true){
      N = Integer.parseInt(br.readLine());
      if(N == 0) break;
      answer = Integer.MAX_VALUE;
      arr = new int[N][N];
      for(int i=0; i<N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int[][] distances = new int[N][N];
      for(int i=0; i<N; i++) {
        Arrays.fill(distances[i], INF);
      }

      distances[0][0] = arr[0][0];

      PriorityQueue<Route> pq = new PriorityQueue<>();
      pq.offer(new Route(0, 0, arr[0][0]));
      while(!pq.isEmpty()) {
        Route route = pq.poll();

        if(route.dist > distances[route.x][route.y]) continue; // 이미 완성된 친구는 패스

        for(int i=0; i<dx.length; i++) {
          int cx = route.x + dx[i];
          int cy = route.y + dy[i];
          if(cx < 0 || cx >= N || cy < 0 || cy >= N) continue; // 범위체크
          int cost = route.dist + arr[cx][cy];
          if(cost < distances[cx][cy]) {
            distances[cx][cy] = cost;
            pq.offer(new Route(cx, cy, cost));
          }
        }
      }

      bw.write("Problem " + testCase++ + ": " + distances[N-1][N-1] + "\n");

      bw.flush();
    }
    bw.close();
  }

  static class Route implements Comparable<Route> {
    int x, y;
    int dist;

    public Route(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }

    @Override
    public int compareTo(Route o) {
      return this.dist - o.dist;
    }
  }
}
