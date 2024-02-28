import java.io.*;
import java.util.*;

public class Solution {
	static int[][] board;
	static boolean[][] visited;
	static Pos[] li;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int N, ans ,mc;
	static PriorityQueue<Comparing> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N]; 
			visited = new boolean[N][N];
			li = new Pos[13];
			ans = 100000;
			pq = new PriorityQueue<>();
			int idx=0;
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j]==1) {
						if(i==0||j==0||i==N-1||j==N-1) {
							continue;
						}
						li[idx++] = new Pos(i,j);
					}
				}
			}
			
			bt(0,0,0, idx,0);
			System.out.println("#"+ t+" " + pq.peek().line);
		}
	}
	private static void bt(int depth, int start, int result, int totalCnt, int maxCount) {
		pq.add(new Comparing(depth,result));
		if(depth == totalCnt)return;

		for(int i=start; i<totalCnt;i++) {
			Pos cur = li[i];
//			if(visited[cur.r][cur.c]) continue;
//			visited[cur.r][cur.c] = true;
			// 프로세스 선택 완료
			// 이후 4방향으로 전선 뽑아보기.
			// stack으로 다음방향 계속 넣기(2로넣으면서), 넣을 때 전선이나 프로세서 만나면 끄기 (다시 다 팝하면서 0으로 되돌려놓기)
			// 다 정상적으로 넣어졌으면 다음 bt 호출
			// 호출 끝나면 (다시 다 팝하면서 0으로 되돌려놓기)
			
			
			for(int j=0;j<4;j++) {
				Stack<Pos> stack = new Stack<>();
				int nextR = cur.r;
				int nextC = cur.c;
				
				while(true) {
					nextR += dr[j];
					nextC += dc[j];
					if(isOut(nextR,nextC)) {
						bt(depth+1, i+1, result+stack.size(),totalCnt,maxCount+1);
//						visited[cur.r][cur.c] = false;
						while(!stack.isEmpty()) {
							Pos tmp = stack.pop();
							board[tmp.r][tmp.c]= 0; 
						}
						break;
					}
					if(board[nextR][nextC]!=0) {
						while(!stack.isEmpty()) {
							Pos tmp = stack.pop();
							board[tmp.r][tmp.c]= 0; 
						}
						break;
					}
					board[nextR][nextC] = 2;
					stack.add(new Pos(nextR,nextC));
				}
			}
//			bt(depth+1, i+1, result,totalCnt,maxCount);
			// 전선 뽑아지면 다음 프로세서 선택하러가기
			
			
		}
		
	}
	
	private static boolean isOut(int i, int j) {
		// TODO Auto-generated method stub
		return i>N-1||i<0||j>N-1||j<0;
	}
	public static class Comparing implements Comparable<Comparing>{
		int count, line;
		
		public Comparing(int count, int line) {
			super();
			this.count = count;
			this.line = line;
		}

		@Override
		public int compareTo(Comparing o) {
			// TODO Auto-generated method stub
			if(this.count == o.count) {
				return this.line - o.line;
			}else {
				return o.count - this.count;
			}
		} 
	}
	public static class Pos{
		int r, c ;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	
}