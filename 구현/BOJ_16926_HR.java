import java.io.*;
import java.util.*;

public class BOJ_16926_HR {
	static int[][] Arr;
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(input.readLine());
		StringBuilder sb = new StringBuilder();
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int arrRot = (row <= col ? row : col) / 2;
		int cntRot = Integer.parseInt(st.nextToken());
		Arr = new int [row + 1][col + 1];
		
		for (int i = 1 ; i <= row ; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 1 ; j <= col ; j++) {
				Arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0 ; i < cntRot ; i++)
			turn(1, row, col, arrRot, 0);

		
		for (int i = 1 ; i <= row ; i++) {
			for (int j = 1 ; j <= col ; j++) {
				sb.append(Arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static void turn(int start, int row, int col, int arrRot, int cnt) {
		if (cnt == arrRot)
			return;

		int temp1 = Arr[start][start];
		int temp2 = Arr[row][start];
		int temp3 = Arr[row][col];
		
		// Top Line Shift
		for (int i = start ; i < col ; i++) {
			Arr[start][i] = Arr[start][i+1];
		} 
		// Left Line Shift
		for (int i = row ; i > start ; i--) {
			if (i == start + 1) {
				Arr[i][start] = temp1;
				break;
			}
			Arr[i][start] = Arr[i-1][start];
		}
		// Bottom Line Shift
		for (int i = col ; i > start ; i--) {
			if (i == start + 1) {
				Arr[row][i] = temp2;
				break;
			}
			Arr[row][i] = Arr[row][i-1];
		}
		// Right Line Shift
		for (int i = start ; i < row ; i++) {
			if (i == row -1) {
				Arr[i][col] = temp3;
				break;
			}
			Arr[i][col] = Arr[i+1][col];	
		}
		turn(start + 1, row - 1, col -1, arrRot, cnt + 1);
	}
}
