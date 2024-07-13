import java.util.*;
import java.io.*;

public class B1238_파티_조승기 {
    static int N, X;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken())-1;

        ArrayList<Pair>[] adj = new ArrayList[N];
        ArrayList<Pair>[] eadj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
            eadj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adj[s].add(new Pair(e, w));
            eadj[e].add(new Pair(s, w));
        }
        int[] a = dij(adj);
        int[] b = dij(eadj);
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (max < a[i] + b[i]) {
                max = a[i] + b[i];
            }
        }
        System.out.println(max);
    }

    static int[] dij(ArrayList<Pair>[] adj) {
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(X, 0));
        int[] dis = new int[N];
        Arrays.fill(dis, 1111111111);
        dis[X] = 0;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (dis[p.n] < p.w) continue;
            for (int i = 0; i < adj[p.n].size(); i++) {
                int to = adj[p.n].get(i).n;
                int w = adj[p.n].get(i).w;

                if (dis[to] > p.w + w) {
                    q.add(new Pair(to, p.w + w));
                    dis[to] = p.w + w;
                }
            }
        }
        return dis;
    }

    static class Pair implements Comparable<Pair> {
        int n, w;
        public Pair(int n, int w) {
            this.n = n;
            this.w = w;
        }
        @Override
        public int compareTo(Pair o) {
            return Integer.compare(w, o.w);
        }
    }
}
