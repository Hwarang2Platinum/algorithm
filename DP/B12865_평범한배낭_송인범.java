import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12865_평범한배낭_송인범 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 햇갈리지 않게 무게 1, 물품 1개 등 이해 쉽게 접근
		int [][] DP = new int [N+1][K+1];
		int [][] info  = new int [N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				if (info[i-1][0]<=j) {
					DP[i][j] = Math.max(DP[i-1][j],DP[i-1][j-info[i-1][0]]+info[i-1][1]);
				}else // 물건을 안 선택할 때의 가치
					DP[i][j] = DP[i-1][j];
				
				
			}
		}
		
		System.out.println(DP[N][K]);
	}

}
