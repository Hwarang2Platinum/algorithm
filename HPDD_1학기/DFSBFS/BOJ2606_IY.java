import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//7
//6
//1 2
//2 3
//1 5
//5 2
//5 6
//4 7

public class BOJ2606_IY {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] graph;
    static boolean[] visited;
    static int n; // 컴퓨터 개수
    static int answer; // 정답 : 감염된 컴퓨터 개수

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];
        visited = new boolean[n+1];

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;
        }

        dfs(1);
        System.out.println(answer-1); // 처음꺼 제외
        br.close();
    }
    public static void dfs(int v) {
        visited[v] = true;
        answer++;

        for(int i=0; i<graph[v].length; i++) {
            if(graph[v][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}
