import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int T, N, M, W;
    static int[][] graph;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph = new int[N + 1][N + 1];
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    graph[i][j] = 1000000;
                }
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if (graph[s][e] > w) {
                    graph[s][e] = w;
                    graph[e][s] = w;
                }
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if (graph[s][e] > -w) {
                    graph[s][e] = -w;
                }
            }
            // 플로이드 워셜
            loop: for (int m = 1; m <= N; m++) {
                for (int s = 1; s <= N; s++) {
                    for (int e = 1; e <= N; e++) {
                        graph[s][e] = Math.min(graph[s][e], graph[s][m] + graph[m][e]);
                        // 음의 사이클을 찾으면 바로 YES
                        if (graph[s][s] < 0) {
                            sb.append("YES").append("\n");
                            break loop;
                        }
                    }
                }
                if (m == N)
                    sb.append("NO").append("\n");
            }
        }
        System.out.print(sb);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}