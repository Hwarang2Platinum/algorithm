
/**
 * B1238_파티_오화랑
 * 1. 특정 장소에서 Part 장소까지 (PQ를 활용한 BFS)
 * 2. Party 장소에서 다른 장소로의 경로 (Dijkstra)
 * => 어떻게 시간상으로 추가적인 최적화를 할 수 있을까?? ( 좀 해보자 )
 */
import java.io.*;
import java.util.*;

public class B1238_파티_오화랑_최적화요구 {

    static class node {
        int to, cost;

        public node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

    }

    static ArrayList<ArrayList<node>> graph;
    static int[] dist;
    static int MAX = 100_001;
    static int MAX_total = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(N + 1);
        dist = new int[N + 1];

        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        int from, to, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new node(to, cost));
        }
        Arrays.fill(dist, MAX);
        dijkstra(N, P);

        PriorityQueue<node> PQ;
        boolean[] visited;
        node temp;
        for (int i = 1; i <= N; i++) {
            if (i == P)
                continue;
            PQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            visited = new boolean[N + 1];

            PQ.offer(new node(i, 0));
            while (!PQ.isEmpty()) {
                temp = PQ.poll();
                if (temp.to == P) {
                    MAX_total = Math.max(MAX_total, temp.cost + dist[i]);
                    break;
                }
                if (visited[temp.to])
                    continue;
                visited[temp.to] = true;
                for (node each : graph.get(temp.to))
                    PQ.offer(new node(each.to, temp.cost + each.cost));
            }
        }
        System.out.println(MAX_total);
    }

    public static void dijkstra(int N, int P) {
        PriorityQueue<node> PQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        boolean[] visited = new boolean[N + 1];

        dist[P] = 0;
        PQ.offer(new node(P, dist[P]));

        node temp;
        while (!PQ.isEmpty()) {
            temp = PQ.poll();
            if (visited[temp.to])
                continue;
            visited[temp.to] = true;
            for (node each : graph.get(temp.to)) {
                if (dist[each.to] < dist[temp.to] + each.cost) // dist[v] < dist[u] + cost(e -> v)
                    continue;
                dist[each.to] = dist[temp.to] + each.cost;
                PQ.offer(new node(each.to, dist[each.to]));
            }
        }
    }
}