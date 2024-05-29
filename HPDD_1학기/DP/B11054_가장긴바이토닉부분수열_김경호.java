import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		int[] dpLeft = new int[N];
		int[] dpRight = new int[N];
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			for(int j=i-1;j>=0;j--) {
				if(nums[j] < nums[i]) {
					dpLeft[i] = Math.max(dpLeft[i], dpLeft[j]+1);
				}
			}
		}
		for(int i=N-1;i>=0;i--) {
			for(int j=i+1;j<N;j++) {
				if(nums[j] < nums[i]) {
					dpRight[i]= Math.max(dpRight[i], dpRight[j]+1);
				}
			}
		}
//		System.out.println(Arrays.toString(dpLeft));
//		System.out.println(Arrays.toString(dpRight));
		int max = -1;
		for(int i=0;i<N;i++) {
			max = Math.max(dpLeft[i]+ dpRight[i],max);
		}
		System.out.println(max+1);
	}
	
	
	
}
