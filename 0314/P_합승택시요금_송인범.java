package March;

import java.util.Arrays;

public class P_합승택시요금_송인범 {
	 static int answer = Integer.MAX_VALUE;
	 public static int solution(int n, int s, int a, int b, int[][] fares) {
	        int [][] dist = new int [n+1][n+1];
	        for(int i=1; i<=n; i++) {
	        	for(int j=1; j<=n; j++) {
	        		if(i==j) dist[i][j]=0;
	        		else dist[i][j] = 111_111_111;
	        	}
	        } //setting
	        
	        for(int[] f: fares) { 
	        	dist[f[0]][f[1]]  = Math.min(dist[f[0]][f[1]] , f[2]);
	        	dist[f[1]][f[0]]  = Math.min(dist[f[1]][f[0]] , f[2]);
	        }
	        //floyd-warshall
	        for (int k = 1; k <= n; k++) {
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= n; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
	        
	        for(int idx =1; idx<=n; idx++) {
	        	answer = Math.min(answer, dist[s][idx]+dist[idx][a]+dist[idx][b]);
	        }
	        
	        
	        return answer;
	    }

	 public static void main(String[] args) {
		    System.out.println(solution(7,3,4,1,new int[][] {{5,7,9}, {4, 6, 4},{3, 6, 1}, {3, 2, 3}, {2, 1, 6}}));
		}

}
