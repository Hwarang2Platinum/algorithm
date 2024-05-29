import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        s--;a--;b--;
        List<int[]>[] adjList = new ArrayList[n];

        for(int[] adj : fares){
            int c = adj[0]-1;
            int d = adj[1]-1;
            if(adjList[c]==null) adjList[c] = new ArrayList<>();
            adjList[c].add(new int[]{d,adj[2]});
            if(adjList[d]==null) adjList[d] = new ArrayList<>();
            adjList[d].add(new int[]{c,adj[2]});
        }
//ㅁㄴㅇ
        int min = Integer.MAX_VALUE;
        int[] distStoCommonPoint = dijkstra(s,n,adjList);
        for(int commonPoint=0; commonPoint<n;commonPoint++){
            int[] distCommonPointToAB = dijkstra(commonPoint,n,adjList);
            min = Math.min(min,
                    distStoCommonPoint[commonPoint] + distCommonPointToAB[a]+distCommonPointToAB[b]);
        }

        System.out.println(min);
        return min;
    }
    int[] dijkstra(int start, int n, List<int[]>[] adjList){
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] dist = new int[n];
        for(int i=0;i<n;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        pq.add(new int[]{start,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(dist[cur[0]] < cur[1]) continue;

            if(adjList[cur[0]]==null) continue;
            for(int[] next : adjList[cur[0]]){
                int nextCost = cur[1] + next[1];
                if(dist[next[0]]>nextCost){
                    dist[next[0]] = nextCost;
                    pq.add(new int[]{next[0],nextCost});;
                }
            }
        }

        return dist;
    }
}