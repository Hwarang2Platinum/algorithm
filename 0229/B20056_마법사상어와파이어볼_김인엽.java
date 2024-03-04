import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
// 160536	708
public class B20056_마법사상어와파이어볼_김인엽 {
	
	// 위부터 시계방향
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int N; // 맵 크기
	static FireBallMoongchi[][] arr; // 맵: 파이어볼 뭉치 갖고있음

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		arr = new FireBallMoongchi[N][N]; // 맵(각각은 파이어볼 뭉치가 존재가능)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = new FireBallMoongchi(new Pair(i, j));
			}
		}

		// 파이어볼 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			arr[x-1][y-1].fireballList.add(new FireBall(x-1, y-1, mass, speed, dir));
		}

		for (int i = 0; i < K; i++) {
			moveFireball(); // 명령하기
			merge(); // 합치기
		}

		System.out.println(remainMassSum());
	}

	// 남은 질량 합 출력
	public static int remainMassSum() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (FireBall fireball : arr[i][j].fireballList) {
					result += fireball.mass;
				}
			}
		}
		return result;
	}

	// 합치는거
	public static void merge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j].initIsMoved();
				// 두 개 이상의 파이어볼이 있다면, 합치고 나누기
				arr[i][j].mergeAndDivide();
			}
		}
	}

	// 명령하는거
	public static void moveFireball() {
		// 모든 파이어볼 이동
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				List<FireBall> tmpList = new ArrayList<>(arr[i][j].fireballList);
				for (FireBall fireBall : tmpList) {
					// 이번에 움직였으면 안움직임
					if(fireBall.isMoved) continue;
					fireBall.move();
				}
			}
		}
	}

	static class Pair {

		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 파이어볼 저장 클래스
	 *
	 * @value x, y: 좌표
	 * @value mass : 질량
	 * @value speed : 속도
	 * @value dir : 방향
	 */
	static class FireBall {

		Pair pair;
		int mass;
		int speed;
		int dir;
		boolean isMoved;

		public FireBall(int x, int y, int mass, int speed, int dir) {
			this.pair = new Pair(x, y);
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
		}

		void move() { // 이동하는 함수
			// 기존에 있던 곳에서 나가기
			arr[this.pair.x][this.pair.y].fireballList.remove(this);
			this.pair.x = (this.pair.x + dx[dir]*speed + N*1001) % N;
			this.pair.y = (this.pair.y + dy[dir]*speed + N*1001) % N;

			this.isMoved = true; // 움직였음을 나타냄
			// 새 위치에 추가
			arr[this.pair.x][this.pair.y].fireballList.add(this);
		}
	}

	static class FireBallMoongchi {
		Pair pair;
		List<FireBall> fireballList = new ArrayList<>(); // 파이어볼들
		
		public FireBallMoongchi(Pair pair) {
			this.pair = pair;
		}
		
		
		// isMoved false로 해주기
		public void initIsMoved() {
			for(FireBall fireball: fireballList) {
				fireball.isMoved = false;
			}
		}

		void mergeAndDivide() {
			// 2개 미만은 그냥 암것도 안하기
			if(fireballList.size() < 2) return;
			
			int massSum = 0; // 질량 합
			int speedSum = 0; // 속력 합
			int evenCnt = 0, oddCnt = 0; // 짝수, 홀수 카운트
			int size = fireballList.size(); // 현재 사이즈
			
			for (FireBall fireBall : fireballList) {
				massSum += fireBall.mass;
				speedSum += fireBall.speed;
				if (fireBall.dir % 2 == 0)
					evenCnt++;
				else
					oddCnt++;
			}

			fireballList.clear(); // 초기화

			if (massSum < 5) { // 질량이 0인 파이어볼 소멸 : 모든 질량 합이 5보다 낮으면 사라짐
				return;
			}

			// 1. 질량 정하기
			int mass = massSum / 5; // 새 질량
			// 2. 속도 구하기
			int speed = speedSum / size;

			// 3. 방향 구하기
			int[] dirs = (evenCnt == 0 || oddCnt == 0) ? new int[] { 0, 2, 4, 6 } : new int[] { 1, 3, 5, 7 };

			// 네 파이어볼로 나누기
			for (int dir : dirs) {
				fireballList.add(new FireBall(pair.x, pair.y, mass, speed, dir));
			}
		}
	}
}
