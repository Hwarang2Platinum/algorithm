import java.io.*;
import java.util.*;

// MST문제 ... 다익스트라로 풀려다가 실패함.
public class Main {
    static class Edge implements Comparable<Edge>{
        int from, to, dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }

    }
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[] schools = new char[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N ; i++) {
            schools[i] = st.nextToken().charAt(0);
        }

        parent = new int[N+1];
        List<Edge> edgeList = new ArrayList();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            
            // 같은 유형의 대학끼리는 도로도 깔지 말아라
            if(schools[from]==schools[to]) continue;

            edgeList.add(new Edge(from, to, dist));
        }

        // 간선리스트 dist기준 정렬 후 작은 것 부터 보면서 현재 그래프에 속하지 않는 애만 더하자.
        Collections.sort(edgeList);
        for (int i = 0; i < N + 1; i++) {
            parent[i] = -1;
        }
        int answer = 0;
        boolean[] visited = new boolean[N+1];
        for (Edge edge : edgeList){
            if(union(edge.from, edge.to)){
                visited[edge.from] = true;
                visited[edge.to] = true;
                answer += edge.dist;
            }
        }

        // visitied로 안들린 대학교 있는지 검사
        for (int i = 1 ; i < N+1 ; i++) {
            if(!visited[i]){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(answer);
    }

    static boolean union(int x, int y){
        int root_x = find(x);
        int root_y = find(y);
        if(root_x == root_y) return false;

        if(parent[root_x]<parent[root_y]){
            parent[root_x] += parent[root_y];
            parent[root_y] = root_x;
        }else{
            parent[root_y] += parent[root_x];
            parent[root_x] = root_y;
        }
        return true;
    }

    static int find(int x){
        if(parent[x] < 0){
            return x;
        }else{
            return find(parent[x]);
        }
    }

}