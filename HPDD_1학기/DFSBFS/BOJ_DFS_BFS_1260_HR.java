package com.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_DFS_BFS_1260_HR {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] graph_component = input.readLine().split(" ");
		int vertexNum = Integer.parseInt(graph_component[0]);
		int nodeNum = Integer.parseInt(graph_component[1]);
		int startVertex = Integer.parseInt(graph_component[2]);
		
		ArrayList<Integer>[] graph = new ArrayList[vertexNum + 1];
		for (int i = 0 ; i < vertexNum + 1 ; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		boolean [] visited = new boolean[vertexNum + 1];
		
		
		for (int i = 0 ; i < nodeNum ; i++) {
			graph_component = input.readLine().split(" ");
			int ver1 = Integer.parseInt(graph_component[0]);
			int ver2 = Integer.parseInt(graph_component[1]);
			graph[ver1].add(ver2);
			graph[ver2].add(ver1);
		}
		
		for (int i = 0 ; i < vertexNum + 1 ; i++)
			Collections.sort(graph[i]);
		
		DFS(graph, startVertex, visited);
		visited = new boolean[vertexNum + 1];
		System.out.println();
		BFS(graph, startVertex, visited);
	}
	
	public static void DFS(ArrayList<Integer> [] graph, int startVertex, boolean[] visited) {
		visited[startVertex] = true;
		System.out.printf("%d ", startVertex);
		for (int adj : graph[startVertex]) {
			if (!visited[adj])
				DFS(graph, adj, visited);
		}
	}
	public static void BFS(ArrayList<Integer> [] graph, int startVertex, boolean[] visited) {
		visited[startVertex] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startVertex);
		while (queue.size() > 0) {
			int currVer = queue.poll();
			System.out.printf("%d ", currVer);
			for (int adj : graph[currVer]) {
				if (!visited[adj]) {
					queue.add(adj);
					visited[adj] = true;
				}
			}	
		}
	}
}


