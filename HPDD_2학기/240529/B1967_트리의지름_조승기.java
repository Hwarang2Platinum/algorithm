import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class B1967_트리의지름_조승기 {
    static ArrayList<Pair>[] adj;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            adj[a].add(new Pair(b, w));
            adj[b].add(new Pair(a, w));
        }
        System.out.println(bfs(new Pair(bfs(new Pair(0, 0)).x, 0)).y);
    }

    static Pair bfs(Pair a) {
        boolean[] isv = new boolean[adj.length];
        Queue<Pair> q = new ArrayDeque<>();
        q.add(a);
        Pair ret = a;
        isv[a.x] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.y > ret.y) {
                ret = p;
            }
            for (int i = 0; i < adj[p.x].size(); i++) {
                Pair to = adj[p.x].get(i);
                if (isv[to.x]) continue;
                q.add(new Pair(to.x, to.y + p.y));
                isv[to.x] = true;
            }
        }
        return ret;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
