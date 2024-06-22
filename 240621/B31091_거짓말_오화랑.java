import java.io.*;
import java.util.*;

// 메모이제이션을 활용해서 문제를 풀어냈다.
// 마지막 공백은 무시해서 받아주는 것 같다.

public class B31091_거짓말_오화랑 {
    static class Solution {
        int N, overCnt, underCnt, lieCnt;
        int[] overList, underList;
        int[] lieMemo;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            this.N = Integer.parseInt(input.readLine());
            this.overList = new int[this.N + 1];
            this.underList = new int[this.N + 1];
            this.lieMemo = new int[this.N + 1];

            StringTokenizer st = new StringTokenizer(input.readLine());
            int k;
            for (int i = 0; i < this.N; i++) {
                k = Integer.parseInt(st.nextToken());
                if (k > 0) {
                    this.overList[k]++;
                    this.overCnt++;
                } else {
                    this.underList[-k]++;
                    this.underCnt++;
                }
            }

            int currentOverCnt, currentUnderCnt;
            currentOverCnt = this.overCnt;
            currentUnderCnt = 0;
            for (int i = 0; i <= this.N; i++) {
                currentOverCnt -= this.overList[i];
                lieMemo[i] = getLie(i, currentOverCnt, currentUnderCnt);
                currentUnderCnt += this.underList[i];
            }

            for (int i = 0; i <= this.N; i++) {
                if (i == lieMemo[i]) {
                    sb.append(i).append(" ");
                    lieCnt++;
                }
            }
            System.out.println(lieCnt);
            System.out.print(sb);
        }

        int getLie(int i, int oCnt, int uCnt) {
            return oCnt + uCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
