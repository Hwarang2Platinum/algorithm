import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3190_뱀_김경호 {
	static int N,K,L;
	static int[][] board;
	static String[] directions;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		
		for(int i=0;i<K;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			board[r-1][c-1] = 2;
		}
		
		L = Integer.parseInt(br.readLine());
		directions = new String[10000];
		
		for(int i=0;i<L;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			directions[x] = c;
		}
		
		int headR = 0;
		int headC = 0;
		board[0][0] = 1;
		Queue<int[]> bam = new ArrayDeque<int[]>();
		bam.add(new int[] {0,0});
		
		int time = 0;
		
		int direction = 0;
		int[] directionR = {0,1,0,-1};
		int[] directionC = {1,0,-1,0};
		
		while(true) {
//			System.out.println(+time+": 현재위치 :(" + headR+","+ headC+")");
//			for(int i=0;i<N;i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
			
			int nextR = directionR[direction];
			int nextC = directionC[direction];
			headR = headR+nextR;
			headC = headC+nextC;
			
			if(isOut(headR,headC) || board[headR][headC] == 1) {
				System.out.println(time+1);
				break;
			}
			
			if(board[headR][headC] != 2) {
				int[] rc = bam.poll();
				board[rc[0]][rc[1]] = 0;
			}
			
			board[headR][headC] = 1;
			bam.add(new int[] {headR,headC});
			
			if(directions[++time] != null) {
//				System.out.println("방향전환 전 !"+ direction );
				if(directions[time].equals("D")) {
					direction = (direction+1)%4;
				}else {
					direction = (direction+3)%4;
				}
//				System.out.println("방향전환 후 !"+ direction);
			}
			
//			time++;
		}
		
		
	}
	public static boolean isOut(int r, int c) {
		return !(r<N && r>=0 && c<N && c>=0);
	}
	
}













