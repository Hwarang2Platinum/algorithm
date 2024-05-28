import java.io.*;
import java.util.*;

public class B13424_비밀모임_이찬민 {
    static int N,M,K;
    static int[][] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dist = new int[N+1][N+1];

            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                        continue;
                    }
                    dist[i][j] = 100000000; // 거리 저장용   왜 MAXVALUE 안됨?
                }
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken()); //거리

                dist[a][b] = Math.min(dist[a][b], c);
                dist[b][a] = Math.min(dist[b][a], c);
            }

            K = Integer.parseInt(br.readLine());
            int[] friend = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                friend[i] = Integer.parseInt(st.nextToken());
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
//            for (int i = 1; i <= N; i++) {
//                for (int j = 1; j <= N; j++) {
//                    System.out.print(dist[i][j] +" ");
//                }
//                System.out.println();
//            }
            int result = Integer.MAX_VALUE;
            int resultI = 0;
            for (int i = 1; i <= N; i++) { //정점
                int temp =0;
                for (int j = 0; j < K; j++) { //칭구
                    temp += dist[i][friend[j]];
                }
                if (temp < result) { // = 없으므로 제일 작은 i 값 출력
                    result = temp;
                    resultI = i;
                }
            }
            System.out.println(resultI);


        }
    }
}
