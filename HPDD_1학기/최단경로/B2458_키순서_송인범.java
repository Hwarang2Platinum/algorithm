package feburary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2458_키순서_송인범 {
	// 플로이드 워셜을 사용하면 된다.
	// 문제는 모든 부분이 연결되어 자신이 어떤 순서인지 알면 된다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];
        // 그래프 노드 비용 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], 999999999);
            graph[i][i] = 0;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }

        // Using Floyd-Warshall
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                	// 간선이 주어지지 않아 1로 결정
                    if (graph[i][k] + graph[k][j] == 2) {
                        graph[i][j] = 1;
                    }
                }
            }
        }
        
        int result = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            // 자신의 순서를 알 수 있는 건  0번째 노드를 제외한 노드들을 모두 지날때 알 수 있다.
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] != 999999999 || graph[j][i] != 999999999) {
                    count++;
                }
            }
            if (count == N ) {
                result++;
            }
        }

        System.out.println(result);
    }
}
