import java.io.*;
import java.util.*;

public class B1749_점수따먹기_오화랑 {
    static class Solution {
        int N, M, maxSum;
        int[][] matrix;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.N = Integer.parseInt(st.nextToken());
            this.M = Integer.parseInt(st.nextToken());
            this.matrix = new int[this.N][this.M];
            for (int i = 0; i < this.N; i++) {
                st = new StringTokenizer(input.readLine());
                for (int j = 0; j < this.M; j++) {
                    this.matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            this.maxSum = -10000;
            getMaxSum();
            System.out.println(this.maxSum);
        }

        void getMaxSum() {
            for (int i = 0; i < this.N; i++) {
                for (int j = i; j < this.N; j++) {
                    getEachSum(i, j);
                }
            }
        }

        void getEachSum(int sR, int eR) {
            int eachSum = 0;
            for (int c = 0; c < this.M; c++) {
                for (int r = sR; r <= eR; r++)
                    eachSum += this.matrix[r][c];
                this.maxSum = Math.max(eachSum, this.maxSum);
                if (eachSum <= 0) {
                    eachSum = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}