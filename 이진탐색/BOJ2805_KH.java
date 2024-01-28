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
	
	// 20 15 10 17
	// M이상으로 잘라야되고, 높이는 최소가 되어야함 
	// M보다 작다면 더 낮은 곳 탐색, M보다 크다면 더 높은 곳 탐색
	static long N;
	static long M;
	static long[] trees;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trees = Arrays.stream(br.readLine().split(" "))
				.mapToLong(o->Integer.parseInt(o)).toArray();
		
		long left = 1;
		long right = Arrays.stream(trees).max().orElse(10000);
		long mid = -1;
		while(left<=right) {
			mid = (left+right)/2;
//			System.out.println(cuttingTrees(mid)+":"+left+","+ right+","+ mid);
			if(cuttingTrees(mid) >= M){
				left = mid+1;
			}else if(cuttingTrees(mid) < M) {
				right = mid-1;
			}
		}
		System.out.println(left-1);
	}
	
	public static long cuttingTrees(long height) {
		return Arrays.stream(trees).map(o->o-height).filter(o->o>0).sum();
	}

}
