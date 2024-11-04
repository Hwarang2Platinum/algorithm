import java.io.*;
import java.util.*;

/**
 * IMP : https://www.acmicpc.net/problem/15486
 * 
 * 1 : 3, 10
 * 2 : 5, 20
 * 3 : 1, 10
 * 4 : 1, 20
 * 5 : 2, 15
 * 6 : 4, 40
 * 7 : 2, 200
 * 
 * IMP : 생각보다 점화식은 간단하다. => 하지만 연습을 하지 않는다면.. 풀어내지 못할 것 같음.
 * IMP : Math.max와 그냥 일반적인 if절을 통한 Max 도출의 차이점..? 한 8ms 차이..
 */
public class B15486_퇴사2_오화랑 {
    static class Solution {
        int N;
        int[] Memo; // MAX SALARY : 1_500_000_000 < MAX INTEGER

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            N = Integer.parseInt(input.readLine());
            Memo = new int[N + 1];

            int eachT, eachS, canMake;
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(input.readLine());
                eachT = Integer.parseInt(st.nextToken());
                eachS = Integer.parseInt(st.nextToken());
                // Memo[i] = Math.max(Memo[i], Memo[i - 1]);
                Memo[i] = Memo[i] > Memo[i - 1] ? Memo[i] : Memo[i - 1];
                if (i + eachT - 1 > N)
                    continue;
                canMake = Memo[i - 1] + eachS;
                // Memo[i + eachT - 1] = Math.max(Memo[i + eachT - 1], canMake);
                Memo[i + eachT - 1] = Memo[i + eachT - 1] > canMake ? Memo[i + eachT - 1] : canMake;
            }
            System.out.println(Memo[N]);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
