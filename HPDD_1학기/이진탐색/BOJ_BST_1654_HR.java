import java.io.*;
import java.util.*;

public class BOJ_BST_1654_HR {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(input.readLine());
		int haveLan = Integer.parseInt(st.nextToken());
		int wantLan = Integer.parseInt(st.nextToken());
		long [] lanArray = new long [haveLan];
		
		long maxLan = 0;
		for (int i = 0 ; i < haveLan ; i++) {
			lanArray[i] = Long.parseLong(input.readLine());
			if (lanArray[i] > maxLan)
				maxLan = lanArray[i];
		}
		
		long start = 1;
		long end = maxLan;
		long mid = (start + end)/2;
		long result = 0;
		while (start <= end) {
			mid = (start + end)/2;
			if (getLan(lanArray, mid) >= wantLan)
				start = mid + 1;
			else 
				end = mid - 1;
//			else {
//				result = mid;
//				break;
//			}
			result = end;
		}
		System.out.println(result);		
	}
	
	public static long getLan(long[] lanArray, long cutLan) {
		long count = 0;
		for (long eachLan : lanArray) {
			if (eachLan >= cutLan)
				count += (eachLan / cutLan);
		}
		return count;
	}
}
