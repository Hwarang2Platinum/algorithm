import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class B11053_가장긴증가하는부분수열_김인엽 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		
		for(int i=1; i<N; i++) {
			// 왼쪽 요소들 탐색
			for(int j=0; j<i; j++) {
				// 더 크다면
				if(arr[j]<arr[i]) {
					// 해당 인덱스의 dp값 + 1과 비교해서 더 큰값 가져가기
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
		}
		
		System.out.println(Arrays.stream(dp).max().orElseThrow(NoSuchElementException::new));
	}

}
