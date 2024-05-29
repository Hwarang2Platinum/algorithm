
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = Integer.MIN_VALUE;
		int[] dp = new int[N+1];
		int[] nums = new int[N+1];
		for(int i=1;i<N+1;i++) nums[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<N+1;i++) {
			// i보다 작은 인덱스 검사해서 nums[i]보다 작은인덱스들중 dp값이 가장 큰 값 + 1
			for(int j=i-1;j>=0;j--) {
				if(nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					ans = Math.max(ans, dp[i]);
				}
			}
		}
		System.out.println(ans);
		
	}

}
