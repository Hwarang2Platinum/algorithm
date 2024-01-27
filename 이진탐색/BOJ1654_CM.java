import java.util.*;
import java.io.*;

public class BOJ1654_CM {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        long hi = 0;

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            hi = Math.max(hi,arr[i]);
        }

        hi++;
        long low = 0;
        long mid = 0;

        while (low < hi) {

            mid = (hi + low) / 2;
            long cnt = 0;

            for (int i = 0; i < arr.length; i++) {
                cnt += (arr[i] / mid);
            }
            if(cnt < N) {
                hi = mid;
            }
            else {
                low = mid + 1;
            }
        }
        System.out.println(low - 1);
    }
}