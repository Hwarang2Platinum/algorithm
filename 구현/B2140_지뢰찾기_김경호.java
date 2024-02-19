package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,ans;
	static int[][] board;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				if(input.charAt(j) == '#') board[i][j]=100;
				else board[i][j] = input.charAt(j) - '0';
			}
		}
		
		if(N==1||N==2) {
			System.out.println(0);
			return;
		}
		
		
		dfs(0,0,1,1,0);
		if(N-4>0) {
			ans+=(N-4)*(N-4);
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int cnt, int direction, int r, int c, int install) {
//		System.out.println("r:"+r+" c:"+c);
		if(N==3) {
			if(board[r-1][c-1] == 1) ans =1;
		}
		if(cnt==4*(N-2)-4) {
//			for(int[] b : board)
//				System.out.println(Arrays.toString(b));
//			System.out.println("=====================");
			ans = Math.max(ans, install);
			return;
		}
		
		if(r==1&&c==N-2) {
			direction++;
		}
		else if(c==N-2&&r==N-2) {
			direction++;
		}
		else if(r==N-2&&c==1) {
			direction++;
		}
		
		int[] drr = {-1,0,1};
		int[] dcc = {-1,0,1};
		boolean flag = false;
		boolean flag2 = false;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(i==1&&j==1)continue;
				if(board[r-drr[i]][c-dcc[j]] - 1<0) flag=true;
				// 음수 안나오면 두가지경우 모두 dfs
				// 음수 나오면 상태유지하고(설치x) 다음 dfs호출
			}
		}
		
		if(r==1) {
			int checkR = 0;
			int checkC = c - dc[direction];
			if(board[checkR][checkC]==0) flag = true;
			if(board[checkR][checkC]==1) flag2 = true;
		}else if(c==N-2) {
			int checkR = r - dr[direction];
			int checkC = N-1;
			if(board[checkR][checkC]==0) flag = true;
			if(board[checkR][checkC]==1) flag2 = true;
		}else if(r==N-2) {
			int checkR = N-1;
			int checkC = c - dc[direction];
			if(board[checkR][checkC]==0) flag = true;
			if(board[checkR][checkC]==1) flag2 = true;
		}else if(c==1) {
			int checkR = r - dr[direction];
			int checkC = 0;
			if(board[checkR][checkC]==0) flag = true;
			if(board[checkR][checkC]==1) flag2 = true;
		}
		
		if(!flag2) dfs(cnt+1, direction,r+dr[direction],c+dc[direction], install);
		
		if(!flag) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(i==1&&j==1)continue;
					board[r-drr[i]][c-dcc[j]] -= 1;
				}
			}
			dfs(cnt+1, direction,r+dr[direction],c+dc[direction], install+1);
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(i==1&&j==1)continue;
					board[r-drr[i]][c-dcc[j]] += 1;
				}
			}
		}		
		
		
		
	}
}
 