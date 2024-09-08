package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1261 {
	static BufferedReader br = null;
	static StringTokenizer st = null;
	static int row, col;
	static int[][] field;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		field = new int[row+1][col+1];
		visited = new boolean[row+1][col+1];
		for(int i = 1; i <= row; i++) {
            String[] input = br.readLine().split("");
			for (int j = 1; j <= col; j++) {
				field[i][j] = Integer.parseInt(input[j-1]);
			}
		}
		System.out.println(go(1, 1));
	}
	
	static int go(int startRow, int startCol) {
		Queue<Point> q = new PriorityQueue<>();
		q.offer(new Point(startRow, startCol, 0));
		visited[startRow][startCol] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			if (p.row == row && p.col == col) {
				return p.brokenWalls;
			}
			for(int i = 0; i < 4; i++) {
				int nextRow = p.row + dx[i];
				int nextCol = p.col + dy[i];
				if (nextRow <= 0 || nextCol <= 0 || nextRow > row || nextCol > col) {
					continue;
				} else if (visited[nextRow][nextCol]) {
					continue;
				} else if (field[nextRow][nextCol] == 0) {
					q.offer(new Point(nextRow, nextCol, p.brokenWalls));
				} else {
					q.offer(new Point(nextRow, nextCol, p.brokenWalls+1));
				}
				
				visited[nextRow][nextCol] = true;
			}
		}
		return 0;
	}
}

class Point implements Comparable<Point> {
	int row, col, brokenWalls;
	Point(int row, int col, int brokenWall) {
		this.row = row;
		this.col = col;
		this.brokenWalls = brokenWall;
	}
	@Override
	public int compareTo(Point o) {
		return this.brokenWalls - o.brokenWalls;
	}
}