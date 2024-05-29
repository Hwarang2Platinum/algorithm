import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,answer=0;
	static int[][] board;
	static boolean[][] eated;
	static int state = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		int[] cur = new int[2];
		boolean[] fishes = new boolean[401];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int fish = Integer.parseInt(st.nextToken());
				board[i][j] = fish;
				if(fish==9) {
					cur[0] = i;
					cur[1] = j;
					continue;
				}
//				if(fish>0) fishes[i*N+j] = true;
			}
		}
		// 1. bfs로 가장 가까운 먹거리를찾는다.
		// 없으면 종료
		// 있으면 자리교체 후 bfs cnt값 받
		int eatingNum = 0;
		int ans = 0;
		int[] ret = null;
		while((ret = bfs(cur[0],cur[1]))!=null) {
//			System.out.println("state=> "+state);
//			for(int[] b : board) {
//				System.out.println(Arrays.toString(b));
//			}
//			System.out.println("-----------------------");
			
			
			eatingNum++;
			if(eatingNum==state) {
				state++;
				eatingNum=0;
			}
			ans+=ret[2];
			board[ret[0]][ret[1]] = 9;
			board[cur[0]][cur[1]] = 0;
			cur[0] = ret[0];
			cur[1] = ret[1];
//			System.out.println(ans);
		}
		
		System.out.println(ans);
	}
	
	public static int[] bfs(int r, int c) {
		int[] dr = {-1,0,0,1};
		int[] dc = {0,-1,1,0};
		boolean[][] visited = new boolean[400][400];
		int[] retu = null;
		Queue<int[]> queue = new ArrayDeque<>();
		PriorityQueue<Fish> pq= new PriorityQueue<>();
		queue.add(new int[] {r,c,0});
		int ans = 10000;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
//			System.out.println(cur[0] +","+cur[1]);
			r = cur[0]; c = cur[1];
			visited[r][c] = true;
			if(ans<cur[2]) {
				Fish ret = pq.peek();
//				System.out.println("=="+ret);
				answer+=ans;
				return new int[] {ret.r,ret.c,ret.cnt};
			}
			if(board[r][c]!=0 && board[r][c] < state) {
				pq.add(new Fish(cur[0],cur[1],board[cur[0]][cur[1]], cur[2]));
				if(ans==10000) {
					ans = cur[2];
					retu = new int[] {cur[0],cur[1],cur[2]};
//					System.out.println(ans);
				}
//				return cur; 
			}
			
			for(int i=0;i<4;i++) {
				int nextR = r + dr[i]; int nextC = c+dc[i];
				
				if(isOut(nextR,nextC)|| visited[nextR][nextC]|| board[nextR][nextC] > state) 
					continue;
				visited[nextR][nextC] = true;
				queue.add(new int[] {nextR,nextC,cur[2]+1});
			}
		}
		return retu;
	}
	
	public static boolean isOut(int r, int c) {
		return r<0||r>N-1 || c<0|| c>N-1;
	}
	static class Fish implements Comparable<Fish>{
		int r, c, size, cnt;

		public Fish(int r, int c, int size,int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			return (this.r-o.r==0 ? this.c - o.c:this.r-o.r);
		}

		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", size=" + size + "]";
		}
		
	}
}
 