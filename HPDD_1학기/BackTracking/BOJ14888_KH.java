import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int[] operators;
	static int N,max,min;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(o->Integer.parseInt(o)).toArray();
		
		operators = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		operators[0] = Integer.parseInt(st.nextToken());
		operators[1] = Integer.parseInt(st.nextToken());
		operators[2] = Integer.parseInt(st.nextToken());
		operators[3] = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		bt(1,arr[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void bt(int depth, int accum) {
		if(depth==N) {
			max = Math.max(max, accum);
			min = Math.min(min, accum);
			return;
		}
		int newAccum = 0;
		for(int i=0;i<4;i++) {
			if(operators[i]<=0) continue;
			switch(i) {
			case 0:
				newAccum = accum+arr[depth];
				break;
			case 1:
				newAccum = accum-arr[depth];
				break;
			case 2:
				newAccum = accum*arr[depth];
				break;
			case 3:
				newAccum = accum/arr[depth];
				break;
			}
			operators[i]--;
			bt(depth+1, newAccum);
			operators[i]++;
		}
	}
}
