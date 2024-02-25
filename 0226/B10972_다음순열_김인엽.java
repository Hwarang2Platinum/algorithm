import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
// 	15360	124
public class B10972_다음순열_김인엽 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] input = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) input[i] = Integer.parseInt(st.nextToken());

    if(np(input)) {
      for (int i = 0; i < N; i++) {
        bw.write(input[i] + " ");
      }
    } else {
      bw.write("-1");
    }
    bw.close();
  }
  public static boolean np(int[] p) {
    final int N = p.length;
    int i = N - 1;
    while(i > 0 && p[i-1] >= p[i]) i--;
    if(i == 0) return false;

    int j = N - 1;
    while(p[i-1] >= p[j]) j--;

    swap(p, i-1, j);

    int k = N - 1;
    while(i < k) swap(p, i++, k--);
    return true;
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
