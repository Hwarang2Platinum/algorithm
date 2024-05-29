package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_kh {
	static StringBuilder sb = new StringBuilder();
	static boolean[] check;
	static int[][] arr;
	
	static int node, line, start;
	
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		node = Integer.parseInt(st.nextToken());
		line = Integer.parseInt(st.nextToken());
		start= Integer.parseInt(st.nextToken());
		
		arr = new int[node+1][node+1];
		check = new boolean[node+1];
		
		for(int i = 0 ; i < line ; i ++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());
			
			arr[a][b] = arr[b][a] =  1;	
		}
			//sb.append("\n");
			dfs(start);
			sb.append("\n");
			check = new boolean[node+1];
			
			bfs(start);
			
			System.out.println(sb);
		
		}
	public static void dfs(int start) {
		
		check[start] = true;
		sb.append(start + " ");
		
		for(int i = 0 ; i <= node ; i++) {
			if(arr[start][i] == 1 && !check[i])
				dfs(i);
		}
		
	}
	
	public static void bfs(int start) {
		q.add(start);
		check[start] = true;
		
		while(!q.isEmpty()) {
			
			start = q.poll();
			sb.append(start + " ");
			
			for(int i = 1 ; i <= node ; i++) {
				if(arr[start][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
				}
			}
		}
		
		
	}
	
	
	

//	static PriorityQueue<Integer> [] mapping;
//	static boolean[] dfs_visited;
//	static int size;
//	static boolean[] bfs_visited;
//	static int N, M, start;
//	static ArrayList ans;
//	
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		N = Integer.valueOf(st.nextToken());
//		M = Integer.valueOf(st.nextToken());
//		start = Integer.valueOf(st.nextToken());
//		
//		ans = new ArrayList();
//		mapping = new PriorityQueue[N+1];
//		dfs_visited = new boolean[N+1];
//		bfs_visited = new boolean[N+1];
//		
//		for(int i=0; i< M ;i++) {
//			st = new StringTokenizer(br.readLine());
//			int s = Integer.valueOf(st.nextToken());
//			int f = Integer.valueOf(st.nextToken());
//			if(mapping[s] == null) mapping[s] = new PriorityQueue<Integer>();
//			if(mapping[f] == null) mapping[f] = new PriorityQueue<Integer>();
//			mapping[s].add(f);
//			mapping[f].add(s);
//		}
//		
//		size = 0;
//		DFS(start);
//		String str = ans.toString().replaceAll("[^0-9 ]","");
//		System.out.println(str);
//		ans.clear();
//		
//		size = 0;
//		BFS(start);
//		str = ans.toString().replaceAll("[^0-9 ]","");
//		System.out.println(str);
//		
//	}
//	public static void BFS(int start) {
//		Queue<Integer> queue = new LinkedList<>();
//		
//		queue.add(start);
//		int cur;
//		
//		while(!queue.isEmpty()) {
//			System.out.println(queue.toString());
//			cur = queue.poll();
//			ans.add(cur);
//			
//			if(size == N-1) break;
//
//			size++;
//			bfs_visited[cur] = true;
//			
//			PriorityQueue<Integer> al = mapping[cur];
//			System.out.println(al.toString());
//			if(al == null)continue;
//			while(!al.isEmpty()) {
//				int tmp = al.poll();
//				System.out.println(tmp);
//				if(!bfs_visited[tmp]) {
//					queue.add(tmp);
//				}
//			}
//			
//		}
//	}
//	public static void DFS(int cur) {
//		ans.add(cur);
//		size++;
//		if(size==N)return;
//		// 깊이 우선 탐색이다.깊이 먼저 탐색 하자 깊이 깊이 깊이 깊이
//		// 
//		
//	}
}
