import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M, X;
    static int[] dist, ans;
    static List<Data>[] graph;

    /**
     * Data
     */
    public class Data implements Comparable<Data> {
        int node, weight;

        Data(int n, int w) {
            node = n;
            weight = w;
        }

        public int compareTo(Data d) {
            return Integer.compare(weight, d.weight);
        }

    }

    // 시작점 ~ 도착점의 거리를 반환하는 다익스트라
    public void dijk(int start) {
        dist = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dist[i] = 10000000;
        }
        dist[start] = 0;
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.add(new Data(start, 0));
        while (!pq.isEmpty()) {
            Data data = pq.poll();
            int node = data.node;
            int weight = data.weight;
            if (dist[node] < weight)
                continue;
            for (Data next : graph[node]) {
                int nextNode = next.node;
                int nextWeight = next.weight;
                if (weight + nextWeight < dist[nextNode]) {
                    dist[nextNode] = weight + nextWeight;
                    pq.add(new Data(nextNode, weight + nextWeight));
                }
            }
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        ans = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Data(e, w));
        }
        dijk(X);
        ans = dist.clone();
        for (int i = 1; i <= N; i++) {
            if (i != X) {
                dijk(i);
                ans[i] += dist[X];
            }
        }
        int ret = 0;
        for (int i = 1; i <= N; i++) {
            ret = Math.max(ret, ans[i]);
        }
        System.out.println(ret);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}