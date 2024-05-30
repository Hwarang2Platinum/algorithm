import java.util.*;
import java.io.*;

public class B14621_나만안되는연애_조승기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[] ism = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ism[i] = st.nextToken().charAt(0) == 'M';
        }
        ArrayList<Pair>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            if (ism[a] == ism[b]) continue;
            adj[a].add(new Pair(b, w));
            adj[b].add(new Pair(a, w));
        }

        int[] isv = new int[N];
        Arrays.fill(isv, -1);

        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(0, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (isv[p.a] != -1) continue;
            isv[p.a] = p.w;

            for (int i = 0; i < adj[p.a].size(); i++) {
                if (isv[adj[p.a].get(i).a] != -1) continue;
                q.add(adj[p.a].get(i));
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (isv[i] == -1) {
                System.out.println(-1);
                return;
            }
            ans += isv[i];
        }
        System.out.println(ans);
    }
    static class Pair implements Comparable<Pair> {
        int a, w;
        public Pair(int a, int w) {
            this.a = a;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(w, o.w);
        }
    }
}
