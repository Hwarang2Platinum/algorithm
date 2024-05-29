import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// 14452	216
public class B1182_부분수열의합_김인엽 {
  static Integer[] arr;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    arr = new Integer[N];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr, Collections.reverseOrder());

    int answer = 0;

    for(int i=1; i<=N; i++) {
      int[] isSelected = new int[N];
      for(int j=N-1; j>N-1-i; j--) {
        isSelected[j] = 1;
      }
      do {
        if(calcSum(isSelected) == S) answer++;
      } while(np(isSelected));
    }

    System.out.println(answer);
  }

  public static boolean np(int[] p) {
    final int N = p.length;
    // 꼭대기 밑 찾기
    int i = N-1;
    while(i > 0 && p[i-1] >= p[i]) i--;

    if(i == 0) return false; // 마지막 경우(내림차순)

    // 큰 애들 중 가장 작은 친구 찾기
    int j = N-1;
    while(p[i-1]>=p[j]) j--;

    swap(p, i-1, j);

    // 꼭대기 부터 끝까지 오름차순 정렬
    int k = N-1;
    while(i<k) swap(p, i++, k--);

    return true;
  }

  private static void swap(int[] p, int i, int j) {
    int tmp = p[i];
    p[i] = p[j];
    p[j] = tmp;
  }

  public static int calcSum(int[] isSelected) {
    int ret = 0;
    for(int i=0; i < arr.length; i++) {
      if(isSelected[i] == 1) ret += arr[i];
    }
    return ret;
  }
}
