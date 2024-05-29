package Programmers;

import java.util.Arrays;

public class P_정수삼각형_김인엽 {
  public static int solution(int[][] triangle) {
    int[][] dp = new int[triangle.length][triangle.length];
    dp[0][0] = triangle[0][0];

    for(int i=1; i<dp.length; i++) {
      for(int j=0; j<i+1; j++) {
        if(j == 0) {
          dp[i][j] = triangle[i][j] + dp[i-1][j];
        } else if(j == i) {
          dp[i][j] = triangle[i][j] + dp[i-1][j-1];
        } else {
          dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
        }
      }
    }

    return Arrays.stream(dp[triangle.length-1]).max().orElse(Integer.MAX_VALUE);
  }

  public static void main(String[] args) {
    int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
    System.out.println(solution(triangle));
  }
}
