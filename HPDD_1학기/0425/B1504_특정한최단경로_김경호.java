import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<int[]>[] adjList = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new int[]{b,c});
            adjList[b].add(new int[]{a,c});
        }
        st = new StringTokenizer(br.readLine());
        int mustGo1 = Integer.parseInt(st.nextToken());
        int mustGo2 = Integer.parseInt(st.nextToken());

        int[] startDp = dijkstra(1,adjList);
        int[] mustDp1 = dijkstra(mustGo1,adjList);
        int[] mustDp2 = dijkstra(mustGo2,adjList);

        // dijkstra start to mustGo1
        // dijkstra mustGo1 to mustGo2
        // dijkstra mustGo2 to end
        int ans1 = startDp[mustGo1] + mustDp1[mustGo2] + mustDp2[N];
        // vs
        // dijkstra start to mustGo2
        // dijkstra mustGo2 to mustGo1
        // dijkstra mustGo1 to end
        int ans2 = startDp[mustGo2] + mustDp2[mustGo1] + mustDp1[N];

        if(ans1>=12345678 && ans2>=12345678){
            System.out.println(-1);
        } else {
            System.out.println(Math.min(ans1,ans2));
        }

    }

    private static int[] dijkstra(int start, List<int[]>[] adjList) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);

        int[] dp = new int[adjList.length];
        Arrays.fill(dp, 123456789);
        pq.add(new int[]{start, 0});
        dp[start] = 0;

        while (!pq.isEmpty()){
            int[] cur = pq.poll();

            if(cur[1] > dp[cur[0]]) continue;

            for(int[] next : adjList[cur[0]]){
                int nextWeight = cur[1] + next[1];
                if(dp[next[0]] > nextWeight){
                    dp[next[0]] = nextWeight;
                    pq.add(new int[]{next[0], nextWeight});
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        return dp;
    }


}
