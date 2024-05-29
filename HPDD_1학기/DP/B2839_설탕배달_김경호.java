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
	
	static int N;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		for(int i=0;i<N+1;i++) {
			dp[i] = -1;
			if(i==3||i==5) dp[i]=1;
		}
		
		for(int i=6; i < N+1;i++) {
			int beforeThree = dp[i-3];
			int beforeFive = dp[i-5];
			
			// 둘중에 0보다 크고 
			if(beforeThree < 0 && beforeFive < 0) continue;
			dp[i] = beforeThree>0 && beforeFive>0 
					? Math.min(beforeThree+1, beforeFive+1) : beforeThree>0?beforeThree+1:beforeFive+1;
		}
		System.out.println(dp[N]);
	}
		
}
