package algorithm_java;
import java.io.*;
import java.util.*;

public class B3197_백조의호수 {
	static int[][] board;
	static int R,C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		int[] start = new int[2];
		int[] end = new int[2];
		int p = 2;
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> qIce = new ArrayDeque<>();
		int[] dr = {1,0,-1,0};
		int[] dc = {0,1,0,-1};
		boolean[][] visited = new boolean[R][C];
		boolean[][] iceVisited = new boolean[R][C];
		for(int r = 0;r<R;r++) {
			String str = br.readLine();
			for(int c=0;c<C;c++) {
				if(str.charAt(c) == '.') {
					board[r][c] = 0;
				}else if(str.charAt(c) == 'X') {
					board[r][c] = 1;
				}else {
					if(p++==2) start = new int[] {r,c};
					else end = new int[] {r,c};
					board[r][c] = 0;
				}
				
			}
		}
		for(int r = 0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(board[r][c] == 1) {
					for(int i=0;i<4;i++) {
						int nextR = r+dr[i];
						int nextC = c+dc[i]; 
						if(nextR<0|| nextR>R-1 || nextC<0 || nextC>C-1) continue;
						if(iceVisited[nextR][nextC]) continue;
						
						if(board[nextR][nextC] == 0) {
							iceVisited[r][c] = true;
							qIce.add(new int[]{r,c});
							break;
						}
					}
				}
			}
		}

		// 1. bfs 해서 만날 수 있는지 확인
		// 2. 빙하 녹이기.
		// 1번 2번 반복
		// 만날 수 있으면 종료
		int ans = 0;
		
		
		q.add(start);
		
		while(true) {
//			System.out.println("before-----------------");
//			for(int[] b : board) {
//				System.out.println(Arrays.toString(b));
//			}
			boolean isFind = false;
			// 만날 수 있는 지 확인 만날 수 있으면 isFind 는 true
			Queue<int[]> nq = new ArrayDeque<>();
//			System.out.println("-----------------");
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				
//				System.out.println(Arrays.toString(cur));
				if(cur[0] == end[0] && cur[1] == end[1]) {
					isFind = true;
					break;
				}
//				if(visited[cur[0]][cur[1]]) continue;
				for(int i=0;i<4;i++) {
					int nextR = cur[0]+dr[i];
					int nextC = cur[1]+dc[i];
					if(nextR<0|| nextR>R-1 || nextC<0 || nextC>C-1) continue;
					if(visited[nextR][nextC]) continue;
					if(board[nextR][nextC]==0) {
						q.add(new int[] {nextR,nextC});
						visited[nextR][nextC] = true;				
					}
					
					if(board[nextR][nextC]==1) {
						nq.add(new int[] {nextR,nextC});
						visited[nextR][nextC] = true;
					}
				}	
			}
			
			q = nq;
			
			// 만날 수 있으면 종료
			if(isFind) {
				System.out.println(ans);
				break;
				
			// 만날 수 없으면 빙하 녹이기
			}else {
				ans++;
				
				Queue<int[]> nqq = new ArrayDeque<>();
				while(!qIce.isEmpty()) {
					int[] cur = qIce.poll();
					
					int r = cur[0]; int c=cur[1];
					board[r][c] = 0;
					
					for(int i=0;i<4;i++) {
						int nextR = r+dr[i];
						int nextC = c+dc[i]; 
						
						if(nextR<0|| nextR>R-1 || nextC<0 || nextC>C-1) continue;
						if(iceVisited[nextR][nextC]) continue;
						
						// 빙하면 녹이고 다른 땅이 접근 못하게 visited처리
						if(board[nextR][nextC] == 1) {
							iceVisited[nextR][nextC] = true;
							nqq.add(new int[] {nextR, nextC});
						}
					}
				}

				qIce = nqq;
			}
//			System.out.println("after-----------------");
//			for(int[] b : board) {
//				System.out.println(Arrays.toString(b));
//			}
		}
			
	}


}