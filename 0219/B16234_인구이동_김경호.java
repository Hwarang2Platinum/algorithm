import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,L,R;
	static boolean[][] visited;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int day = 0;
		
		while(true) {
			flag = false;
			int[] populationUnion = new int[N*N+1];
			int[] countriesUnion = new int[N*N+1];
			visited = new boolean[N][N];
//			System.out.println("before==============");
//			for(int i=0;i<N;i++)
//				System.out.println(Arrays.toString(A[i]));
			int unionIdx = 1;
			for(int i=0;i<N;i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) continue;
					bfs(new Pos(i,j,A[i][j]), A, populationUnion,countriesUnion, unionIdx++);
				}
			}
			
			for(int i=0;i<N;i++) {
				for (int j = 0; j < N; j++) {
//					System.out.println("r: " + i + " c: "+j + " 인구수: "+populationUnion[A[i][j]]
//							+ " 연합수: " + countriesUnion[A[i][j]]);
//					System.out.println("연합 정보 "+A[i][j]);
					A[i][j] = populationUnion[A[i][j]] / countriesUnion[A[i][j]];
				}
			}
//			System.out.println("After==============");
//			for(int i=0;i<N;i++)
//				System.out.println(Arrays.toString(A[i]));
			if(!flag) break;
			else day++;
		}
		System.out.println(day);
		
	}
	private static void bfs(Pos start, int[][] A,int[] populationUnion,int[] countriesUnion,int unionIdx) {
		Queue<Pos> q  = new ArrayDeque<>();
		q.add(start);
		countriesUnion[unionIdx]++;
		populationUnion[unionIdx]+=A[start.r][start.c];
		visited[start.r][start.c] = true;
		A[start.r][start.c] = unionIdx;
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(--size>=0) {
				Pos p = q.poll();
				int r = p.r;
				int c = p.c;
				int curNum = p.num;
				
				for(int i=0;i<4;i++) {
					int nextR = r+dr[i];
					int nextC = c+dc[i];
//					System.out.println("=====================");
//					System.out.println("next R: " + nextR + " next C: "+ nextC);
//					System.out.println(A[nextR][nextC]+" - "+curNum);
//					System.out.println("=====================");
					if(isOut(nextR, nextC) || visited[nextR][nextC]) continue;
					
					int diff =  Math.abs(A[nextR][nextC] - curNum);
					if( diff< L || diff > R) continue;
					
					flag = true;
					// 연합에 나라 추가
					visited[nextR][nextC] = true;
					q.add(new Pos(nextR,nextC,A[nextR][nextC]));
					countriesUnion[unionIdx]++;
					populationUnion[unionIdx]+=A[nextR][nextC];
					A[nextR][nextC] = unionIdx;
				}
			}
			
		}
		
	}
	public static boolean isOut(int r, int c) {
		return r<0||r>N-1||c<0||c>N-1;
	}
	public static class Pos{
		int r, c, num;

		public Pos(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
		
	}
}