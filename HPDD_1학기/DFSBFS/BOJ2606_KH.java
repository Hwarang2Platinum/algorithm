package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2606_KH {

	static int ans = 0;
	static boolean[] check;
	static int[][] arr;
	
	static int node, line, start;
	
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		node = Integer.parseInt(br.readLine());
		line = Integer.parseInt(br.readLine());
		start= 1;
		
		arr = new int[node+1][node+1];
		check = new boolean[node+1];
		
		for(int i = 0 ; i < line ; i ++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());
			
			arr[a][b] = arr[b][a] =  1;	
			}
		bfs(start);
		
		System.out.println(ans-1);
		
	}

	public static void bfs(int start) {
		q.add(start);
		check[start] = true;
		
		while(!q.isEmpty()) {
			
			start = q.poll();
			ans++;
			
			for(int i = 1 ; i <= node ; i++) {
				if(arr[start][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
				}
			}
		}
		
		
	}

}
