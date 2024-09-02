import java.io.*;
import java.util.*;

public class B1043_거짓말_조승기 {
    static int N, M;
    static int[] uf;

    static int find(int n) {
        if (uf[n] < 0)
            return n;
        return uf[n] = find(uf[n]);
    }
    static void merge(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            uf[a] = b;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        uf = new int[N];
        Arrays.fill(uf, -1);
        HashSet<Integer>[] parties = new HashSet[M];
        boolean[] truemans = new boolean[N];

        st = new StringTokenizer(br.readLine());
        HashSet<Integer> trueman = new HashSet<>();
        for (int i = 0, e = Integer.parseInt(st.nextToken()); i < e; i++) {
            int elem = Integer.parseInt(st.nextToken()) - 1;
            trueman.add(elem);
            truemans[elem] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            parties[i] = new HashSet<>();
            int a = Integer.parseInt(st.nextToken())-1;
            parties[i].add(a);
            for (int j = 0; j < e-1; j++) {
                int t = Integer.parseInt(st.nextToken())-1;
                merge(a, t);
            }
        }

        int ans = 0;

        for (int tm: trueman) {
            truemans[find(tm)] = true;
        }

        for (int i = 0; i < M; i++) {
            boolean isFail = false;

            for (int p: parties[i]) {
                isFail |= truemans[find(p)];
            }
            ans += isFail ? 0 : 1;
        }
        System.out.println(ans);
    }
}
