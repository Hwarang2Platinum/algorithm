import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dr = {1,0,-1,0};
        int[] dc = {0,-1,0,1};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]>[] adjList = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A_i = Integer.parseInt(st.nextToken());
            int B_i = Integer.parseInt(st.nextToken());
            int C_i = Integer.parseInt(st.nextToken());

            adjList[A_i].add(new int[]{B_i,C_i});
            adjList[B_i].add(new int[]{A_i,C_i});
        }

        int[] dist = new int[N+1];
        Arrays.fill(dist, 12345678);
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1]-o2[1]));
        pq.add(new int[]{1,0});
        dist[1] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(dist[cur[0]] < cur[1]) continue;

            for (int[] next : adjList[cur[0]]){
                int nextCost = cur[1] + next[1];
                if(dist[next[0]] > nextCost){
                    dist[next[0]] = nextCost;
                    pq.add(new int[]{next[0], nextCost});
                }
            }
        }

        System.out.println(dist[N]);
    }
}
