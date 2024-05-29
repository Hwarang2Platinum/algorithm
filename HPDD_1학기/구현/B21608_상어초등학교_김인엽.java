import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//22712	200
public class B21608_상어초등학교_김인엽 {
  static int[][] arr, favorites;
  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N + 1][N + 1];

    StringTokenizer st;
    favorites = new int[N * N + 1][4];
    for (int i = 0; i < N * N; i++) {
      st = new StringTokenizer(br.readLine());
      int student = Integer.parseInt(st.nextToken());
      for (int j = 0; j < 4; j++) {
        favorites[student][j] = Integer.parseInt(st.nextToken());
      }
      solution(student);
    }

    int answer = 0;
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        answer += calcScore(i, j);
      }
    }
    System.out.println(answer);
  }

  private static int calcScore(int r, int c) { // 점수 계산
    switch(checkAroundFavorites(r, c)) {
      case 0:
        return 0;
      case 1:
        return 1;
      case 2:
        return 10;
      case 3:
        return 100;
      case 4:
        return 1000;
    }
    return 0;
  }

  private static int checkAroundFavorites(int x, int y) { // 주변 좋아하는 학생 세기
    int[] myFavorites = favorites[arr[x][y]];
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};

    int cnt = 0;
    for(int i=0; i<dx.length; i++) {
      int cx = x + dx[i];
      int cy = y + dy[i];
      if(cx < 1 || cx >= N+1 || cy < 1 || cy >= N+1) continue;
      // 만약 favorties에 소속되어있다면 + 1
      for(int myFavorite: myFavorites) {
        if(myFavorite == arr[cx][cy]){
          cnt++;
          break;
        }
      }
    }
    return cnt;
  }
  private static int[] checkAround(int x, int y, int student) { // 주변 세기(좋아하는 학생 , 빈칸)
    int[] result = new int[2]; // 0: favorite, 1 : blank
    int[] myFavorites = favorites[student];
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};

    for(int i=0; i<dx.length; i++) {
      int cx = x + dx[i];
      int cy = y + dy[i];
      if(cx < 1 || cx >= N+1 || cy < 1 || cy >= N+1) continue;
      // 비어있따면 +1
      if(arr[cx][cy] == 0) {
        result[1]++;
        continue;
      }
      // 만약 favorties에 소속되어있다면 + 1
      for(int myFavorite: myFavorites) {
        if(myFavorite == arr[cx][cy]){
          result[0]++;
          break;
        }
      }
    }

    return result;
  }
  private static void solution(int student) { // 해당 학생을 arr안에 넣기
    int x = 0, y = 0;
    int maxFav = -1;
    int maxBlank = -1;
    // 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리 정하기
    for(int i=1; i<N+1; i++) {
      for(int j=1; j<N+1; j++) {
        // 비어있지 않으면 패스
        if(arr[i][j] != 0) continue;
//        int tmpFavCnt = checkAroundFavorites(i, j, student);
        int[] tmp = checkAround(i, j, student);
        int tmpFavCnt = tmp[0];
        int tmpBlankCnt = tmp[1];
        // 만약 좋아하는 학생이 더 많은 위치라면 바꾸기
        if(tmpFavCnt > maxFav) {
          maxFav = tmpFavCnt;
          maxBlank = tmpBlankCnt;
          x = i;
          y = j;
        } else if(tmpFavCnt == maxFav) { // 비어있는 칸이 더 많은 자리 선택
          if(tmpBlankCnt > maxBlank) {
            maxBlank = tmpBlankCnt;
            x = i;
            y = j;
          }
        }
      }
    }
    arr[x][y] = student;
  }
}
