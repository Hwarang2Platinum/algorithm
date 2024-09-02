import java.io.*;
import java.util.*;

public class B14621_나만안되는연애_이찬민 { // 프림 까먹어서 찾아보고 함 ㅠ
    static boolean visited[];
    static int count = 0;
    static int N,M;
    static ArrayList<Edge>[] graph;
    static long result = 0;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학교수
        M = Integer.parseInt(st.nextToken()); // 도로 수
        String school[] = new String[N];
        boolean check = false;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            school[i] = st.nextToken();
        }

        graph = new ArrayList[N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());

            if(!school[a].equals(school[b])) { // 게이 컷
                graph[a].add(new Edge(b, c));
                graph[b].add(new Edge(a, c));
            }
        }

        prim();

        for(int i = 0; i < N; i++) {
            if(!visited[i]) { //연결 안된 곳 있으면
                check = true;
                break;
            }
        }

        if(check) System.out.println(-1);
        else System.out.println(result);

    }

    static void prim() {
        pq.add(new Edge(0,0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(visited[cur.node]) continue;

            visited[cur.node] = true;
            result += cur.weight;

            for(Edge next : graph[cur.node]) {
                if(!visited[next.node]) {
                    pq.offer(next);
                }
            }

            if(++count == N) {
                break;
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        Edge(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

    }

}