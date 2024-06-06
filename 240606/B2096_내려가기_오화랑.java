
/**
 * B2096_내려가기_오화랑
 */
import java.io.*;
import java.util.*;

// Problem
// N개의 줄에 걸쳐, 0이상 9 이하의 숫자가 3개씩 적혀 있다.
// 내려가기 게임 -> 첫 줄에서 시작해서 마지막 물에서 끝나게 되는 놀이
// 처음에 적혀있는 3개의 숫자 중, 하나를 골라서 시작하게 됨. -> 다음 줄로 내려가는데, 바로 아래의 수 or 아래의 수와 붙어있는 수로만 이동 가능
// 줄은 1 ~ 100,000개 -> 전부 Bactracking? 하면 난리날 것이 뻔하다.
// 최대 점수와 최소 점수를 듸어서 출력

public class B2096_내려가기_오화랑 {
    static int[][] maxMemo, minMemo;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(input.readLine());
        maxMemo = new int[N][3];
        minMemo = new int[N][3];
        int n1, n2, n3;
        int leftMax, rightMax, midMax, leftMin, rightMin, midMin;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            n3 = Integer.parseInt(st.nextToken());
            if (i == 0) {
                maxMemo[i][0] = minMemo[i][0] = n1;
                maxMemo[i][1] = minMemo[i][1] = n2;
                maxMemo[i][2] = minMemo[i][2] = n3;
                continue;
            }
            leftMax = maxMemo[i - 1][0] > maxMemo[i - 1][1] ? maxMemo[i - 1][0] : maxMemo[i - 1][1];
            rightMax = maxMemo[i - 1][1] > maxMemo[i - 1][2] ? maxMemo[i - 1][1] : maxMemo[i - 1][2];
            midMax = leftMax > rightMax ? leftMax : rightMax;

            leftMin = minMemo[i - 1][0] < minMemo[i - 1][1] ? minMemo[i - 1][0] : minMemo[i - 1][1];
            rightMin = minMemo[i - 1][1] < minMemo[i - 1][2] ? minMemo[i - 1][1] : minMemo[i - 1][2];
            midMin = leftMin < rightMin ? leftMin : rightMin;

            maxMemo[i][0] = n1 + leftMax;
            maxMemo[i][1] = n2 + midMax;
            maxMemo[i][2] = n3 + rightMax;
            minMemo[i][0] = n1 + leftMin;
            minMemo[i][1] = n2 + midMin;
            minMemo[i][2] = n3 + rightMin;
        }
        int totalMax = Math.max(Math.max(maxMemo[N - 1][0], maxMemo[N - 1][1]), maxMemo[N - 1][2]);
        int totalMin = Math.min(Math.min(minMemo[N - 1][0], minMemo[N - 1][1]), minMemo[N - 1][2]);
        System.out.println(totalMax + " " + totalMin);
    }
}