package March;

public class P_정수삼각형_송인범 {
	
	public static int solution(int[][] triangle) {
	        int answer = 0;
	        int N = triangle.length; //5
	        int [][] dp = new int[N+1][N+1];
	        dp[0][0] = triangle[0][0];
	        
	        for(int i=1; i<N; i++) {
	        	for(int j=0; j<i+1; j++) {
	        		if(j==0) {
	        			dp[i][j] = dp[i-1][j]+triangle[i][j];
	        		}
	        		else if(j==i) {
	        			dp[i][j] = dp[i-1][j-1]+triangle[i][j]; //다음칸 1증가
	        		}
	        		else
	        			dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1])+triangle[i][j];
	        	}
	        }
	        
	        
	        for(int i=0; i<N; i++) {
	        	answer = Math.max(answer, dp[N-1][i]);
	        }
	        return answer;
	    }

	public static void main(String[] args) {
		// 1,2,3,4,5
		System.out.println(solution(new int [][] {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}}));

	}

}
