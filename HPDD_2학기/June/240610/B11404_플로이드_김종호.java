import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N, M;
    static int[][] graph;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], 10000000);
        }
        for (int i = 0; i < M; i++) {
            int s, e, c;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
            graph[s][e] = Math.min(graph[s][e], c);
        }

        for (int m = 0; m < N; m++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j)
                        graph[i][j] = Math.min(graph[i][j], graph[i][m] + graph[m][j]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(graph[i][j] == 10000000 ? 0 : graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}