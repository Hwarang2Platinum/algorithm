import java.util.*;
import java.io.*;

public class B23326_홍익투어리스트_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        TreeSet<Integer> ts = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            if (st.nextToken().charAt(0) == '1') {
                ts.add(i);
            }
        }
        int curr = 0;

        for (int t = 0; t < Q; t++) {
            st = new StringTokenizer(br.readLine());
            char command = st.nextToken().charAt(0);

            if (command == '1') {
                int n = Integer.parseInt(st.nextToken()) - 1;
                if (ts.contains(n)) {
                    ts.remove(n);
                } else {
                    ts.add(n);
                }
            } else if (command == '2') {
                int n = Integer.parseInt(st.nextToken()) ;
                curr = (curr + n) % N;
            } else {
                if (ts.isEmpty()) {
                    sb.append(-1).append("\n");
                    continue;
                }

                int stay = ts.contains(curr) ? 0 : Integer.MAX_VALUE;
                int go = ts.higher(curr) != null ? ts.higher(curr) - curr : Integer.MAX_VALUE;
                int jump = ts.higher(-1) + N - curr;

                sb.append(Math.min(stay, Math.min(go, jump))).append("\n");
            }
        }
        System.out.println(sb);
    }
}