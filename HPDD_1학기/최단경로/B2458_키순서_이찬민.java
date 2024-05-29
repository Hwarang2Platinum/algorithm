import java.io.*;
import java.util.*;
public class B2458_키순서_이찬민 { // 답지봄
    static int N,M;
    static boolean[][] visit;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            visit[a][b] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (visit[i][k] && visit[k][j]) { // 연결되어있는지 체크(갈수있는지)
                        visit[i][j] = true; //어떻게든 갈수 잇으면 true
                    }
                }
            }
        }

        int[] count = new int[N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visit[i][j] || visit[j][i])count[i]++; //내가 갈수있는곳 or 나한테 올수 있는곳
            }
        }
        int result =0;
        for (int x : count) {
            if (x == N-1) { //자기 빼고 N-1개 전부 연결되어있다면 자기 위치 파악가능
                result++;
            }
        }
        System.out.println(result);
    }
}
