import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] rMask = {-1,0,1,0};
	static int[] cMask = {0,1,0,-1};
	static int[][] room;
	static int N,M;
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int startR = Integer.parseInt(st.nextToken());
		int startC = Integer.parseInt(st.nextToken());
		int direc = Integer.parseInt(st.nextToken());
		
		room = new int[N][];
		
		for(int i = 0 ;i<N; i++) {
			room[i] = br.readLine().chars().map(o->o-48).filter(o->o>=0).toArray();
		}
		
		dfs(1,startR,startC,direc);
		
		System.out.println(ans);
	}
	// 
	static void dfs(int depth, int r, int c, int curDirection) {
//		System.out.println(r+" "+c);
		ans = Math.max(ans, depth);
		//if dirty cleaning
		if(room[r][c] == 0) {
			room[r][c] = 2;
		}
		int cnt = 5;
		int tmp = curDirection-1;
		for(int i=0;i<4;i++) {
			if(tmp==-1) tmp=3;
			int nextr = r+rMask[tmp];
			int nextc = c+cMask[tmp];
			if(room[nextr][nextc] == 0) {
				cnt = tmp;
				break;
			}
			tmp--;
		}
		// 2. 주변에 청소되지 않은 빈칸이 있는 경우 => 90도 회전 (반시계
		if(cnt!=5) {
			dfs(depth+1, r+rMask[cnt], c+cMask[cnt], cnt);
		}
		// 1. 주변에 청소되지 않은 빈칸이 없는 경우 => 후진
		else {
			if(r-rMask[curDirection]<0 || r-rMask[curDirection]>=N 
					|| c-cMask[curDirection]<0 || c-cMask[curDirection]>=M
					|| room[r-rMask[curDirection]][c-cMask[curDirection]] == 1) return;
			dfs(depth, r-rMask[curDirection], c-cMask[curDirection], curDirection);
		}
		// 	회전 후 청소해야되면 전진 아니면 회전 => 최초 청소지점까지 회전
	}

}
