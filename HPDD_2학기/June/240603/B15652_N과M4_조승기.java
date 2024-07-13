import java.util.*;
import java.io.*;
public class B15652_N과M4_조승기 {
    static int N, K;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    static void find(int n, int m) {
        if (m == K) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = n; i <= N; i++) {
            arr[m] = i;
            find(i, m + 1);
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];
        find(1, 0);
        System.out.println(sb);
    }
}
