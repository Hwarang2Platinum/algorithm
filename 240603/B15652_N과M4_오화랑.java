import java.io.*;
import java.util.*;

// 중복 증가 수열?!

public class B15652_N과M4_오화랑 {
    static int[] each;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 ~ N 까지의 수 에서
        int M = Integer.parseInt(st.nextToken()); // 몇 개를 추출할 것인가?
        each = new int[M];
        duplicatePermute(1, 0, N, M);
        System.out.println(sb);
    }

    public static void duplicatePermute(int curr, int cnt, int N, int M) {
        if (cnt == M) {
            for (int num : each)
                sb.append(num).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = curr; i <= N; i++) {
            each[cnt] = i;
            duplicatePermute(curr, cnt + 1, N, M);
            curr++; // Next 증가 수열 관리
        }
    }
}