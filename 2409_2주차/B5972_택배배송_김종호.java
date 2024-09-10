import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    
    static int N, M;
    static ArrayList<Data> graph[];
    static int[] visit;
    /**
     * Data
     */
    public class Data implements Comparable<Data>{
        int node, cost;

        Data(int n, int c){
            node = n;
            cost = c;
        }

        @Override
        public int compareTo(Main.Data o) {
            return Integer.compare(cost, o.cost);
        }
    
        
    }



    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        visit = new int[N + 1];
        for (int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
            visit[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++){
            int s, e, c;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[s].add(new Data(e, c));
            graph[e].add(new Data(s, c));
        }
        
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.add(new Data(1, 0));
        while (!pq.isEmpty()){
            Data d = pq.poll();
            int node = d.node;
            int cost = d.cost;
            if (visit[node] < cost)
                continue;
            visit[node] = cost;
            for (Data next : graph[node]){
                int nextCost = cost + next.cost;
                if (visit[next.node] > nextCost){
                    pq.add(new Data(next.node, nextCost));
                    visit[next.node] = nextCost;
                }
            }
        }
        System.out.println(visit[N]);

       
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}