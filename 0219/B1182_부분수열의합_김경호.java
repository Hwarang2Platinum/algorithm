import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,S,answer=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
	
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		bt(0,arr,0,0);
		
		System.out.println(answer);
	}
	
	static void bt(int depth, int[] arr, int before, int result) {
		if(depth!=0 && result == S) {
			answer++;
		}
		
		for(int i = before; i<N;i++) {
			result+=arr[i];
			bt(depth+1,arr,i+1,result);
			result-=arr[i];
		}
	}
}
 