import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 0;
        int t = 0;
        while((N = Integer.parseInt(br.readLine())) != 0){
            ++t;
            int[][] map = new int[N][N];
            int[][] dp = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st =new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = 123456789;
                }
            }

            dp[0][0] = map[0][0];
            Queue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[2], o2[2]);
                }
            });
            pq.add(new int[]{0,0,dp[0][0]});

            while(!pq.isEmpty()){
                int[] cur = pq.poll();

                if(cur[2] > dp[cur[0]][cur[1]]) continue;

                for(int i=0;i<4;i++){
                    if(cur[0]+dr[i]<0||cur[0]+dr[i]>N-1||cur[1]+dc[i]<0||cur[1]+dc[i]>N-1)
                        continue;
                    int newWeight = cur[2] + map[cur[0]+dr[i]][cur[1]+dc[i]];
                    if(dp[cur[0]+dr[i]][cur[1]+dc[i]] > newWeight){
                        dp[cur[0]+dr[i]][cur[1]+dc[i]] = newWeight;
                        pq.add(new int[]{cur[0]+dr[i], cur[1]+dc[i], newWeight});
                    }
                }
            }
            System.out.println("Problem "+t+": "+dp[N-1][N-1]);


        }
    }

}