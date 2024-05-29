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
	
	static int K;
	static int N;
	static long[] lans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lans = new long[K];
		for(int i=0 ; i<K;i++) {
			lans[i] = Integer.parseInt(br.readLine());
		}
		
//		int start = Arrays.stream(lans).min().orElse(-1);
		long start = 0;
		long end = (int) Arrays.stream(lans).max().orElse(-1);
		long mid = 1;
		long cnt = 0;
		while(start<=end) {
			mid = (start+end)/2;
			
			// cnt => 현재 길이(mid)로는 몇개가 만들어지니??
			cnt = maxLanCnt(mid);
			
			// N개보다 많으면 랜선의 길이를 늘인다. (탐색범위 오른쪽으로)
			if(cnt>=N) {
				start = mid+1;
				
			// N개보다 적으면 랜선의 길이를 줄인다.(탐색범위 왼쪽으로)
			}else if(cnt<N){
				end = mid-1;
			}
		}
		
		System.out.println(start-1);
	}
	public static long maxLanCnt(long len) {
		return Arrays.stream(lans).map(o->o/len).sum();
	}

}
