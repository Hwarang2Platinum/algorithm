import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B16236_아기상어_김인엽 {
  static class BabyShark {
    Position position = new Position(); // 아기상어 위치
    int size = 2; // 아기상어 크기 (초기값 2)
    int eatCnt = 0; // 먹은 물고기
    PositionDist target; // 타겟 물고기

    void growUp() { // 성장하기
      this.size++; // 사이즈 증가
      this.eatCnt = 0; // 먹은 물고기 수 초기화
    }
    void eat() { // 물고기 먹기
      target = null; // 타겟 초기화
      arr[this.position.x][this.position.y] = 0; // 해당 칸의 물고기 없애기
      this.eatCnt++; // 먹은 물고기 수 증가
      if(this.eatCnt == this.size) growUp(); // 먹은 수와 상어 크기가 같다면 성장!
    }
    void move2Target() { // 해당 타겟으로 이동
      arr[this.position.x][this.position.y] = 0; // 있던 자리 비우기
      this.position = this.target.position;
      time += this.target.dist; // 시간 업데이트
    }
    void setTarget() {
      Queue<PositionDist> queue = new ArrayDeque<>();
      boolean[][] visited = new boolean[N][N]; // 방문 여부
      queue.add(new PositionDist(this.position, 0)); // 현재 상어 위치
      PositionDist target = new PositionDist(null, Integer.MAX_VALUE);

      while(!queue.isEmpty()) {
        PositionDist pd = queue.poll();

        if(pd.dist > target.dist) continue; // 이미 거리가 더 지났다면, 패스
        // 만약 먹을 수 있다면, 타겟 후보로 선정
        if(arr[pd.position.x][pd.position.y] > 0 && arr[pd.position.x][pd.position.y] < this.size) {
          if(target.dist > pd.dist) { // 거리가 짧다면 교체
            target = pd;
          } else { // =(target.dist == pd.dist) { // 거리가 같다면,
            if(target.position.x > pd.position.x) target = pd; // 더 위에 있으면 교체
            else if(target.position.x == pd.position.x && target.position.y > pd.position.y) target = pd; // 그마저도 같다면, 더 왼쪽에 있으면 교체
          }
          continue;
        }

        for(int i=0; i<dx.length; i++) {
          int cx = pd.position.x + dx[i];
          int cy = pd.position.y + dy[i];
          // 위치 벗어나면 패스
          if(cx < 0 || cx >= N || cy < 0 || cy >= N) continue;
          // 나보다 큰 물고기가 있으면 패스
          if(arr[cx][cy] > this.size) continue;
          // 이미 지나친 곳이면 패스
          if(visited[cx][cy]) continue;
          visited[cx][cy] = true;
          // 새 위치, 기존 거리+1 해서 큐에 삽입
          queue.add(new PositionDist(new Position(cx, cy), pd.dist+1));
        }
      }

      this.target = target;
    }
  }
  static class PositionDist {
    Position position;
    int dist;

    public PositionDist(Position position, int dist) {
      this.position = position;
      this.dist = dist;
    }

  }

  static class Position {
    int x, y;
    public Position() {};

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int[][] arr; // 맵
  static int N; // 맵 크기
  static int time; // 시간
  static int[] dx = {-1,0,1,0}; // 상좌하우 : 상, 좌 일수록 우선순위가 높아서.
  static int[] dy = {0,-1,0,1};
  static BabyShark babyShark = new BabyShark(); // 아기상어
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N][N];

    StringTokenizer st;
    for(int i=0; i<N; i++) { // 맵 채우기
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        int element = Integer.parseInt(st.nextToken());
        arr[i][j] = element;

        if(element == 9) { // 아기 상어면, 위치 업데이트
          babyShark.position.x = i;
          babyShark.position.y = j;
        }
      }
    }

    while(true) { // 아기상어가 먹을게 있을 동안
      babyShark.setTarget(); // 타겟 설정
      if(babyShark.target.position == null) break;
      babyShark.move2Target(); // 이동
      babyShark.eat(); // 냠냠
    }

    System.out.println(time);
  }
}
