import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
// 	17220	512
public class B17281_야구공_김인엽 {

  final static int BLANK = -1;
  static int[][] arr; // 이닝별 결과
  static int N, answer; // 이닝 수, 최대 점수

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N][9];

    // 배열 채우기
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 9; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[] players = new int[9]; // 1번 타자를 뜻하는 0번 인덱스는 안움직이기
    for (int i = 0; i < players.length; i++) {
      players[i] = i;
    }
    // 순열 만들기
    do {
      play(players);
    } while (np(players));

    System.out.println(answer);
  }

  /**
   * 야구하는 함수
   *
   * @param players : 해당 순서로 진행(0번째는 3번째 타자와 교체 필요)
   */
  private static void play(int[] players) {
    int[] realPlayers = Arrays.copyOf(players, players.length);
    swap(realPlayers, 0, 3); // 1번 타자 4번 타자로 보내기

    int curPlayer = 0; // 현재 플레이어
    int outCnt = 0; // 아웃 카운트
    int inningCnt = 0; // 이닝 카운트
    int score = 0; // 점수
//    Queue<Integer> queue = queueInit(); // 베이스 초기화
    int[] bases = new int[3];
    Arrays.fill(bases, BLANK);

    while (inningCnt < N) { // 모든 이닝이 끝날때까지
      // 현재 플레이어 번호
      int curPlayerNum = realPlayers[curPlayer];
      // 현재 이닝에 현재 치는 플레이어의 결과
      switch (arr[inningCnt][curPlayerNum]) {
        case 0: // 아웃
          outCnt++;
          break;
        case 1: // 안타
          score = hitByArray(bases, curPlayerNum, 1, score);
//          score = hit(queue, curPlayerNum, 1, score);
          break;
        case 2: // 2루타
          score = hitByArray(bases, curPlayerNum, 2, score);
//          score = hit(queue, curPlayerNum, 2, score);
          break;
        case 3: // 3루타
          score = hitByArray(bases, curPlayerNum, 3, score);
//          score = hit(queue, curPlayerNum, 3, score);
          break;
        case 4: // 홈런
          score = hitByArray(bases, curPlayerNum, 4, score);
//          score = hit(queue, curPlayerNum, 4, score);
          break;
      }
      curPlayer = (curPlayer + 1) % 9; // 타자 다음 차례로 고고
      // 아웃 세번이면, 다음 이닝으로 이동 및 아웃카운트 초기화
      if (outCnt == 3) {
        inningCnt++; // 다음 이닝으로 이동
        outCnt = 0; // 아웃카운트 초기화
//        queue = queueInit(); // 베이스 초기화
        Arrays.fill(bases, BLANK);
      }
    }

    answer = Math.max(answer, score); // 큰 점수로 교체
  }

  public static int hitByArray(int[] arr, int playerNum, int hitCnt, int score) {
    // 뒤에서부터 확인(3루수부터)
    for(int i=arr.length-1; i>=0; i--) {
      if(arr[i] == BLANK) continue;
      // 점수를 낼 상황이라면
      if(i + hitCnt >= arr.length) {
        score++;
      } else {
        arr[i+hitCnt] = arr[i];
      }
      arr[i] = BLANK;
    }
    if(hitCnt < 4) // 홈런아니면
      // 안타 친 선수 넣기
      arr[hitCnt-1] = playerNum;
    else { // 홈런인경우
      score++;
    }
    return score;
  }

  /**
   * 모든 안타에 대한 처리 함수
   *
   * @param queue     베이스 상태
   * @param playerNum 플레이어 등번호
   * @param hitCnt    몇루타인지
   * @param score     현재 스코어
   * @return 안타 반영된 스코어 반환
   */
  public static int hit(Queue<Integer> queue, int playerNum, int hitCnt, int score) {
    queue.add(playerNum); // 안타친 플레이어 추가
    for (int i = 0; i < hitCnt - 1; i++) {
      queue.add(BLANK); // n루타를 쳤을때 n-1번 빈칸 채우기
    }
    for (int i = 0; i < hitCnt; i++) { // n루타를 쳤을 때 그만큼 poll해서, 점수획득 여부 확인
      int comeIn = queue.poll();
      if (comeIn > BLANK) {
        score++; // 만약 빈칸값보다 크면 플레이어 -> 점수 획득
      }
    }
    return score;
  }

  /**
   * 큐 초기화. 3칸을 빈칸으로 채우기
   *
   * @return Queue<Integer>
   */
  public static Queue<Integer> queueInit() {
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < 3; i++) {
      queue.add(BLANK); // -1로 채우기(빈칸으로 생각)
    }
    return queue;
  }

  /**
   * 순열 만드는 함수 by next permutation
   *
   * @param p : 순열로 만들 배열
   * @return 순열을 더이상 못만들면(내림차순) false, 그 외엔 true
   */
  public static boolean np(int[] p) {
    final int N = p.length;

    int i = N - 1;
    while (i > 1 && p[i - 1] >= p[i]) {
      i--; // 꼭대기 아래 찾기
    }

    if (i == 1) {
      return false; // 이때가 내림차순(0번인덱스제외)
    }

    int j = N - 1;
    while (p[i - 1] >= p[j]) {
      j--; // 큰 애들중 가장 작은 애 찾기
    }

    swap(p, i - 1, j); // swap하기

    int k = N - 1;
    while (i < k) {
      swap(p, i++, k--); // 큰 애들 오른참순 정렬하기
    }

    return true;
  }

  /**
   * 배열 arr의 i번째 인덱스 값과 j번째 인덱스 값을 교체
   *
   * @param arr : 배열
   * @param i   : 인덱스1
   * @param j   : 인덱스2
   */
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
