package algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ2667_KH {
	static int N;
	static int[][] board;
	static boolean[][] arr;
	static int ans = 0;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int idx = 0;
	static ArrayList<Integer> al = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		board= new int[N][];
		arr = new boolean[N][N];
		for(int i=0;i<N;i++) {
			
			String line = br.readLine();
			int[] ints = new int[N];
			Arrays.setAll(ints, k -> Character.getNumericValue( line.toCharArray()[k]));
			board[i] = ints;
		}
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!arr[i][j] && board[i][j] > 0) {
					al.add(0);
					dfs(i,j);
					idx++;
					ans++;

				}
			}
		}
		System.out.println(ans);
		Collections.sort(al);
		for(int t:al) {
			if(t!=0) System.out.println(t);
		}
		
	}
	
	public static void dfs(int r, int c) {
		al.set(idx, al.get(idx)+1);
		arr[r][c] = true;
		for(int i=0; i<4;i++) {
			int next_r = r+dx[i];
			int netx_c = c+dy[i];
			if(next_r<N && netx_c<N && next_r>=0 && netx_c>=0
					&&!arr[next_r][netx_c] &&board[next_r][netx_c] > 0 ) {
				dfs(next_r, netx_c);
			}
		}
	}
}
