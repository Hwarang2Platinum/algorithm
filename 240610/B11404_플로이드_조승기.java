import java.util.*;
import java.io.*;

public class B11404_플로이드_조승기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] li = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(li[i], 123456789);
            li[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            li[a][b] = Math.min(w, li[a][b]);
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    li[i][j] = Math.min(li[i][k] + li[k][j], li[i][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((li[i][j] >= 123456789 ? 0 : li[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
