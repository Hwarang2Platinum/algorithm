import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//4 5 1
//1 2
//1 3
//1 4
//2 4
//3 4
public class BOJ2160_IY {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] graph;
    static boolean[] visited_bfs;
    static boolean[] visited_dfs;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 노드 개수
        n = Integer.parseInt(st.nextToken());
        // 간선 개수
        m = Integer.parseInt(st.nextToken());
        // 시작 노드
        int v = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1]; // 인덱스 1부터 사용.
        visited_bfs = new boolean[n+1];
        visited_dfs = new boolean[n+1];
        // 그래프 채우기
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;
        }

        dfs(v);
        System.out.println();
        bfs(v);
        br.close();
    }

    public static void dfs(int v) {
        // 방문처리
        visited_dfs[v] = true;
        System.out.print(v + " ");

        for(int i=0; i<graph[v].length; i++) {
            // v와 연결되어있으면서, 방문하지 않은 노드 방문!
            if(graph[v][i] == 1 && !visited_dfs[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited_bfs[start] = true;

        while(!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");

            for(int i=0; i<graph[v].length; i++) {
                // v와 연결되어있으면서, 방문하지 않은 노드 큐에 추가
                if(graph[v][i] == 1 && !visited_bfs[i]) {
                    queue.add(i);
                    visited_bfs[i] = true;
                }
            }
        }
    }
}
