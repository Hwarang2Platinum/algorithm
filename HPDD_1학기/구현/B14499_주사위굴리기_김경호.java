import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14499_주사위굴리기_김경호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dr = {0,0,0,-1,1};
		int[] dc = {0,1,-1,0,0};
		
		int[][] board = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] operators = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		// 동 서 북 남
		// 동쪽으로 가면 서쪽이 북쪽이되고 동쪽이 남쪽이된다.
		// 서쪽으로 가면 동쪽이 북쪽이되고 서쪽이 남쪽이된다.
		int[] diceUp = {0,6,5,4,3,2,1};
		int[] matching = {0,0,0,0,0,0,0};
		int[] diceState = {0,1,2,3,4,5,6};
		int down = 6;
		// 0 이면 down 복사 , 0 이 아니면 칸에서 down으로 복사.
		for(int op:operators) {
			if(x+dr[op]<0||x+dr[op]>N-1||y+dc[op]<0||y+dc[op]>M-1) continue;
			x += dr[op];
			y += dc[op];
			if(op==1) down = diceState[3];
			if(op==2) down = diceState[4];
			if(op==3) down = diceState[2];
			if(op==4) down = diceState[5];
//			System.out.println("현재 밑:"+down+"현재 위:"+diceUp[down]+"\n x:"+x+" y:"+y+" 좌표에 새겨진 값: "+board[x][y]);
			if(board[x][y]==0) {
				board[x][y] = matching[down];
			}else {
				matching[down] = board[x][y];
				board[x][y] = 0;
			}
			if(op==1) diceState = toEast(diceState);
			if(op==2) diceState = toWest(diceState);
			if(op==3) diceState = toNorth(diceState);
			if(op==4) diceState = toSouth(diceState);
//			System.out.println(Arrays.toString(matching));
			System.out.println(matching[diceUp[down]]);
		}
 		//
	}
	static int[] toEast(int[] diceState) {
		return new int[] {0,diceState[3],diceState[2],diceState[6],diceState[1],diceState[5],diceState[4]};
	}
	static int[] toWest(int[] diceState) {
		return new int[] {0,diceState[4],diceState[2],diceState[1],diceState[6],diceState[5],diceState[3]};
	}
	static int[] toSouth(int[] diceState) {
		return new int[] {0,diceState[5],diceState[1],diceState[3],diceState[4],diceState[6],diceState[2]};
	}
	static int[] toNorth(int[] diceState) {
		return new int[] {0,diceState[2],diceState[6],diceState[3],diceState[4],diceState[1],diceState[5]};
	}
	
}

			
	
