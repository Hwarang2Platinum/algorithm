import java.util.*;
import java.io.*;

public class B13418_학교탐방하기_조승기 {
    static int N, M;
    static ArrayList<Pair>[] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken()) + 1;

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int isUp = Integer.parseInt(st.nextToken()) ^ 1;

            adj[a].add(new Pair(b, isUp));
            adj[b].add(new Pair(a, isUp));
        }

        PriorityQueue<Pair> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
        PriorityQueue<Pair> rq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.w, o1.w));

        System.out.println((find(rq) - find(q)));
    }

    static int find(PriorityQueue<Pair> q) {
        q.add(new Pair(0, 0));
        int ret = 0;
        int vc = 0;
        boolean[] isv = new boolean[N];

        while (vc != N) {
            Pair p = q.poll();
            if (isv[p.n]) continue;
            isv[p.n] = true;
            vc++;
            ret += p.w;
            for (int i = 0; i < adj[p.n].size(); i++) {
                Pair to = adj[p.n].get(i);
                if (isv[to.n]) continue;
                q.add(to);
            }
        }

        return ret * ret;
    }

    static class Pair {
        int n, w;
        public Pair(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}
