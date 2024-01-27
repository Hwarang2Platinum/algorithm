package KIY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1654 {
  static long[] arr;

  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 1<= <= 1만
    int k = Integer.parseInt(st.nextToken());
    // 1<= <= 백만
    int n = Integer.parseInt(st.nextToken());
    long maxValue = 0;

    // 2^31 - 1 -> int
    arr = new long[k];

    for(int i=0; i<k; i++) {
      st = new StringTokenizer(br.readLine());
      long tmpValue = Integer.parseInt(st.nextToken());
      arr[i] = tmpValue;
      if(tmpValue > maxValue) maxValue = tmpValue;
    }

    System.out.println(binarySearch(0, maxValue, n));
  }

  private static long binarySearch(long start, long end, int target) {
    while(start<=end) {
      long mid = (start + end) / 2;
      if(mid == 0) break;

      long tmpAns = getLanLine(mid);
      if(tmpAns >= target) {
        // 많이 만들었다 -> 랜선길이를 더 높여도 되겠다.
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return end;
  }

  private static long getLanLine(long mid) {
    return Arrays.stream(arr).map(e -> e / mid).sum();
  }

}