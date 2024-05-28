import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] apps;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        apps = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            apps[i][0] = Integer.parseInt(st.nextToken());
            total+=apps[i][0];
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            apps[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[10001];
        int ans = 100000;
        for(int i=1;i<=N;i++){
            int m = apps[i-1][0];
            int c = apps[i-1][1];

            for(int j=10000;j>=c;j--){
                dp[j] = Math.max(dp[j-c]+m, dp[j]);
                if(dp[j]>=M) ans = Math.min(ans,j);
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(ans);
    }


}
