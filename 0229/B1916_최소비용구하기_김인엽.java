import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1916_최소비용구하기_김인엽 {
  static class Node {
    int to, weight;
    Node next;

    public Node(int to, int weight, Node next) {
      this.to = to;
      this.weight = weight;
      this.next = next;
    }

    @Override
    public String toString() {
      return "Node{" +
          "to=" + to +
          ", weight=" + weight +
          ", next=" + next +
          '}';
    }
  }

  static class Route implements Comparable<Route> {
    long distance;
    int node;

    public Route(long distance, int node) {
      this.distance = distance;
      this.node = node;
    }

    @Override
    public int compareTo(Route o) {
      return Long.compare(this.distance, o.distance);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    Node[] list = new Node[N+1]; // 도시 번호가 1부터니까

    StringTokenizer st;
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      // 인접리스트 만들기
      list[from] = new Node(to, weight, list[from]);
    }

    st = new StringTokenizer(br.readLine());
    // 출발 도시
    int start = Integer.parseInt(st.nextToken());
    // 도착 도시
    int end = Integer.parseInt(st.nextToken());

    // 다익스트라 시작
    long[] distances = new long[N+1]; // 최단 거리 저장 배열

    final long INF = Integer.MAX_VALUE;
    Arrays.fill(distances, INF);
    distances[start] = 0; // 시작도시는 0으로 초기화
    PriorityQueue<Route> pq = new PriorityQueue<>();
    pq.offer(new Route(0, start)); // 시작도시 pq에 넣기

    while(!pq.isEmpty()) {
      Route route = pq.poll();

      if(distances[route.node] < route.distance) continue; // 이미 확인한 정점이면 패스

      // 인접 정점들 탐색
      for(Node tmp = list[route.node]; tmp != null; tmp = tmp.next) {
        long cost = route.distance + tmp.weight;
        // 만약 더 짧은 경로라면 교체
        if(cost < distances[tmp.to]) {
          distances[tmp.to] = cost;
          pq.offer(new Route(cost, tmp.to));
        }
      }
    }

    System.out.println(distances[end]);
  }


}
