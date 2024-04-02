import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = map[0][0];
        for(int i=1;i<M;i++){
            dp[0][i] = dp[0][i-1]+map[0][i];
        }
//        dp[0][M-1] = map[0][M-1];
//        for(int i=M-2;i>=0;i--){
//            dp[0][i] = dp[0][i+1]+map[0][i];
//        }
        for (int i = 1; i < N; i++) {
            int[] tmp1 = new int[M];
            int[] tmp2 = new int[M];
            tmp1[0] = dp[i-1][0] + map[i][0];
            for (int j = 1; j < M; j++) {
                tmp1[j] = tmp1[j-1] + map[i][j];
            }
            tmp2[M-1] = dp[i-1][M-1] + map[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                tmp2[j] =tmp2[j+1] + map[i][j];
            }
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(Math.max(tmp1[j],tmp2[j]),dp[i-1][j]+ map[i][j]);
            }
        }
        for(int[] d : dp){
            System.out.println(Arrays.toString(d));
        }
        System.out.println(dp[N-1][M-1]);
    }
}
