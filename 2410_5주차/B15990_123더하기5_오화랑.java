
/**
 * IMP : https://www.acmicpc.net/problem/15990
 * IMP : 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 3가지 => 합을 나타낼 때 1개 이상 사용 & 같은 수 연속 사용 X
 * IMP : 각 TC마다, 1,000,000,009로 나눈 나머지를 출력한다.
 * * 1 -> 1
 * * 2 -> 2
 * * 3 -> 1 + 2, 2 + 1, 3
 * 
 * * 4 -> 1 + (3) / / 1 + 2 + (1), 3 + (1)
 * 
 * * 5 -> 2 + (3) / 2 + 1 + (2), 3 + (2) / 1 + 3 + (1)
 * 
 * * 6 -> 1 + 2 + (3), 2 + 1 + (3) / 1 + 3 + (2), 1 + 2 + 1 + (2), 3 + 1 + (2) / 2 + 3 + (1), 2 + 1 + 2 + (1), 3 + 2 + (1)
 * 
 * * 7 -> 2 + 2 + 5 => 9
 * 
 * IMP : long이 반드시 필요한 이유를 잘 모르겠다.. => 없어도 가능할 것 같은데..?
 * IMP : 내가 보기엔, 내부 연산이 그렇게 long 범위가 안될 것 같은데 그렇게 되나 보다.
 */

import java.io.*;
import java.util.*;

public class B15990_123더하기5_오화랑 {
    static class Solution {
        int DIV = 1_000_000_009;

        class Info {
            long end1, end2, end3;

            public Info(long end1, long end2, long end3) {
                this.end1 = end1;
                this.end2 = end2;
                this.end3 = end3;
            }

            public long getTotalValue() {
                return (this.end1 + this.end2 + this.end3) % DIV;
            }
        }

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            // Init
            int T = Integer.parseInt(input.readLine());
            Info[] sumInfo = new Info[100_001];

            for (int i = 1; i <= 100_000; i++) {
                if (i == 1)
                    sumInfo[i] = new Info(1, 0, 0);
                else if (i == 2)
                    sumInfo[i] = new Info(0, 1, 0);
                else if (i == 3)
                    sumInfo[i] = new Info(1, 1, 1);
                else {
                    long e1 = (sumInfo[i - 1].end2 + sumInfo[i - 1].end3) % DIV;
                    long e2 = (sumInfo[i - 2].end1 + sumInfo[i - 2].end3) % DIV;
                    long e3 = (sumInfo[i - 3].end1 + sumInfo[i - 3].end2) % DIV;
                    sumInfo[i] = new Info(e1, e2, e3);
                }
            }

            for (int t = 1; t <= T; t++) {
                int target = Integer.parseInt(input.readLine());
                sb.append(sumInfo[target].getTotalValue()).append("\n");
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}