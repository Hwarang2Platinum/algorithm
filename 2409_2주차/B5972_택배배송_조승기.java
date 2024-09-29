import java.util.*;
import java.io.*;

public class B5972_택배배송_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Pair>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            adj[a].add(new Pair(b, w));
            adj[b].add(new Pair(a, w));
        }

        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(0, 0));

        int[] isv = new int[N];
        Arrays.fill(isv, Integer.MAX_VALUE);
        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (isv[p.to] < p.w) {
                continue;
            }
            isv[p.to] = p.w;

            for (int i = 0; i < adj[p.to].size(); i++) {
                Pair to = adj[p.to].get(i);
                int w = p.w + to.w;
                if (isv[to.to] <= w) continue;
                isv[to.to] = w;
                q.add(new Pair(to.to, w));
            }
        }
        System.out.println(isv[N - 1]);
    }

    static class Pair implements Comparable<Pair> {
        int to, w;

        public Pair(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(w, o.w);
        }
    }
}
