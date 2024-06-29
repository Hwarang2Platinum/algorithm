import java.io.*;
import java.util.*;

public class B4920_테트리스게임_오화랑 {
    static class Solution {
        int N, T, maxSum;
        int[][] Map;

        int[][] frame1 = { { 1, 0 }, { 2, 0 }, { 3, 0 } };
        int[][] frame2 = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
        int[][] frame3 = { { 0, 1 }, { 1, 1 }, { 1, 2 } };
        int[][] frame4 = { { 1, 0 }, { 1, -1 }, { 2, -1 } };
        int[][] frame5 = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
        int[][] frame6 = { { 1, 0 }, { 2, 0 }, { 2, -1 } };
        int[][] frame7 = { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        int[][] frame8 = { { 0, -1 }, { 1, -1 }, { 2, -1 } };
        int[][] frame9 = { { 0, -1 }, { 0, 1 }, { 1, 0 } };
        int[][] frame10 = { { -1, 0 }, { 1, 0 }, { 0, -1 } };
        int[][] frame11 = { { 0, -1 }, { 0, 1 }, { -1, 0 } };
        int[][] frame12 = { { -1, 0 }, { 1, 0 }, { 0, 1 } };
        int[][] frame13 = { { 1, 0 }, { 1, 1 }, { 0, 1 } };

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = null;
            this.T = 1;
            while (true) {
                this.N = Integer.parseInt(input.readLine().trim());
                if (this.N == 0)
                    break;
                this.Map = new int[this.N][this.N];
                this.maxSum = Integer.MIN_VALUE;
                for (int i = 0; i < this.N; i++) {
                    st = new StringTokenizer(input.readLine());
                    for (int j = 0; j < this.N; j++) {
                        this.Map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                for (int i = 0; i < this.N; i++) {
                    for (int j = 0; j < this.N; j++) {
                        getMaxSum(i, j, this.Map[i][j]);
                    }
                }
                sb.append(T++).append(". ").append(this.maxSum).append("\n");
            }
            System.out.print(sb);
        }

        void getMaxSum(int x, int y, int startSum) {
            int s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13;
            s1 = s2 = s3 = s4 = s5 = s6 = s7 = s8 = s9 = s10 = s11 = s12 = s13 = startSum;

            boolean f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13;
            f1 = f2 = f3 = f4 = f5 = f6 = f7 = f8 = f9 = f10 = f11 = f12 = f13 = true;

            int x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13;
            int y1, y2, y3, y4, y5, y6, y7, y8, y9, y10, y11, y12, y13;

            for (int i = 0; i < 3; i++) {
                x1 = x + frame1[i][0];
                y1 = y + frame1[i][1];
                x2 = x + frame2[i][0];
                y2 = y + frame2[i][1];
                x3 = x + frame3[i][0];
                y3 = y + frame3[i][1];
                x4 = x + frame4[i][0];
                y4 = y + frame4[i][1];
                x5 = x + frame5[i][0];
                y5 = y + frame5[i][1];
                x6 = x + frame6[i][0];
                y6 = y + frame6[i][1];
                x7 = x + frame7[i][0];
                y7 = y + frame7[i][1];
                x8 = x + frame8[i][0];
                y8 = y + frame8[i][1];
                x9 = x + frame9[i][0];
                y9 = y + frame9[i][1];
                x10 = x + frame10[i][0];
                y10 = y + frame10[i][1];
                x11 = x + frame11[i][0];
                y11 = y + frame11[i][1];
                x12 = x + frame12[i][0];
                y12 = y + frame12[i][1];
                x13 = x + frame13[i][0];
                y13 = y + frame13[i][1];
                if (x1 < 0 || y1 < 0 || x1 >= this.N || y1 >= this.N)
                    f1 = false;
                else
                    s1 += this.Map[x1][y1];
                if (x2 < 0 || y2 < 0 || x2 >= this.N || y2 >= this.N)
                    f2 = false;
                else
                    s2 += this.Map[x2][y2];
                if (x3 < 0 || y3 < 0 || x3 >= this.N || y3 >= this.N)
                    f3 = false;
                else
                    s3 += this.Map[x3][y3];
                if (x4 < 0 || y4 < 0 || x4 >= this.N || y4 >= this.N)
                    f4 = false;
                else
                    s4 += this.Map[x4][y4];
                if (x5 < 0 || y5 < 0 || x5 >= this.N || y5 >= this.N)
                    f5 = false;
                else
                    s5 += this.Map[x5][y5];
                if (x6 < 0 || y6 < 0 || x6 >= this.N || y6 >= this.N)
                    f6 = false;
                else
                    s6 += this.Map[x6][y6];
                if (x7 < 0 || y7 < 0 || x7 >= this.N || y7 >= this.N)
                    f7 = false;
                else
                    s7 += this.Map[x7][y7];
                if (x8 < 0 || y8 < 0 || x8 >= this.N || y8 >= this.N)
                    f8 = false;
                else
                    s8 += this.Map[x8][y8];
                if (x9 < 0 || y9 < 0 || x9 >= this.N || y9 >= this.N)
                    f9 = false;
                else
                    s9 += this.Map[x9][y9];
                if (x10 < 0 || y10 < 0 || x10 >= this.N || y10 >= this.N)
                    f10 = false;
                else
                    s10 += this.Map[x10][y10];
                if (x11 < 0 || y11 < 0 || x11 >= this.N || y11 >= this.N)
                    f11 = false;
                else
                    s11 += this.Map[x11][y11];
                if (x12 < 0 || y12 < 0 || x12 >= this.N || y12 >= this.N)
                    f12 = false;
                else
                    s12 += this.Map[x12][y12];
                if (x13 < 0 || y13 < 0 || x13 >= this.N || y13 >= this.N)
                    f13 = false;
                else
                    s13 += this.Map[x13][y13];
            }
            if (f1)
                this.maxSum = Math.max(s1, this.maxSum);
            if (f2)
                this.maxSum = Math.max(s2, this.maxSum);
            if (f3)
                this.maxSum = Math.max(s3, this.maxSum);
            if (f4)
                this.maxSum = Math.max(s4, this.maxSum);
            if (f5)
                this.maxSum = Math.max(s5, this.maxSum);
            if (f6)
                this.maxSum = Math.max(s6, this.maxSum);
            if (f7)
                this.maxSum = Math.max(s7, this.maxSum);
            if (f8)
                this.maxSum = Math.max(s8, this.maxSum);
            if (f9)
                this.maxSum = Math.max(s9, this.maxSum);
            if (f10)
                this.maxSum = Math.max(s10, this.maxSum);
            if (f11)
                this.maxSum = Math.max(s11, this.maxSum);
            if (f12)
                this.maxSum = Math.max(s12, this.maxSum);
            if (f13)
                this.maxSum = Math.max(s13, this.maxSum);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}