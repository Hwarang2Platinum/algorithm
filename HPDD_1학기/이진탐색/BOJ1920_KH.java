import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] nums;
	static int N;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(o->Integer.parseInt(o)).sorted().toArray();
		int M = Integer.parseInt(br.readLine());
		for(int num: Arrays.stream(br.readLine().split(" ")).mapToInt(o->Integer.parseInt(o)).toArray()) {
			find(num);
		}
		
	}
	
	public static void find(int num) {
		int start = 1;
		int end = N-1;
		int mid = 0;
		while(start<=end) {
			mid = (start+end)/2;
			if(nums[mid]<=num) {
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		System.out.println(nums[start-1]==num?1:0);
	}
}
