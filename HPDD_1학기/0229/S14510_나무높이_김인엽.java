import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S14510_나무높이_김인엽 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      // 배열 크기
      int N = Integer.parseInt(br.readLine());
      // 높이 입력받기
      int[] arr = new int[N];
      int maxValue = 0;
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        int tmp = Integer.parseInt(st.nextToken());
        arr[i] = tmp;
        maxValue = Math.max(maxValue, tmp);
      }

      // 답지 봤습니다.. 그리디인건 알았는데 규칙을 도저히..
      // 나무가 자라야할 높이에서 필요한 1,2의 개수 구하기
      int even = 0, odd = 0;
      for (int i = 0; i < N; i++) {
        int diff = maxValue - arr[i];
        if (diff == 0) {
          continue;
        }
        even += diff / 2;
        odd += diff % 2;
      }
      // 짝수가 더 많다면 짝수 한번에 1두번하는 방식으로 옮겨주기
      if (even > odd) {
        while (Math.abs(even - odd) > 1) {
          even--;
          odd += 2;
        }
      }
      int answer = 0;
      if (odd > even) {
        answer = odd * 2 - 1;
      } else if (even > odd) {
        answer = even * 2;
      } else {
        answer = even + odd; // 두 개수가 같으면, 그냥 번갈아가면서 하면돼서
      }

      System.out.println("#" + test_case + " " + answer);
    }
  }
}
