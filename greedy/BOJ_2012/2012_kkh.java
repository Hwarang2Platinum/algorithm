package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int N = Integer.valueOf(br.readLine());
			int[] nums = new int[N];
			
			for(int i=0; i<N; i++) {
				nums[i] = Integer.valueOf(br.readLine());
			}
			
			long ans = 0;
			Arrays.sort(nums);
//			System.out.println(Arrays.toString(nums));
			for(int i=0; i<N; i++) {
				ans+= Math.abs(nums[i] - (i+1)) ;
			}
			System.out.println(ans);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
