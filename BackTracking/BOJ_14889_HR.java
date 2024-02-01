import java.io.*;
import java.util.*;

public class BOJ_14889_HR {
	static int[][] pointArr;
	static int[] team1;
	static int[] team2;
	static int minResult = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int size = Integer.parseInt(input.readLine());
		pointArr = new int [size][size];
		team1 = new int [size / 2];
		team2 = new int [size / 2];
		
		for (int i = 0 ; i < size ; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0 ; j < size ; j++) {
				pointArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		selectTeam(0, size, 0);
		System.out.println(minResult);
	}
	
	public static void selectTeam(int cnt, int size, int start) {
		if (cnt == size/2) {
			otherTeam(size);
			calPointDiff();
			return;
		}
		for (int i = start ; i < size ; i++) {
			team1[cnt] = i;
			selectTeam(cnt + 1, size, i + 1);
		}
	}
	
	public static void otherTeam(int size) {
		boolean isIn;
		int index = 0;
		for (int i = 0 ; i < size ; i++) {
			isIn = false;
			for (int team1Mem : team1) {
				if (i == team1Mem)
					isIn = true;
			}
			if (!isIn) {
				team2[index] = i;
				index++;
			}
		}
	}
	
	public static void calPointDiff() {
		int team1Point = 0;
		int team2Point = 0;
		for (int eachMem : team1) {
			for (int eachMem2 : team1) {
				team1Point += pointArr[eachMem][eachMem2];
			}
		}	
		for (int eachMem : team2) {
			for (int eachMem2 : team2) {
				team2Point += pointArr[eachMem][eachMem2];
			}
		}
		minResult = Math.min(minResult, Math.abs(team1Point - team2Point));
	}
}
