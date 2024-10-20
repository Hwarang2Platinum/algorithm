import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int  from, to;
        int limit;

        public Edge(int from, int to, int limit) {
            this.from = from;
            this.to = to;
            this.limit = limit;
        }
    }
    /**
     * 13905 세부 : 금빼빼로는 무게 1, 숭이는 금빼빼로보다 가벼움. : 크루스칼
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // 숭이
        int e = Integer.parseInt(st.nextToken()); // 혜빈

        List<Edge> list = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken()); // 집 번호1
            int h2 = Integer.parseInt(st.nextToken()); // 집 번호2
            int k = Integer.parseInt(st.nextToken()); // 무게제한
            list.add(new Edge(h1, h2, k));
        }

        System.out.println(kruskal(list, s, e, N, M));
    }

    private static int kruskal(
        List<Edge> list,
        int start,
        int end,
        int n,
        int m
    ) {
        int[] parent = new int[n+1];

        for(int i = 1; i < n+1; i++) {
            parent[i] = i;
        }

        Collections.sort(list, ((o1, o2) -> o2.limit - o1.limit));

        for(Edge edge : list) {
            if(union(parent, edge.from, edge.to)) {
                if(find(parent, start) == find(parent, end)) {
                    return edge.limit;
                }
            }
        }
        return 0;
    }
    private static int find(int[] parent, int v) {
        if(v == parent[v]) return v;
        else return parent[v] = find(parent, parent[v]);
    }

    private static boolean union(int[] parent, int a, int b) {
        int aRoot = find(parent, a);
        int bRoot = find(parent, b);
        if(aRoot == bRoot) return false;
        parent[aRoot] = bRoot;
        return true;
    }
}
