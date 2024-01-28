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
	
	static int C;
	static int N;
	static long[] routers;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		routers = new long[N];
		for(int i=0 ; i<N;i++) {
			routers[i] = Integer.parseInt(br.readLine());
		}
		// n개의 집중에 c개를 골랐을 때
		// 가장 인접한 두 공유기 사이의 거리가 최대가 되는 경우 
		// 1 2 4 8 9
		// o o x o x x x o o
		Arrays.sort(routers);
		long start = 1;
		long end = Arrays.stream(routers).max().orElse(0);
		long mid = 0;
		while(start<=end) {
			mid = (start+end)/2;
			int cnt = checkMaxInstall(mid);
//			System.out.println("cnt:"+cnt+" mid:"+mid);
			if(cnt>=C) {
				start = mid + 1;
			}else {
				end = mid - 1;
			}
		}
		System.out.println(start-1);
	}
	
	public static int checkMaxInstall(long dist) {
		int ret = 1;
		long lastInstalled = routers[0];
		
		for(int i = 1; i<N ; i++) {
			if(routers[i]-lastInstalled>=dist) {
				ret++;
				lastInstalled = routers[i];
			}
		}
		
		return ret;
	}
}
