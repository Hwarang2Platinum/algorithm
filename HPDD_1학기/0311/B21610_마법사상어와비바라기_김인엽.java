import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B21610_마법사상어와비바라기_김인엽 {
  static int N; // 맵 크기
  static int[][] arr; // 맵
  static int[] dx = {0, 0,-1,-1,-1,0,1,1,1}; // 방향 (하나씩 더 추가)
  static int[] dy = {0, -1,-1,0,1,1,1,0,-1};
  static List<Cloud> clouds = new ArrayList<>(); // 구름
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    arr = new int[N][N];
    // 맵 채우기
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    // 최초 구름
    clouds.add(new Cloud(N-1, 0));
    clouds.add(new Cloud(N-1, 1));
    clouds.add(new Cloud(N-2, 0));
    clouds.add(new Cloud(N-2, 1));

    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      for(Cloud cloud : clouds) {
        cloud.move(d, s); // 1. 구름 이동
        cloud.rain(); // 2. 비가 내린다 주륵주륵
      }
      for(Cloud cloud : clouds) {
        cloud.waterCopyBug(); // 4. 물복사버그 마법 시전
        cloud.stamp(); // 구름이 있던자리라고 표시하기
      }
      clouds = new ArrayList<>(); // 구름 삭제(물복사를 먼저 처리하는게 더 편리해서 순서 바꿈)
      generateClouds(); // 구름 발생
    }
    System.out.println(calcSum());
  }
  /**
   * 물의 양 합 구하기
   */
  static int calcSum() {
    int sum = 0;
    for(int[] a : arr) {
      sum += Arrays.stream(a).sum();
    }
    return sum;
  }

  /**
   * 구름 만들기
   * 1. 물의 양이 2 이상인 칸만
   * 2. 해당 이동에서 구름이 있던 자리는 제외
   */
  static void generateClouds() {
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        // 구름이 있던 자리는 원상복구만 시키기
        if(arr[i][j] < 0) {
          arr[i][j] = -arr[i][j];
        } else if (arr[i][j] >= 2) { // 2 이상인 칸에 구름 생기기
          arr[i][j] -= 2; // 물의 양 2 줄어들기
          clouds.add(new Cloud(i, j));
        }
      }
    }
  }
  /**
   * 구름
   */
  static class Cloud {
    int x, y;

    public Cloud(int x, int y) {
      this.x = x;
      this.y = y;
    }

    /**
     * direction 방향으로 speed 만큼 이동
     * @param direction : 방향
     * @param speed : 거리
     */
    void move(int direction, int speed) {
      x = (x + dx[direction]*speed + (speed+1)*N) % N; // (speed+1)*N을 더하면 무조건 음수가 아님
      y = (y + dy[direction]*speed + (speed+1)*N) % N;
    }

    /**
     * 비가 내려 구름이 있는 칸의 바구니에 저장된 물 증가
     */
    void rain() {
      arr[x][y]++;
    }

    /**
     * 물 복사 버그 마법
     * 대각선 방향 칸에 물이 있는 바구니 수만큼 물 증가
     * 경계를 넘어가는 칸은 생각X
     */
    void waterCopyBug() {
      int[] crossX = {1,1,-1,-1};
      int[] crossY = {-1,1,-1,1};
      int waterCnt = 0;

      for(int i=0; i<crossX.length; i++) {
        int cx = x + crossX[i];
        int cy = y + crossY[i];
        // 범위 벗어나면 패스
        if(cx < 0 || cx >= N || cy < 0 || cy >= N) continue;
        // 물이 안담겨있으면 패스
        if(arr[cx][cy] == 0) continue;
        waterCnt++;
      }
      // 물 담겨있는 수만큼 물복사 버그!
      arr[x][y] += waterCnt;
    }

    /**
     * 현재 자리가 구름이 있던 자리임을 나타내기
     */
    void stamp() {
      arr[x][y] = -arr[x][y];
    }
  }
}
