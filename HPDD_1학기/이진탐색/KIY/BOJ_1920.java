package KIY;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {
	private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr;
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			if(binarySearch(0, n-1, Integer.parseInt(st.nextToken()))) {
				bw.write(1 + "\n");
			} else {
				bw.write(0 + "\n");
			}
		}
		bw.close();
		br.close();
	}
	
	private static boolean binarySearch(int start, int end, int num) {
		while(start<=end) {
			int mid = (start + end) / 2;
			if(arr[mid] == num) {
				return true;
			} else if(arr[mid] > num) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

}
