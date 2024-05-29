package algorithm;

import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static int[][] directions = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Play.register(new FireBall(r-1,c-1,m,s,d));
		}
		
		for(int i=0;i<K;i++) {
			Play.move();
			Play.divide();
		}
		System.out.println(Play.calMass());
	}
	
	public static class Play{
		static Queue<FireBall>[][] board= new ArrayDeque[N][N];
		
		Play(){
			super();
		}
		
		// board에 넣기.
		public static void register(FireBall ball) {
			if(board[ball.r][ball.c] == null) 
				board[ball.r][ball.c] = new ArrayDeque<>();
			board[ball.r][ball.c].add(ball);
		}
		
		// 움직이기.
		public static void move() {
			List<FireBall> list = new ArrayList<>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(board[i][j] == null||board[i][j].size()==0) continue;
					while(!board[i][j].isEmpty()) {
						FireBall ball = board[i][j].poll();
						
						// s가 N보다큰경우 고려 해야함.
						int nextr = i + (ball.s%N)*directions[ball.d][0];
						int nextc = j + (ball.s%N)*directions[ball.d][1];
						
						if(nextr>N-1) nextr %= N;
						if(nextc>N-1) nextc %= N;
						if(nextr<0) nextr = N + nextr;
						if(nextc<0) nextc = N + nextc;
						
						// 탐색도중에 board에 넣어버리면 이번에 들어간게 POP될 수 있기 때문에 list에 모았다가 나중에 넣음.
						FireBall tmp = new FireBall((char)nextr,(char)nextc,ball.m,ball.s,ball.d);
						list.add(tmp);
					}
				}
			}
			
			for(FireBall ball:list) {
				if(board[ball.r][ball.c]==null)
					board[ball.r][ball.c] = new ArrayDeque<>();
				board[ball.r][ball.c].add(ball);
			}
			
		}
		
		// 나누기. 한칸에 두개이상이면 정책에 따라서 4개로 나누어준다.
		public static void divide() {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(board[i][j] == null||board[i][j].size()==0) continue;
					
					// 2개이상 합쳐졌을 
					if(board[i][j].size()<2) continue;
					int m = 0;
					int s = 0;
					int odd = 0;
					int even = 0;
					int size = board[i][j].size();
					for(FireBall ball:board[i][j]) {
						m+=ball.m;
						s+=ball.s;
						if(ball.d%2==0) even++;
						else odd++;
					}
					board[i][j] = new ArrayDeque();
					
					m /= 5;
					if(m==0) continue;
					
					s /= size;
					
					int[] tmpDirections = null;
					if(odd==0 || even==0) tmpDirections = new int[]{0,2,4,6};
					else tmpDirections = new int[]{1,3,5,7};
					
					
					for(int k=0;k<4;k++) {
						board[i][j].add(new FireBall(i,j,m,s,tmpDirections[k]));
					}
				}
			}
		}
		
		// 질량 계
		public static int calMass() {
			int ret = 0;;
			for(Queue<FireBall>[] s: board) {
				for(Queue<FireBall> a:s) {
					if(a == null||a.size()==0) {
						continue;
					}
					for(FireBall i: a) {
						ret+=i.m;
					}
				}
			}
			return ret;
		}
		
	}
	
	public static class FireBall{
		char r,c,m,s,d;
		
		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = (char)r;
			this.c = (char)c;
			this.m = (char)m;
			this.s = (char)s;
			this.d = (char)d;
		}

		@Override
		public String toString() {
			return m+"";
		}
	}
	
}