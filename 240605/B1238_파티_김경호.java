import java.io.*;
import java.util.*;
/**
 * BOJ 1238 파티
 * 다익스트라 문제
 * 파티에 오고가는 마을 학생의 최단거리 계산
 * 타겟 마을 -> 각 학생 (다익스트라)
 * 단방향이므로 각학생 -> 타겟마을도 각각 구해줘야함
 */
public class Main {
    static List<int[]>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[s].add(new int[]{e,c});
        }

        int[] xToStudentsDist = new int[N+1];
        Arrays.fill(xToStudentsDist,123456789);
        dijkstra(X, xToStudentsDist);

//        System.out.println(Arrays.toString(xToStudentsDist));
        int ans = 0;
        for (int i = 1; i < N+1; i++) {
            if(i==X) continue;
            int[] dist = new int[N+1];
            Arrays.fill(dist,123456789);
            dijkstra(i,dist);
//            System.out.println(Arrays.toString(dist));
            ans = Math.max(ans, xToStudentsDist[i] + dist[X]);
        }
        System.out.println(ans);
    }

    public static void dijkstra(int start,int[] dist){
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(((o1, o2) -> o1[1]-o2[1]));
        pq.add(new int[]{start, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(dist[cur[0]] < cur[1]) continue;

            for(int[] next : adjList[cur[0]]){
                int newDist = dist[cur[0]] + next[1];
                if(dist[next[0]] > newDist){
                    dist[next[0]] = newDist;
                    pq.add(new int[]{next[0], newDist});
                }
            }
        }

    }

}