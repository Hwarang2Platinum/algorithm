package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B20055_컨베이어벨트위의로봇_김인엽 {
	static int N; // 컨베이어 한 줄 크기
	static int[] arr; // 컨베이어 벨트 내구도
	static List<Integer> robots = new ArrayList<>(); // 로봇 위치
	static int curIdx; // 현재 첫칸을 가리키는 인덱스
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[2*N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());

		int answer = 1;

		while(true) {
//			System.out.println(robots);
//			System.out.println("=============" + answer + " 단계 =============");
			// 1. 벨트 회전
			rotate();
//			print("벨트 회전");
			downRobot();
			// 2. 로봇 이동
			moveRobot();
//			print("로봇 이동");

			// 3. 로봇 올릴 수 있으면 올리기
			putRobot();
//			print("로봇 올리기");
			// 4. 내구도 0인 칸 개수 확인 후 종료
			if(numOfZero() >= K) break;
			answer++;
		}
		
		System.out.println(answer);
	}

	private static void downRobot() {
		for(int i=0; i<robots.size(); i++) {
			if(robots.get(i) == -1) continue;
			if(robots.get(i) == curIdx + arr.length/2 - 1) {
				robots.set(i, -1);
				break;
			}
		}
	}

	public static void print(String s) {
		System.out.println(s + " : ");
		System.out.println(Arrays.toString(arr));
		System.out.println("현재 인덱스 : "+ curIdx);
		System.out.println("로봇: " + robots);
	}

	/**
	 * robot 올리기
	 * - 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리기
	 */
	private static void putRobot() {
		// 내구도가 0이면 끝
		if(arr[curIdx] == 0) return;
		// 내구도 있으면, 올리기
		robots.add(curIdx);
		// 해당 위치 내구도 내리기
		arr[curIdx]--;
	}

	/**
	 * 로봇 이동
	 * 먼저 벨트에 올라간 로봇부터, 회전 방향으로 이동할 수 있으면 한 칸 이동 가능
	 * 1. 이동하려는 칸에 로봇이 없어야함
	 * 2. 내구도가 1이상 남아있어야함
	 * 이동이 끝나면 해당 위치 내구도도 내리기
	 */
	private static void moveRobot() {
		for(int i=0; i<robots.size(); i++) {
			if(robots.get(i) == -1) continue;
			// 다음칸
			int next = (robots.get(i) + 1) % arr.length;
			// 다음칸에 로봇 있는지 확인
			if(robots.stream().anyMatch(r -> r == next))
				continue;
			// 내구도가 1 이상 남았는지 확인
			if(arr[next] == 0)
				continue;
			// 내리는 위치라면 내리기
			if(next == curIdx + arr.length/2 - 1) {
				robots.set(i, -1);
				continue;
			}
			// 다 통과했으면 이동
			robots.set(i, next);
			// 해당 위치 내구도 내리기
			arr[next]--;
		}
	}

	/**
	 * 내구도 0인 칸 개수 세기
	 * @return 내구도 0인 칸 개수
	 */
	private static int numOfZero() {
		return (int) Arrays.stream(arr).filter(a -> a == 0).count();
	}
	
	/**
	 * 컨테이너 돌리기 -> N번쨰에 있는 친구 없애기
	 */
	private static void rotate() {
		// 벨트 돌리기
		curIdx = ((curIdx-1) + arr.length) % arr.length;
	}

}
