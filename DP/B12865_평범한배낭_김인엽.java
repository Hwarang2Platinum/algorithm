import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12865_평범한배낭_김인엽 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][2];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 무게
			arr[i][1] = Integer.parseInt(st.nextToken()); // 가치
		}
		
		int[][] dp = new int[N+1][K+1];
		
		for(int i=1; i<N+1; i++) {
			// 무게가 0이면 0이니까 생략
			for(int j=1; j<K+1; j++) {
				// 지금 들어오는 물건 무게 > 배낭 무게
				if(arr[i][0] > j) {
					dp[i][j] = dp[i-1][j];
				} else { // 그 외
					// 최대값(이전까지의 최대, 현재 물품 가치 + (배낭무게-현재물품무게)의 최대)
					dp[i][j] = Math.max(dp[i-1][j], arr[i][1] + dp[i-1][j-arr[i][0]]);
				}
			}
		}
		
		
		System.out.println(dp[N][K]);
	}

}
