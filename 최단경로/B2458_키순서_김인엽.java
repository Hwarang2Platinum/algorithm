import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 	41428	728
public class B2458_키순서_김인엽 {
  static class Student {
    List<Integer> links = new ArrayList<>();
    @Override
    public String toString() {
      return "Student{" +
          "links=" + links +
          '}';
    }
  }
  static int N;
  static Student[] studentsUp; // 주어진 입력에 대한 그래프. 큰 방향으로 주어짐
  static Student[] studentsDown; // 주어진 입력에 반대방향 그래프. 작은 방향으로 주어짐
  static boolean[] visitedUp;
  static boolean[] visitedDown;
  static int upCnt, downCnt;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    studentsUp = new Student[N+1];
    studentsDown = new Student[N+1];

    for(int i=1; i<N+1; i++) {
      studentsUp[i] = new Student();
      studentsDown[i] = new Student();
    }

    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      studentsUp[a].links.add(b);
      studentsDown[b].links.add(a);
    }

    int answer = 0;

    for(int i=1; i<N+1; i++) {
      visitedUp = new boolean[N+1];
      visitedDown = new boolean[N+1];
      downCnt = -1;
      upCnt = -1;
      dfsUp(i); dfsDown(i);
      if((downCnt + upCnt) == (N-1)) { // 낮은애+높은애 = N-1이면 다 찾은거니 내 키 번호 알 수 있음
        answer++;
      }
    }
    System.out.println(answer);
  }

  private static void dfsDown(int v) { // 낮은 애들 찾으러가기
    downCnt++;
    visitedDown[v] = true;
    for(int link: studentsDown[v].links) {
      if(!visitedDown[link])
        dfsDown(link);
    }
  }

  private static void dfsUp(int v) { // 높은 애들 찾으러가기
    upCnt++;
    visitedUp[v] = true;
    for(int link: studentsUp[v].links) {
      if(!visitedUp[link])
        dfsUp(link);
    }
  }
}
