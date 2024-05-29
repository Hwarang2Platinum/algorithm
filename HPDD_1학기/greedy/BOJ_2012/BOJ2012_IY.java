import java.util.Arrays;
import java.util.Scanner;

public class BOJ2012_IY {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] students = new int[n];
    // 등수 입력받기
    for(int i=0; i<n; i++) {
      students[i] = sc.nextInt();
    }
    long ans = 0;
    // 1. students를 정렬한 상태에서 불만도 계산
    Arrays.sort(students);
    for(int i=0; i<n; i++) {
      ans += Math.abs(students[i] - (i+1));
    }

    System.out.println(ans);
  }
}
