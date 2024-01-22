package com.DFS_BFS;

import java.io.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_DFS_BFS_2606_HR {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int numCom = Integer.parseInt(input.readLine());
		int numPair = Integer.parseInt(input.readLine());
		
		boolean [] visited = new boolean[numCom + 1];
		ArrayList<Integer>[] graph = new ArrayList[numCom + 1];
		for (int i = 0 ; i < numCom + 1 ; i++)
			graph[i] = new ArrayList();
		
		for (int i = 0 ; i < numPair ; i++) {
			StringTokenizer pair = new StringTokenizer(input.readLine());
			int com1 = Integer.parseInt(pair.nextToken());
			int com2 = Integer.parseInt(pair.nextToken());
			graph[com1].add(com2);
			graph[com2].add(com1);
		}
		
		System.out.println(BFS(graph, visited)); 

	}
	
	public static int BFS(ArrayList<Integer> [] graph, boolean[] visited) {
		int count = 0;
		visited[1] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		while (queue.size() > 0) {
			int currVer = queue.poll();
			count++;
			for (int adj : graph[currVer] ) {
				if (!visited[adj]) {
					queue.add(adj);
					visited[adj] = true;
				}
			}
		}
		return count - 1;
	}
}
