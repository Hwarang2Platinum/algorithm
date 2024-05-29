package March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class S14510_나무높이_송인범 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T =  Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int cnt = 0;
			int num = Integer.parseInt(br.readLine());
			Integer [] tree = new Integer[num];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<num; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(tree, Collections.reverseOrder());
			int target  = tree[0];
			int even = 0, odd = 0;
			for(int i=1; i<tree.length; i++) {
				int diff = target - tree[i];
				if(diff == 0)continue;
				even +=diff/2;
				odd +=diff%2;
			}
			
			if(even > odd) {
				while(Math.abs(even-odd)>1) {
					even--;
					odd+=2;
				}
			}
				
				
			if(odd>even)cnt =odd*2-1;
			else if(even>odd)cnt = even*2;
			else cnt = even+odd;
			
			
			System.out.println("#"+(t+1)+" "+cnt);
		}

	}

}
