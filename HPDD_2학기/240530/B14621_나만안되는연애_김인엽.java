import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * MST인거 보고 풀었고, 구현방법도 기억안나서 싸피 때 배운 코드 보면서 했습니다,,
 * 18008	204
 */
public class B14621_나만안되는연애_김인엽 {
    static class Edge implements Comparable<Edge> {
        int from, to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static Edge[] edges; // 간선리스트
    static int[] parents; // 부모 저장
    static int N;
    static boolean[] genders; // 성별

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        genders = new boolean[N]; // 남자면 true / 여자면 false

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            genders[i] = st.nextToken().equals("M");
        }

        edges = new Edge[M]; // 간선 리스트
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }
        // 간선리스트 오름차순 정렬
        Arrays.sort(edges);

        parents = new int[N]; // 자신의 부모나 루트 저장

        // 1. make set
        make();

        // 2. 정렬된 간선 하나씩 꺼내서 신장트리 생성
        int weight = 0;
        int cnt = 0;
        for(Edge edge : edges) {
            if(isSameGender(edge)) continue; // 같은 성이면 넘어가기
            if(!union(edge.from, edge.to)) continue; // 싸이클 발생
            weight += edge.weight;
            if(++cnt == N-1) break; // N-1개 선택 완료 -> 종료
        }
        if(cnt < N-1) weight = -1; // N-1이 안될 때!
        System.out.println(weight);
    }

    private static boolean isSameGender(Edge edge) {
        return genders[edge.to] == genders[edge.from];
    }

    public static void make() {
        for(int i=0; i<N; i++) {
            parents[i] = i;
        }
    }

    public static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) return false; // 같은 트리임

        parents[yRoot] = xRoot;
        return true;
    }

}