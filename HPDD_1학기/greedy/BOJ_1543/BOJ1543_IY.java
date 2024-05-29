import java.util.Scanner;

public class BOJ1543_IY {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String str = sc.nextLine();
    String toFind = sc.nextLine();
    // 현재 인덱스
    int curIdx = 0;
    int ans = 0;

    while(true) {
      // 다음 인덱스 = 찾으려는 문자열의 위치 + 길이
      curIdx = str.indexOf(toFind);
      // 못 찾으면 종료
      if(curIdx < 0) break;
      curIdx += toFind.length();
      str = str.substring(curIdx);
      ans++;
    }
    System.out.println(ans);
  }
}
