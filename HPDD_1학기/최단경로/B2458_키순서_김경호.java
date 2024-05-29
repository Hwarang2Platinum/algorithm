package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int INF = Integer.MAX_VALUE;
		int adjMatrix[][]  = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i==j) continue;
				adjMatrix[i][j] = INF;
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a-1][b-1] = 1;
		}
		
		// 플로이드 와샬 
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(adjMatrix[i][k]!= INF && adjMatrix[k][j]!= INF && 
							adjMatrix[i][j] > adjMatrix[i][k]+adjMatrix[k][j]) {
						adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
					}
				}
			}
		}
		int[] ans = new int[N];
		// 0번 노드부터 => i번노드에서 갈 수 있는 노드들 + i번노드로 갈 수 있는 노드들
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(adjMatrix[i][j] !=INF) {
					ans[i] ++;
					if(adjMatrix[i][j] > 0) ans[j]++;
				}
			}
		}
		
		System.out.println(Arrays.stream(ans).filter(o->o==N).count());
	
	}
}
 