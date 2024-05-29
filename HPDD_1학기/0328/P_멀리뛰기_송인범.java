package March;

public class P_멀리뛰기_송인범 {
	
	 public static long solution(int n) {
	        long answer = 0;
	        long [] dp =  new long[n+2];
	        dp[1]=1;
	        dp[2]=2;
	        
	        for(int i=3; i<n+1; i++) {
	        	dp[i] = dp[i-2]+dp[i-1];
	        	
	        }
	        
	        answer = dp[n];
	        return answer%1234567;
	    }
	

	public static void main(String[] args) {
		System.out.println(solution(4));
		
	}

}
