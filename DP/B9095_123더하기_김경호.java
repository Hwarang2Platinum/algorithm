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
		
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		for(int i=0;i<T;i++) {
			N = Integer.parseInt(br.readLine());
			
			int[] dp = new int[N+3];
			for(int j=1;j<N+1;j++) {
				if(j==1) {
					dp[1] = 1; continue;
				}else if(j==2) {
					dp[2] = 2; continue;
				}else if(j==3) {
					dp[3]=4; continue;
				}
				dp[j] = dp[j-1]+dp[j-2]+dp[j-3];
			}
			System.out.println(dp[N]);
		}
		
		
		
	}
		
}
