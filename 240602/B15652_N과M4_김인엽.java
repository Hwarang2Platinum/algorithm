import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B15652_N과M4_김인엽 {
  static int N, M;
  static int[] numbers;
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    numbers = new int[M];

    permutation(0, 1);

    bw.close();
  }

  static void permutation(int cnt, int start) throws IOException {
    if(cnt == M) {
      for(int number : numbers) {
        bw.write(number + " ");
      }
      bw.write("\n");
      return;
    }
    for(int i=start; i<=N; i++) {
      numbers[cnt] = i;
      permutation(cnt+1, i);
    }
  }

}