import java.util.*;
class Solution {
    static int answer;
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        answer = triangle[0][0];
        go(0,triangle, dp);
        return answer;
    }
    public static void go(int depth,int[][] triangle, int[][] dp){
        if(depth==triangle.length-1){
            return;
        }

        for(int p=0;p<depth+1;p++){
            if(dp[depth+1][p] < dp[depth][p]+triangle[depth+1][p]) {
                dp[depth+1][p] = dp[depth][p]+triangle[depth+1][p];
                answer = Math.max(dp[depth+1][p],answer);
            }
            if(dp[depth+1][p+1] < dp[depth][p]+triangle[depth+1][p+1]) {
                dp[depth+1][p+1] = dp[depth][p]+triangle[depth+1][p+1];
                answer = Math.max(dp[depth+1][p+1],answer);
            }
        }
        go(depth+1, triangle, dp);

    }
}