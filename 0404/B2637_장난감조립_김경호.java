import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static List<int[]>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        int[] xdegrees = new int[N+1];
        int[] ydegrees = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            adjList[x].add(new int[]{y,k});
            ydegrees[y]++;
            xdegrees[x]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] dp = new int[N+1];
        q.add(N);
        dp[N] = 1;
        while(!q.isEmpty()){
            int cur = q.poll();
            for (int[] next : adjList[cur]) {
                dp[next[0]] += dp[cur]*next[1];
                if(--ydegrees[next[0]] == 0){
                    q.add(next[0]);
                }
            }
        }
        for (int i = 1; i < N + 1; i++) {
            if(xdegrees[i]==0)
                System.out.println(i+" "+dp[i]);
        }


    }

}
