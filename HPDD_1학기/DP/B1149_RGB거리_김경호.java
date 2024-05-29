package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp= new int[N+1][3];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())
					, g = Integer.parseInt(st.nextToken())
					, b = Integer.parseInt(st.nextToken());
			
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + r;
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + g;
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + b;
		}
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
		
	}
		
}
