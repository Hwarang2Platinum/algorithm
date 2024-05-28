import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14891 {
  private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int[][] wheels = new int[4][8];
  static int[] twelveIdxs = {0,0,0,0};
  static List<Integer> toRotateRight = new ArrayList<>();
  static List<Integer> toRotateLeft = new ArrayList<>();
  static boolean[] visited = new boolean[4];
  public static void main(String[] args) throws IOException {
    // 톱니바퀴 초기화 | n: 0, s: 1
    for(int i=0; i<4; i++) {
      String[] tmp = br.readLine().split("");
      for(int j=0; j<tmp.length; j++) {
        wheels[i][j] = Integer.parseInt(tmp[j]);
      }
    }
    // 회전 횟수
    int k = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for(int i=0; i<k; i++) {
      st = new StringTokenizer(br.readLine());
      // 회전 바퀴 번호
      int wheelNum = Integer.parseInt(st.nextToken());
      // 방향 | 1:시계, -1: 반시계
      int direct = Integer.parseInt(st.nextToken());
      // 인덱스 맞추기 위해 -1
      solution(wheelNum-1, direct);
      doRotate();
    }

    int answer = 0;
    // 1 -> 2 -> 4 -> 8
    for(int i=0; i< twelveIdxs.length; i++) {
      if(wheels[i][twelveIdxs[i]] == 1) {
        answer += 1 << i;
      }
    }
    System.out.println(answer);
  }

  private static void solution(int wheelNum, int direct) {
    // 벗어나는 인덱스 처리
    if(wheelNum < 0 || wheelNum >= 4) return;
    // 이미 방문한 인덱스 처리
    if(visited[wheelNum]) return;

    // 시계 방향 -> 시계방향 리스트에 추가
    if(direct == 1) toRotateRight.add(wheelNum);
    else toRotateLeft.add(wheelNum);
    // visit 추가
    visited[wheelNum] = true;

    if(checkLeft(wheelNum)) {
      solution(wheelNum-1, direct*(-1));
    }
    if(checkRight(wheelNum)) {
      solution(wheelNum+1, direct*(-1));
    }

  }

  private static void doRotate() {
    for(int idx: toRotateLeft) {
      twelveIdxs[idx] = (twelveIdxs[idx] + 1) % 8;
    }
    for(int idx: toRotateRight) {
      twelveIdxs[idx] = twelveIdxs[idx] == 0 ? 7 :(twelveIdxs[idx] - 1);
    }
    // 모든 거 초기화
    toRotateRight.clear();
    toRotateLeft.clear();
    visited = new boolean[4];
  }

  private static boolean checkRight(int wheelNum) {
    if(wheelNum == 3) {
      return false;
    }
    // 현재 : 2번 인덱스 == 비교: 6번 인덱스 -> return false
    // else -> return true
    return wheels[wheelNum][(twelveIdxs[wheelNum] + 2) % 8] != wheels[wheelNum + 1][
        (twelveIdxs[wheelNum + 1] + 6) % 8];
  }

  private static boolean checkLeft(int wheelNum) {
    if(wheelNum == 0) {
      return false;
    }
    // 현재 : 6번 인덱스 == 비교: 2번 인덱스 -> return false
    // else -> return true
    return wheels[wheelNum][(twelveIdxs[wheelNum] + 6) % 8] != wheels[wheelNum - 1][
        (twelveIdxs[wheelNum - 1] + 2) % 8];
  }
}
