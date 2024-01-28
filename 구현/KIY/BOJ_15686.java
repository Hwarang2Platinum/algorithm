package KIY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 풀이 봤음
public class BOJ_15686 {

  static class RC {
    int r;
    int c;

    public RC(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int answer;
  static List<RC> houses = new ArrayList<>(); // 집 좌표
  static List<RC> chickens = new ArrayList<>(); // 치킨집 좌표
  static int N;
  static int M;
  static int chickensLen;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        String tmp = st.nextToken();
        if(tmp.equals("1")) houses.add(new RC(i, j));
        else if(tmp.equals("2")) chickens.add(new RC(i, j));
      }
    }
    answer = 2*N*2*N;
    chickensLen = chickens.size();

    dfs(0, new ArrayList<>());

    System.out.println(answer);
  }

  public static void dfs(int n, List<RC> tmpList) {
    // n: 현재 치킨집 인덱스, tmpList: 유지한 치킨집
    // 종료
    if(n == chickensLen) {
      // 만약 M개 골랐다면 최솟값을 결과에 넣기
      if(tmpList.size() == M) {
        answer = Math.min(answer, calcChickenDist(tmpList));
      }
      return;
    }

    // 해당 치킨집을 유지하지 않을 때
    dfs(n+1, tmpList);

    // 해당 치킨집을 유지할 때
    tmpList.add(chickens.get(n));
    dfs(n+1, tmpList);
    tmpList.remove(tmpList.size() - 1);

  }

  private static int calcChickenDist(List<RC> tmpList) {
    int tmpAns = 0;
    for(RC house: houses) {
      tmpAns += tmpList.stream().mapToInt(c -> calcRCDist(c, house)).min().orElse(1000);
    }
    return tmpAns;
  }

  private static int calcRCDist(RC one, RC two) {
    return Math.abs(one.r - two.r) + Math.abs(one.c - two.c);
  }
}