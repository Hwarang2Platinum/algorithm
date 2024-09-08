package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B2583 {
	static int n, m, k, extent;
	static int[][] field;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<Integer> extents;
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		field = new int[m][n];
		extents = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int leftBottomX = Integer.parseInt(st.nextToken());
			int leftBottomY = Integer.parseInt(st.nextToken());
			int rightTopX = Integer.parseInt(st.nextToken());
			int rightTopY = Integer.parseInt(st.nextToken());
			for (int a = leftBottomY; a < rightTopY; a++) {
				for (int b = leftBottomX; b < rightTopX; b++) {
					field[a][b] = 1;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (field[i][j] != 1) {
					extent = 1;
					dfs(i, j);
					extents.add(extent);
				}
			}
		}
		Collections.sort(extents);
		
		System.out.println(extents.size()); 
		for (int i = 0; i < extents.size(); i++) {
		    if (i == extents.size() - 1) {
		        System.out.print(extents.get(i));
		    } else {
		        System.out.print(extents.get(i) + " ");
		    }
		}
	}
	
	static void dfs(int y, int x) {
		field[y][x] = 1;
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX >= 0 && nextY >= 0 && nextX < n && nextY < m && field[nextY][nextX] == 0) {
				extent++;
				dfs(nextY, nextX);
			}
		}
	}
}
