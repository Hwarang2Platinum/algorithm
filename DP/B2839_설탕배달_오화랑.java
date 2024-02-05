import java.util.Scanner;

public class B2839_설탕배달_오화랑 {
	static int[] weightList = {3,5};
	static int[] madeList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weight = sc.nextInt();
		madeList = new int[weight + 1];
		
		int temp1, temp2;
		for (int i = 3 ; i <= weight ; i++) {
			temp1 = 0; temp2 = 0;
			if (i == 3 | i == 5) {
				madeList[i] = 1;
				continue;
			}
			if (madeList[i-3] > 0)
				temp1 = madeList[i-3] + 1;
			
			if (i > 5 && madeList[i-5] > 0)
				temp2 = madeList[i-5] + 1;
			
			
			
			if (temp1 <= 0) {
				if (temp2 > 0)
					madeList[i] = temp2;
				else
					madeList[i] = 0;
			}
			
			else { // temp1 > 0
				if (temp2 > temp1)
					madeList[i] = temp1;
				else { // temp2 <= temp1
					if (temp2 <= 0)
						madeList[i] = temp1;
					else
						madeList[i] = temp2;
				}
			}
		}
		System.out.println(madeList[weight] == 0 ? -1 : madeList[weight]);
	}
}
