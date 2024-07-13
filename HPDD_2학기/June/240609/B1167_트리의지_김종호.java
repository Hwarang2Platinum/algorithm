import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, ans, maxNode;
    static List<Pair>[] graph;
    static boolean visit[];

    /**
     * Pair
     */
    public class Pair {
        int node, weight;

        Pair(int n, int w) {
            node = n;
            weight = w;
        }
    }

    public void dfs(int node, int sum) {
        visit[node] = true;
        if (sum > ans) {
            ans = sum;
            maxNode = node;
        }
        for (Pair p : graph[node]) {
            int next = p.node;
            if (visit[next])
                continue;
            visit[next] = true;
            dfs(next, sum + p.weight);
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new List[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken()) - 1;
            graph[cur] = new ArrayList();
            while (true) {
                int node = Integer.parseInt(st.nextToken());
                if (node == -1)
                    break;
                int weight = Integer.parseInt(st.nextToken());
                graph[cur].add(new Pair(node - 1, weight));
            }
        }
        visit = new boolean[N];
        dfs(0, 0);
        // System.out.println(ans + " " + maxNode);
        visit = new boolean[N];
        ans = 0;
        dfs(maxNode, 0);
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}