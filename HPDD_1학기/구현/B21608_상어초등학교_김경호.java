import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21608_상어초등학교_김경호 {
	static int N;
	static int[][] room;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		room = new int[N+1][N+1]; // 학생번호, 좋아하는 하
		Position[] students = new Position[N*N+1];
		int[][] friends = new int[N*N+1][4];
		int[][] favorites;
		
		// 1. 좋아하는 학생이 인접한 칸에 가장 많은 칸
		// 2. 인접한 칸중 비어있는 칸이 가장 많은 칸
		// 3. 행열 작은 순
		for(int t=0;t<N*N;t++) {
			st = new StringTokenizer(br.readLine());
			int studentNum = Integer.parseInt(st.nextToken());
			int maxF = Integer.MIN_VALUE;
			int maxB = Integer.MIN_VALUE;
			int maxR = 0, maxC = 0;
			favorites = new int[N+1][N+1];
			for(int k=0;k<4;k++) {
				int friend = Integer.parseInt(st.nextToken());
				friends[studentNum][k] = friend;
				if(students[friend] == null) continue;
				for(int i=0;i<4;i++) {
					int R = students[friend].r - dr[i];
					int C = students[friend].c - dc[i];
					if(R<1||R>N||C<1||C>N||room[R][C]!=0) continue;
					favorites[R][C]++;
				}
			}
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(room[i][j]!=0) continue;
					int blankNum = 4;
					if((i==1||i==N)&&(j==1||j==N)) {
						blankNum -= 2;
					}
					else if((i==1||i==N)||(j==1||j==N)) {
						blankNum -= 1;
					}
					for(int p=0;p<4;p++) {
						int R = i - dr[p];
						int C = j - dc[p];
						if(R<1||R>N||C<1||C>N) continue;
						if(room[R][C]!=0) blankNum--;
					}
//					System.out.println("R="+i+" C="+j+ " f="+favorites[i][j]+" b="+blankNum);
					if(maxF < favorites[i][j]) {
						maxF = favorites[i][j];
						maxB = blankNum;
						maxR = i;
						maxC = j;
					}else if(maxF == favorites[i][j]) {
						if(maxB < blankNum) {
							maxB = blankNum;
							maxR = i;
							maxC = j;
						}
					}
				}
			}

			students[studentNum] = new Position(maxR, maxC);
			room[maxR][maxC] = studentNum;
		}
		
		int score = 0;
		
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				int tmpF = 0;
				// i,j 위치의 학생의 친구들 friends[room[i][j]]
				for(int friend: friends[room[i][j]]) {
					for(int q=0;q<4;q++) {
						if(i+dr[q]==students[friend].r && j+dc[q]==students[friend].c){
							tmpF++;
						}
					}
				}
				if(tmpF==0) continue;
				score+=Math.pow(10, tmpF-1);
//				System.out.print(room[i][j]+" ");
			}
//			System.out.println();
		}
		System.out.println(score);
	}
	public static class Position{
		int r,c;

		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
}

			
	
