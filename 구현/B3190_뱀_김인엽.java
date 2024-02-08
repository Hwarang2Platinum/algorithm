import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B3190_뱀_김인엽 {

	private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 우 -> 하 -> 좌 -> 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };
	// private static int headX, headY; // 머리
	private static int tailX = 1, tailY = 1; // 꼬리
	private static int headDi, tailDi; // 방향

	private static int[][] arr; // 보드
	private static int N; // 보드 크기
	private static int second = 1; // 현재 시간
	private static int[] changesTime; // 변환 시간 정보
	private static boolean[] changesDir; // 변환 방향 정보
	private static int changeIdx; // 변환 시간, 방향 정보 인덱스

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1]; // 편리한 인덱싱을 위해 +1

		// 사과 채우기
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = -1; // 사과는 -1로 표시
		}

		// 방향 변환 횟수 채우기(x초가 끝난뒤 바뀌기 때문에)
		int L = Integer.parseInt(br.readLine());
		changesTime = new int[L];
		changesDir = new boolean[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			changesTime[i] = Integer.parseInt(st.nextToken());
			changesDir[i] = st.nextToken().equals("L"); // L -> true / D -> false
		}
		// 최초 시작점인 1,1 에서 기어가기 시작!
		arr[1][1] = 1;
		crawl(1, 2);
		System.out.println(second);
	}

	private static void crawl(int x, int y) {
		// 종료 조건
		// 벽 체크
		if (x < 1 || x > N || y < 1 || y > N) {
			return;
		}
		// 몸통 체크(몸통: 1, 방향바뀌는 몸통은 Di+10)
		if (arr[x][y] > 0) {
			return;
		}

		// 헤드 차지(비어있으면 차지: 1)
		if (arr[x][y] == 0) {
			arr[x][y] = 1;
		}
		
		boolean isApple = arr[x][y] == -1;
		int tmpValue = 0;
		
		// 방향 변환 체크
		if (second == changesTime[changeIdx]) {
			// L일 때
			if (changesDir[changeIdx]) {
				headDi = headDi > 0 ? headDi - 1 : 3;
			} else { // D 일 때
				headDi = (headDi + 1) % 4;
			}
			// 방향 변환 다음꺼 보기
			changeIdx = (changeIdx + 1) % changesTime.length;
			// 몸통 부분 -> 방향 바뀌는 몸통으로 교체
//			arr[x][y] = headDi + 10;
			tmpValue = headDi + 10;
		}

		// 사과 체크
		// 사과가 아니라면
		if (!isApple) {
			// 꼬리가 방향이 바뀌는 부분이라면 꼬리 방향 바꾸기
			if (arr[tailX][tailY] >= 10) { // 방향 바뀌는 몸통은 Di+10 >= 10이므로
				tailDi = arr[tailX][tailY] - 10;
			}
			arr[tailX][tailY] = 0; // 꼬리 짜르기
			// 새 꼬리 찾기
			tailX += dx[tailDi];
			tailY += dy[tailDi];
		} else { // 사과라면 먹고 차지하기
			arr[x][y] = 1;
		}
		
		if(tmpValue > 0) arr[x][y] = tmpValue;
//		print(second + "초");
		// 시간 증가
		second++;
		// 다음으로 이동
		crawl(x + dx[headDi], y + dy[headDi]);
	}
	private static void print(String s) {
		System.out.println(s);
		for(int[] a: arr) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
	}
}