import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// n개의 물건이 무게w와 가치k를 가지고 있음
// 무게를 최대 m까지 가질 수 있을 때, 최대가치를 가지게 짐을 구성

// k개의 dp배열
// i+w1 => max(dp[i+w1], dp[i] + k1), k+w2
// 하나의 물건은 한번만 사용가능.......
// n * k dp배열
// n개중 하나의 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][K+1];
		int[][] things = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken());
			things[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int j=0;j<N;j++) {
			for(int i=0;i<K+1;i++) {
				int w = things[j][0];
				int v = things[j][1];
				if(i-w>=0) {
					dp[j+1][i] = Math.max(dp[j][i-w] + v, dp[j][i]);
				}else {
					dp[j+1][i] = dp[j][i];
				}
			}
//			System.out.println(Arrays.toString(dp[j+1]));
		}
		System.out.println(dp[N][K]);
	}
	
	
	
}
