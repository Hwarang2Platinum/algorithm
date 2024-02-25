import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 	88196	352
public class B14502_연구소_김인엽 {
  static int N, M, arr[][]; // 세로, 가로
  static int wallCnt; // 벽 개수
  static int minVirusCnt = Integer.MAX_VALUE;
  static List<Pair> blanks = new ArrayList<>();
  static boolean[] selected;
  static Pair[] pairs;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[N][M]; // 연구소
    // 입력 처리
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        int tmp = Integer.parseInt(st.nextToken());
        arr[i][j] = tmp;
        if(tmp == 0) blanks.add(new Pair(i, j)); // 빈칸 추가
        if(tmp == 1) wallCnt++; // 벽 개수 세기
      }
    }
    selected = new boolean[blanks.size()]; // 방문여부
    pairs = new Pair[3]; // 조합
    // 조합 만들기 -> 다만들면 검사하기
    makeCombination(0, 0);

    System.out.println(N*M - (wallCnt + 3) - (minVirusCnt));
  }

  private static void check(Pair[] pairs) {
    int[][] newArr = new int[N][M]; // 복사하기

    Queue<Pair> queue = new ArrayDeque<>();

    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(arr[i][j] == 2) queue.add(new Pair(i, j)); // 큐 채우기
        newArr[i][j] = arr[i][j];
      }
    }
    // 벽 세개 만들기
    for(Pair pair: pairs) {
      newArr[pair.x][pair.y] = 1;
    }
    // 방향 배열
    int[] dx = {0,0,-1,1};
    int[] dy = {1,-1,0,0};

    int virusCnt = 0;
    // bfs 시작(바이러스 채우기)
    while(!queue.isEmpty()) {
      Pair virus = queue.poll();
      virusCnt++;
      if(virusCnt > minVirusCnt) return; // 이미 바이러스 개수가 더 많으면 끝

      for(int i=0; i<dx.length; i++) {
        int cx = virus.x + dx[i];
        int cy = virus.y + dy[i];
        if(cx < 0 || cx >= N || cy < 0 || cy >= M) continue; // 범위 처리
        if(newArr[cx][cy] == 0) {
          newArr[cx][cy] = 2; // 감염 처리
          queue.add(new Pair(cx,cy));
        }
      }
    }
    minVirusCnt = Math.min(minVirusCnt, virusCnt);
  }

  private static void makeCombination(int cnt, int start) {
    if(cnt == 3) {
      check(pairs);
      return;
    }

    for(int i=start; i<blanks.size(); i++) {
      if(selected[i]) continue;
      pairs[cnt] = blanks.get(i);
      selected[i] = true;
      makeCombination(cnt+1, i);
      selected[i] = false;
    }
  }

  static class Pair {
    int x, y;

    public Pair() {
    }

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "Pair{" +
          "x=" + x +
          ", y=" + y +
          '}';
    }
  }
}
