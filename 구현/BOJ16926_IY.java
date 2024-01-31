import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ16926_IY {
  private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int n; // 세로
  static int m; // 가로
  static int r; // 돌리는 횟수
  static int[][] arr; // 배열
  static boolean[][] visited; // 방문여부
  static int[][] answer; // 정답

  static Deque<Integer> queue = new ArrayDeque<>(); // 돌릴 숫자들 담은 큐

  // 아래, 오른쪽, 위, 왼쪽
//  static int[] dx = { 1, 0, -1, 0 };
  static int[] dx = { 0, 1, 0, -1 };
  //  static int[] dy = { 0, 1, 0, -1 };
  static int[] dy = { 1, 0, -1, 0 };

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    arr = new int[n][m];
    visited = new boolean[n][m];
    answer = new int[n][m];

    r = Integer.parseInt(st.nextToken()); // 회전 수

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 큐 채우기 -> 횟수만큼 큐 돌리기
    for (int i = 0; i < Math.min(n,m)/2; i++) {
      solution(i, i, 0);
      rotate();
      fillAnswer(i, i, 0);
    }
    for(int i=0; i<n; i++) {
      for(int j=0; j<m; j++) {
        bw.write(answer[i][j] + " ");
      }
      bw.write("\n");
    }
    br.close();
    bw.close();
  }

  private static void rotate() {
    // 네방향 다 돌았으면 끝. 큐 돌리기
    int size = queue.size();
    int rotate = r % size; // 큰 수가 나오면 줄이기위해. 어차피 r만큼은 도돌이표니까
    for(int i=0; i<rotate; i++) queue.add(queue.poll());
  }

  private static void solution(int x, int y, int dirIdx) {
    if(x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) {
      // 끝까지 갔다면 돌아가서 dirIdx 올리기
      x -= dx[dirIdx];
      y -= dy[dirIdx];
      dirIdx++;
      if(dirIdx == dx.length) {
        return;
      }

      x += dx[dirIdx];
      y += dy[dirIdx];
    }
    if(visited[x][y]) {
      return;
    }
    // 방문처리
    visited[x][y] = true;
    // 큐에 넣기
    queue.add(arr[x][y]);
    x += dx[dirIdx];
    y += dy[dirIdx];

    solution(x, y, dirIdx);
  }

  private static void fillAnswer(int x, int y, int dirIdx) {
    if(x < 0 || x >= n || y < 0 || y >= m || answer[x][y] != 0) {
      // 끝까지 갔다면 돌아가서 dirIdx 올리기
      x -= dx[dirIdx];
      y -= dy[dirIdx];

      dirIdx++;
      if(dirIdx == dx.length) {
        // 네방향 다 돌았으면 끝.
        return;
      }
      x += dx[dirIdx];
      y += dy[dirIdx];
    }
    // answer에 넣기
    if(!queue.isEmpty()) answer[x][y] = queue.poll();

    x += dx[dirIdx];
    y += dy[dirIdx];
    fillAnswer(x, y, dirIdx);

  }
}