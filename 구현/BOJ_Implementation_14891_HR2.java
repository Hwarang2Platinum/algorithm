import java.io.*;
import java.util.*;

public class BOJ_Implementation_14891_HR2 {
	
	static int[][] gear = new int [4][8];
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String [] line = null;
		
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
			gearNum = Integer.parseInt(st.nextToken()) - 1;
			turnType = Integer.parseInt(st.nextToken());
			Turn(gearNum, turnType);
		}
		
		int total = 0;
		for (int i = 0 ; i < 4 ; i++) {
			total += Math.pow(2,i)*gear[i][0];
		}
		System.out.println(total);
	}
	
	public static void rotate(int gearNum, int turnType) {
		if (turnType == 1) {
			int temp = gear[gearNum][7];
			for (int i = 7 ; i >= 0 ; i--) {
				if (i > 0)
					gear[gearNum][i] = gear[gearNum][i-1];
				else
					gear[gearNum][i] = temp;
			}
		} else {
			int temp = gear[gearNum][0];
			for (int i = 0 ; i < 8 ; i++) {
				if (i < 7)
					gear[gearNum][i] = gear[gearNum][i+1];
				else
					gear[gearNum][i] = temp;
			}
		}
	}
	
	public static void Turn(int gearNum, int turnType) {
		goLeft(gearNum - 1, -turnType);
		goRight(gearNum + 1, -turnType);
		rotate(gearNum, turnType);
	}
	
	public static void goLeft(int gearNum, int turnType) {
		if (gearNum < 0) return;
		if (gear[gearNum][2] == gear[gearNum + 1][6]) return;
		goLeft(gearNum - 1, -turnType);
		rotate(gearNum, turnType);
	}
	
	public static void goRight(int gearNum, int turnType) {
		if (gearNum > 3) return;
		if (gear[gearNum][6] == gear[gearNum - 1][2]) return;
		goRight(gearNum + 1, - turnType);
		rotate(gearNum, turnType);
	}
}
