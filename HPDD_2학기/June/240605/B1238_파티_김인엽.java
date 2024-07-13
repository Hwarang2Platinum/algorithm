import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, X;
    static ArrayList<Node>[] adj, reverseAdj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        adj = new ArrayList[N];
        reverseAdj = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
            reverseAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adj[from].add(new Node(to, weight));
            reverseAdj[to].add(new Node(from, weight)); // 역방향 그래프 생성
        }

        int[] toX = dijkstra(reverseAdj, X); // 각 노드에서 X로 가는 최단 경로
        int[] fromX = dijkstra(adj, X); // X에서 각 노드로 가는 최단 경로

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, toX[i] + fromX[i]);
        }
        System.out.println(result);
    }

    static int[] dijkstra(ArrayList<Node>[] graph, int start) {
        int[] distances = new int[N];
        Arrays.fill(distances, 1_000_000);
        distances[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distances[current.index] < current.weight) continue;

            for (Node next : graph[current.index]) {
                int cost = distances[current.index] + next.weight;
                if (cost < distances[next.index]) {
                    distances[next.index] = cost;
                    pq.add(new Node(next.index, cost));
                }
            }
        }
        return distances;
    }

    static class Node {
        int index, weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
}
