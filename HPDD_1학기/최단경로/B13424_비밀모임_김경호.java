import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		final int INF = 1_000_000_000;
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); 
			int M = Integer.parseInt(st.nextToken()); 
			int[][] adjMatrx = new int[N+1][N+1];
			
			for(int i=1;i<N+1;i++) {
				for(int j=1;j<N+1;j++) {
					if(i==j) continue;
					else adjMatrx[i][j] = INF;
				}
			}
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				adjMatrx[a][b] = c;
				adjMatrx[b][a] = c;
			}
			
			int K = Integer.parseInt(br.readLine());
			int[] magicians = new int[K];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<K;i++) {
				magicians[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int k=1;k<N+1;k++) {
				for(int i=1;i<N+1;i++) {
					for(int j=1;j<N+1;j++) {
						adjMatrx[i][j] = Math.min(adjMatrx[i][j], adjMatrx[i][k]+adjMatrx[k][j]);
					}
				}
			}
			int minIdx = 0; int minValue = Integer.MAX_VALUE;
			for(int i=1;i<N+1;i++) {
				int localSum = 0;
				for(int num:magicians) {
//					System.out.println(adjMatrx[num][i]);
					localSum += adjMatrx[num][i];
				}
				if(localSum < minValue) {
					minIdx = i;
					minValue = localSum;
				}
			}
			sb.append(minIdx+"\n");
		}
		System.out.println(sb.toString());
		
		
	}
	
}