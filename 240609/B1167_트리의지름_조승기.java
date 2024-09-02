import java.util.*;
import java.io.*;

public class B1167_트리의지름_조승기 {

    static boolean[] isv;
    static int maxi, maxw;
    static ArrayList<Pair>[] adj;

    static void dfs(Pair p) {
        if (isv[p.to]) return;
        if (p.w > maxw) {
            maxi = p.to;
            maxw = p.w;
        }
        isv[p.to] = true;

        for (int i = 0; i < adj[p.to].size(); i++) {
            Pair to = adj[p.to].get(i);
            if (isv[to.to]) continue;
            dfs(new Pair(to.to, to.w + p.w));
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;

            while(true) {
                int to = Integer.parseInt(st.nextToken())-1;
                if (to < 0) break;
                int w = Integer.parseInt(st.nextToken());
                adj[from].add(new Pair(to, w));
                adj[to].add(new Pair(from, w));
            }
        }

        isv = new boolean[V];
        dfs(new Pair(0, 0));
        isv = new boolean[V];
        dfs(new Pair(maxi, 0));
        System.out.println(maxw);
    }
    static class Pair {
        int to, w;
        public Pair(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
