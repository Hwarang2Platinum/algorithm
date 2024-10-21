import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        int[] times = new int[N+1];
        int[] degrees = new int[N+1];
        List<Integer>[] grade = new ArrayList[N+1];
        List<Integer>[] adjList = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            grade[i] = new ArrayList<>();
            adjList[i] = new ArrayList<>();
        }
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            times[i] = t;
            grade[g].add(i);
        }

        for (int i = 1; i < N; i++) {
            for(int g : grade[i]){
                for(int n : grade[i+1]){
                    adjList[g].add(n);
                    degrees[n]++;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        int[] dp = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            if(degrees[i]==0) {
                q.add(new int[]{i, times[i]});
                dp[i] = times[i];
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            for (int next : adjList[cur[0]]) {
                dp[next] = Math.max(dp[next], (cur[0]-next)*(cur[0]-next) + cur[1] + times[next]);

                degrees[next]--;
                if(degrees[next]==0){
                    q.add(new int[]{next, dp[next]});
                }
            }
        }
        Arrays.sort(dp);
        System.out.println(dp[dp.length-1]);
    }

}
