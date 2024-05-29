import java.io.*;
import java.util.*;

/**
 * B1967_트리의지름_김종호
 */
public class Main {

    static int N, max, maxnode;
    static boolean visit[];

    static List<Data>[] graph;

    class Data {
        int node, weight;

        Data(int n, int w) {
            this.node = n;
            this.weight = w;
        }
    }

    public void dfs(int node, int count) {
        if (count > max) {
            max = Math.max(max, count);
            maxnode = node;
        }
        for (Data data : graph[node]) {
            int next = data.node;
            int weight = data.weight;
            if (!visit[next]) {
                visit[next] = true;
                dfs(next, count + weight);
            }
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        visit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Data(e, w));
            graph[e].add(new Data(s, w));
        }
        visit[1] = true;
        // 루트에서 가장 깊이가 깊은 노드 찾기
        dfs(1, 0);
        visit = new boolean[N + 1];
        max = 0;
        visit[maxnode] = true;
        // 찾은 노드를 루트로 해서 가장 깊이가 깊은 노드 찾
        dfs(maxnode, 0);
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}