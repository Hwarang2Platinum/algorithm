import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2583
 * 예상 풀이 방법 : bfs + 정렬을 위해 Priority Queue
 * 안 쓰면 184 -> pq 쓰면 76
 * shout out to 경호, 종호
 */
public class B2583_영역구하기_김인엽 {
	static int N, M, K;
	static PriorityQueue<Integer> areas = new PriorityQueue<>(); // 넓이 !

	static int[] dx = {0, 1, 0, -1}; // 방향
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new boolean[M][N]; // 배열

		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken()); // x, y 바꿔서 받기
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());

			for(int j = x1; j < x2; j++) {
				for(int k = y1; k < y2; k++) {
					arr[j][k] = true; // 그린 곳 색칠해놓기
				}
			}
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j])
					continue; // 색칠된 곳은 건너뛰기
				bfs(i, j); // 색칠 안 됐으면 탐색
			}
		}

		bw.write(areas.size() + "\n");
		while(!areas.isEmpty()) {
			bw.write(areas.poll() + " ");
		}
		bw.close();
	}

	static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x, y});
		arr[x][y] = true;
		int tmpSum = 0;

		while(!queue.isEmpty()) {
			int[] pair = queue.poll();
			tmpSum++;

			for(int i = 0; i < 4; i++) {
				int cx = pair[0] + dx[i];
				int cy = pair[1] + dy[i];
				if(!isInArr(cx, cy))
					continue; // 영역 체크
				if(arr[cx][cy])
					continue; // 방문 or 직사각형 그림
				queue.add(new int[] {cx, cy});

				arr[cx][cy] = true;
			}
		}

		areas.add(tmpSum);
	}

	static boolean isInArr(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

	static void printArr() {
		System.out.println();
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
