package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<int[]>[] adjList = new ArrayList[N+1];
		StringTokenizer st = null;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if(adjList[a] == null) adjList[a] = new ArrayList<>();
			adjList[a].add(new int[] {b,w});
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});
		
		long[] dist = new long[N+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[start] = 0;
		pq.add(new int[] {start,0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(dist[cur[0]] < cur[1]) continue;
			
			if(adjList[cur[0]]==null) continue;
			for(int i=0;i<adjList[cur[0]].size();i++) {
				int[] next = adjList[cur[0]].get(i);
				int newDist = cur[1] + next[1];
				if(dist[next[0]]> newDist) {
					dist[next[0]] = newDist;
					pq.add(new int[] {next[0], newDist});
				}
			}
		}
		
//		System.out.println(Arrays.toString(dist));
		System.out.println(dist[end]);
		
	}
}
 