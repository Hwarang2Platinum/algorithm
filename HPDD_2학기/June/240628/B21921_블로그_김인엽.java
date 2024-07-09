import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] blogs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < blogs.length; i++) {
            blogs[i] = Integer.parseInt(st.nextToken());
        }

        int[] sums = new int[N+1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i-1] + blogs[i-1];
        }

        if(sums[N] == 0) {
            System.out.println("SAD");
            return;
        }

        int maxVisit = 0;
        int maxCnt = 0;

        for (int i = 0; i < N+1-X; i++) {
            int tmpSum = sums[i+X] - sums[i];
            if (tmpSum > maxVisit) {
                maxVisit = tmpSum;
                maxCnt = 1;
            } else if(tmpSum == maxVisit) {
                maxCnt++;
            }
        }
        System.out.println(maxVisit);
        System.out.println(maxCnt);
    }
}
