import java.util.*;
import java.io.*;

public class B1865_웜홀_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 0, T = Integer.parseInt(br.readLine()); t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            ArrayList<Pair> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Pair(s, e, w));
                edges.add(new Pair(e, s, w));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Pair(s, e, -w));
            }
            int[] dis = new int[N];
            Arrays.fill(dis, 0);

            for (int i = 0; i < N; i++) {
                for (Pair edge : edges) {
                    int s = edge.a;
                    int e = edge.b;
                    int w = edge.w;
                    if (dis[s] + w < dis[e]) dis[e] = dis[s] + w;
                }
            }

            boolean isFail = false;
            for (Pair edge : edges) {
                int s = edge.a;
                int e = edge.b;
                int w = edge.w;
                if (dis[s] + w < dis[e]) isFail = true;
            }
            if (isFail) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    static class Pair {
        int a, b, w;
        public Pair(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }
}
