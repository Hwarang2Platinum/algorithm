import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Implementation_14891_HR {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String [] line = null;
		int[][] gear = new int [4][8];
		
		for (int i = 0 ; i < 4 ; i++) {
			line = input.readLine().split("");
			for (int j = 0 ; j < 8 ; j++) {
				gear[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		int turnNum = Integer.parseInt(input.readLine());
		
		int gearNum, turnType;
		for (int i = 0 ; i < turnNum ; i++) {
			st = new StringTokenizer(input.readLine());
			gearNum = Integer.parseInt(st.nextToken());
			turnType = Integer.parseInt(st.nextToken());
			if (turnType == -1) 
				leftTurn(gear, gearNum);
			else
				rightTurn(gear, gearNum);
		}
		
		int sum = 0;
		for (int i = 0 ; i < 4 ; i++) {
			if (gear[i][0] == 1)
				sum += 1;
		}
		System.out.println(sum);
	}
	
	public static int[][] rightTurn(int[][] gear, int gearNum) {
		int temp, right, left;
		
		switch (gearNum) {
			case 1:	{
				right = gear[gearNum - 1][2];
				if (right != gear[gearNum][6])
					leftTurn(gear, gearNum + 1);
				break;
			}
			case 2: {
				left = gear[gearNum - 1][6];
				if (left != gear[gearNum - 2][2])
					leftTurn(gear, gearNum -1);
				
				right = gear[gearNum][2];
				if (right != gear[gearNum][6])
					leftTurn(gear, gearNum +1);
				break;
			}
			case 3:{
				left = gear[gearNum - 1][6];
				if (left != gear[gearNum - 2][2])
					leftTurn(gear, gearNum -1);
				
				right = gear[gearNum][2];
				if (right != gear[gearNum][6])
					leftTurn(gear, gearNum +1);
				break;
			}
			case 4: {
				left = gear[gearNum - 1][6];
				if (left != gear[gearNum - 2][2])
					leftTurn(gear, gearNum - 1);
				break;
			}
		}
		
		temp = gear[gearNum -1][7];
		for (int i = 7 ; i > 0 ; i--) {
			if (i > 0)
				gear[gearNum -1][i] = gear[gearNum-1][i-1];
			else
				gear[gearNum -1][i] = temp;
		}
		
		return gear;
	}
	
	public static int[][] leftTurn(int[][] gear, int gearNum) {
		int temp, right, left;
		
		switch (gearNum) {
			case 1:	{
				right = gear[gearNum - 1][2];
				if (right != gear[gearNum][6])
					rightTurn(gear, gearNum + 1);
				break;
			}
			case 2: {
				left = gear[gearNum - 1][6];
				if (left != gear[gearNum - 2][2])
					rightTurn(gear, gearNum -1);
				
				right = gear[gearNum][2];
				if (right != gear[gearNum][6])
					rightTurn(gear, gearNum +1);
				break;
			}
			case 3:{
				left = gear[gearNum - 1][6];
				if (left != gear[gearNum - 2][2])
					rightTurn(gear, gearNum -1);
				
				right = gear[gearNum][2];
				if (right != gear[gearNum][6])
					rightTurn(gear, gearNum +1);
				break;
			}
			case 4: {
				left = gear[gearNum - 1][6];
				if (left != gear[gearNum - 2][2])
					rightTurn(gear, gearNum - 1);
				break;
			}
		}
		
		temp = gear[gearNum -1][0];
		for (int i = 0 ; i < 8 ; i++) {
			if (i < 7)
				gear[gearNum -1][i] = gear[gearNum-1][i+1];
			else
				gear[gearNum -1][i] = temp;
		}
		return gear;
	}
}
// 1번 : 2
// 2번 : 6 2
// 3번 : 6 2
// 4번 : 6




//for (int i = 0 ; i < 4 ; i++) {
//for (int j = 0 ; j < 8 ; j++) {
//	System.out.printf("%d ", gear[i][j]);
//}
//System.out.println();
//}