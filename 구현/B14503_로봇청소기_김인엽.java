import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//	14344	128
public class B14503_로봇청소기_김인엽 {
  static int N, M; // 그래프 크기
  static int[][] arr; // 그래프
  static int answer; // 청소 횟수
  static int[] dx = {-1,0,1,0}; // 북, 서, 남, 동 -> 반시계 90도에 맞춰 설정
  static int[] dy = {0,-1,0,1};
  static int[] dxdyMapping = {0,3,2,1}; // d의 인덱스에 해당하는 dx, dy 맞추기 예시) 0 -> dx[0]을 보기. 1->dx[3]을 보기
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[N][M];

    st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());

    // 그래프 채우기
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dfs(r, c, dxdyMapping[d]); // 현재 위치, dxdy index

    System.out.println(answer);
  }

  private static void dfs(int x, int y, int dirIdx) {
    if(arr[x][y] == 0) { // 청소가 가능하면
      arr[x][y] = 2; // 청소
      answer++; // 청소 횟수 증가
    } else if(arr[x][y] == 1) { // 벽이라면
      return; // 작동 멈춤
    }

    int cx=0, cy=0, curIdx=0;
    boolean canClean = false;
    for(int i=1; i<=4; i++){
      curIdx = (dirIdx+i)%dx.length; // 반시계 90도를 돌린 방향
      cx = x + dx[curIdx];
      cy = y + dy[curIdx];
      if(cx < 0 || cx >= N || cy < 0 || cy >= M) continue; // 범위를 벗어나면 패스

      if(arr[cx][cy] != 0) continue; // 벽이거나 청소했으면 건너뛰기

      canClean = true;
      break;
    }
    // 청소할수있으면 이동
    if(canClean) {
      dfs(cx,cy, curIdx);
    } else {
      // 청소되지 않은 빈칸이 없는경우 후진&방향은 그대로
      dfs(x-dx[dirIdx], y-dy[dirIdx], dirIdx);
    }

  }
}
