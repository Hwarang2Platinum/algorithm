import java.io.*;
import java.util.*;

// 스티커 2n개를 구매함
// 2행 N열로 배치되어 있음
// 한 장을 떼면, 해당 스티커와 변을 공유하는 스티커는 모두 찢어져 사용할 수 없음
// 상하좌우의 스티커는 사용할 수 없다.
// 모든 스티커를 붙일 수 없게 되면, 각 스티커에 점수에 따라 최대 점수를 받는 스티커를 떼어내려고 한다.
// TestCase가 주어짐
// 각 TestCase의 첫번째 줄에는 n : 1 ~ 100,000개가 주어진다. 점수는 0 ~ 100
// 1. 각 열에서 1개의 스티커만 뽑을 수 있음
// 2. 각 열에서 최대값을 뽑아 나가는 방향으로 계산을 해나가면 된다.

public class B9465_스티커_오화랑 {
    static class Solution {
        int testCase;
        int col;
        int[][] stickers;

        public void init() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = null;
            this.testCase = Integer.parseInt(input.readLine());
            for (int t = 1; t <= testCase; t++) {
                this.col = Integer.parseInt(input.readLine());
                this.stickers = new int[2][this.col];
                for (int i = 0; i < 2; i++) {
                    st = new StringTokenizer(input.readLine());
                    for (int j = 0; j < this.col; j++)
                        this.stickers[i][j] = Integer.parseInt(st.nextToken());
                }
                sb.append(solution()).append("\n");
            }
            System.out.print(sb);
        }

        public int solution() {
            int[][] memo = new int[2][this.col];
            memo[0][0] = stickers[0][0];
            memo[1][0] = stickers[1][0];
            for (int i = 1; i < this.col; i++) {
                memo[0][i] = Math.max(memo[1][i - 1] + stickers[0][i],
                        memo[0][i - 1] + stickers[0][i] - stickers[0][i - 1]);
                memo[1][i] = Math.max(memo[0][i - 1] + stickers[1][i],
                        memo[1][i - 1] + stickers[1][i] - stickers[1][i - 1]);
            }
            return Math.max(memo[0][this.col - 1], memo[1][this.col - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.init();
    }
}