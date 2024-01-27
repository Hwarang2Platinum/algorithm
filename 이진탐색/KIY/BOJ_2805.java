package KIY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805 {
  static long[] arr;
  static long ans;

  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    arr = new long[n];

    // arr 채우기
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    long maxHeight = 0;
    for(int i=0; i<n; i++) {
      if(maxHeight < arr[i]) maxHeight = arr[i];
    }

    binarySearch(0, Math.max(maxHeight, m), m);

    System.out.println(ans);
    br.close();
  }

  public static void binarySearch(long start, long end, long target) {
    while(start <= end) {
      long mid = (start + end) / 2;

      long tmpAnswer = getTree(mid);

      // 나무 높이가 타겟과 같으면 끝!
      if(tmpAnswer == target) {
        ans = Math.max(mid, ans);
        return;
      } else if (tmpAnswer > target) {
        // 타겟보다 큰 값이다 -> 자르는 나무 높이를 더 키워도 되겠다
        ans = Math.max(mid, ans);
        start = mid + 1;
      } else {
        // 타겟보다 작은 값이다 -> 자르는 나무 높이를 더 낮추자
        end = mid - 1;
      }
    }
  }

  public static long getTree(long cutHeight) {
    return Arrays.stream(arr).filter(e -> e >= cutHeight).map(e -> e - cutHeight).sum();
  }
}