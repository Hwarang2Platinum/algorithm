import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[][][] arrIdx;
	static int N,M,R;
	static int ans = 0;
	static int r_start, r_end;
	static int c_start, c_end;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		arrIdx = new int[N][M][2];
		for(int i =0 ; i<N;i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(o->Integer.parseInt(o)).toArray();
			for(int j=0;j<M;j++) {
				arrIdx[i][j][0] = i;
				arrIdx[i][j][1] = j;
			}
		}
		
		for (int j = 0; j <R; j++) {
			r_start=0;
			r_end=N;
			c_start=0; 
			c_end=M;
			while(r_start<r_end && c_start<c_end) {
				// 위 => c증가, r고정
				for(int i=c_start;i<c_end-1;i++) {
					rotateOne(r_start,i);
				}
				
				// 오른쪽 => r증가
				for(int i=r_start;i<r_end-1;i++) {
					rotateOne(i,c_end-1);
				}
				
				// 아래
				for(int i=c_end-1;i>=c_start+1;i--) {
					rotateOne(r_end-1,i);
				}
				
				// 왼쪽
				for(int i=r_end-1;i>=r_start+1;i--) {
					rotateOne(i,c_start);
				}
				r_start++;
				r_end--;
				c_start++;
				c_end--;
			}
		}
		
		for(int i =0 ; i<N;i++) {
			for(int j=0;j<M;j++) {
				int tmpR = arrIdx[i][j][0];
				int tmpC = arrIdx[i][j][1];
				if(j==M-1) System.out.println(arr[tmpR][tmpC]);
				else System.out.print(arr[tmpR][tmpC]+" ");
			}
		}
		
	}
	// 
	static void rotateOne(int r, int c) {
		int tmpR = arrIdx[r][c][0];
		int tmpC = arrIdx[r][c][1];
		// 윗줄이 경우
		if(tmpR==r_start && tmpC!=c_end - 1) {
			arrIdx[r][c][1]++;
		}
		if(tmpC==c_end - 1 && tmpR!=r_end-1) {
			arrIdx[r][c][0]++;
		}
		if(tmpR==r_end - 1 && tmpC!=c_start) {
			arrIdx[r][c][1]--;
		}
		if(tmpC==c_start && tmpR!=r_start) {
			arrIdx[r][c][0]--;
		}
	}

}
