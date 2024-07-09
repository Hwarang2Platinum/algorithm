import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M, ans;
    static boolean[] isMan, isVisit;
    static List<Data>[] graph;

    class Data implements Comparable<Data> {
        int node, weight;

        Data(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Data d) {
            return Integer.compare(weight, d.weight);
        }
    }

    public void prim() {
        PriorityQueue<Data> pq = new PriorityQueue();
        pq.add(new Data(1, 0));
        while (!pq.isEmpty()) {
            Data data = pq.poll();
            int cur = data.node;
            if (isVisit[cur])
                continue;
            isVisit[cur] = true;
            ans += data.weight;
            for (Data next : graph[cur]) {
                if (!isVisit[next.node]) {
                    pq.add(next);
                }
            }
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isMan = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        isVisit = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList();
            if (st.nextToken().charAt(0) == 'M')
                isMan[i] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // 남녀 정보가 다른 노드들만 연결정보를 넣는다.
            if (isMan[s] != isMan[e]) {
                graph[s].add(new Data(e, w));
                graph[e].add(new Data(s, w));
            }
        }
        // 최소 스패닝 트리
        prim();
        for (int i = 1; i <= N; i++) {
            if (!isVisit[i]) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}