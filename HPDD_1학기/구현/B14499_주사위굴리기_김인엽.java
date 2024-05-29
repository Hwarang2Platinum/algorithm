import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 14880	176
public class B14499_주사위굴리기_김인엽 {
	static class Dice {
		// 좌표
		public int x, y;
		// 각각각각
		public int up, down, east, west, north, south;
		
		public Dice() {
			up=down=east=west=north=south=0;
		}
		
		void goEast() {
			int tmp = this.down;
			this.down = this.east;
			this.east = this.up;
			this.up = this.west;
			this.west = tmp;
		} 
		void goWest() {
			int tmp = this.down;
			this.down = this.west;
			this.west = this.up;
			this.up = this.east;
			this.east = tmp;
		} 
		void goNorth() {
			int tmp = this.down;
			this.down = this.north;
			this.north = this.up;
			this.up = this.south;
			this.south = tmp;
		} 
		void goSouth() {
			int tmp = this.down;
			this.down = this.south;
			this.south = this.up;
			this.up = this.north;
			this.north = tmp;
		} 
	}
	static int N, M;
	static int[][] arr;
	// 동서북남(인덱스0은 무시)
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	static Dice dice = new Dice();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		
		dice.x = Integer.parseInt(st.nextToken());
		dice.y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				arr[i][j] = tmp;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int order = Integer.parseInt(st.nextToken());
			move(order);
		}
	}

	private static void move(int order) {
		// 주사위 이동
		dice.x += dx[order];
		dice.y += dy[order];
		
		// 범위 벗어나면 후퇴
		if(dice.x < 0 || dice.x >= N || dice.y < 0 || dice.y >= M) {
			dice.x -= dx[order];
			dice.y -= dy[order];
			return;
		}
		
		switch (order) {
		case 1: // 동
			dice.goEast();;
			break;
		case 2: // 서
			dice.goWest();
			break;
		case 3: // 북
			dice.goNorth();
			break;
		case 4: // 남
			dice.goSouth();
			break;
		}
		
		// 이동한 칸의 수 확인
		// 0이면, 바닥면 수 복사
		if(arr[dice.x][dice.y] == 0) {
			arr[dice.x][dice.y] = dice.down;
		} else { // 0이 아니면 칸의 수를 바닥면에 복사 -> 0으로 바꾸기
			dice.down = arr[dice.x][dice.y];
			arr[dice.x][dice.y] = 0;
		}
		// 출력
		System.out.println(dice.up);
	}
	
}
