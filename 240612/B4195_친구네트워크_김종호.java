import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int T, N;
    static int[] p, ans;
    static Map<String, Integer> map;

    public int find(int a) {
        if (p[a] == a)
            return a;
        return (p[a] = find(p[a]));
    }

    public void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa == fb)
            return;
        p[fb] = fa;
        ans[fa] += ans[fb];
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new HashMap<>();

            p = new int[400000];
            ans = new int[400000];
            for (int i = 0; i < 400000; i++) {
                p[i] = i;
                ans[i] = 1;
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String s1, s2;
                int n1, n2;
                s1 = st.nextToken();
                s2 = st.nextToken();
                map.putIfAbsent(s1, map.size());
                map.putIfAbsent(s2, map.size());
                n1 = map.get(s1);
                n2 = map.get(s2);
                union(n1, n2);
                sb.append(ans[find(n1)]).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}