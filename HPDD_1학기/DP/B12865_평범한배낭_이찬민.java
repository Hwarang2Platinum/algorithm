import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12865_평범한배낭_이찬민 {
    static int[][] arr;
    static int[][] dp;
    static int N;
    static int weight;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물건종류
        weight = Integer.parseInt(st.nextToken()); // 무게제한


        arr = new int[N+1][2]; // 물품의 무게, 가치
        dp = new int[N+1][weight+1]; // 최대 가치 저장

        for(int i = 1 ; i <= N ;i ++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st1.nextToken()); //무게
            arr[i][1] = Integer.parseInt(st1.nextToken()); // 가치
        }

        for(int i = 1 ; i <= N ; i++) {
            for(int j = 1 ; j <= weight ; j++) {
                if(j - arr[i][0] >= 0) //현재 최대 무게에서 물건의 무게를 빼도 남을떄
                    dp[i][j] = Math.max( dp[i-1][j], arr[i][1]+dp[i-1][j-arr[i][0]]); //현재물건가치+ 현재물건 무게를 제외한 최대가치
                else
                    dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][weight]);
    }

}