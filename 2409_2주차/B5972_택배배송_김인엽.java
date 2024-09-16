import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<List<Node>> graph = new ArrayList<>();

    static int[] distances;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distances = new int[N+1];

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 노드
            int b = Integer.parseInt(st.nextToken()); // 끝 노드
            int dist = Integer.parseInt(st.nextToken()); // 비용

            // 양방향 간선
            graph.get(a).add(new Node(b, dist));
            graph.get(b).add(new Node(a, dist));
        }

        // 다익스트라
        dijkstra(1);

        System.out.println(distances[N]);
    }

    static void dijkstra(int node) {
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(node, 0));
        distances[node] = 0;

        while(!pq.isEmpty()) {
            Node temp = pq.poll();

            if(distances[temp.to] < temp.dist) continue; // 이미 한건 무시

            for (Node next : graph.get(temp.to)) {
                int cost = distances[temp.to] + next.dist;
                if (cost < distances[next.to]) {
                    distances[next.to] = cost;
                    pq.add(new Node(next.to, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        int dist;

        public Node(
            int to,
            int dist
        ) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist; // 오름차순
        }
    }
}