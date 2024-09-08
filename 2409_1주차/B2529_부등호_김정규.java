package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B2529 {
	static int n;
	static int[] nums;
	static String[] arr;
	static boolean[] visited;
	static ArrayList<String> ans = new ArrayList<>();
	static BufferedReader br = null;
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()) + 1;
		nums = new int[10];
		for (int i = 0; i < 10; i++) {
			nums[i] = i;
		}
		arr = new String[n-1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n-1; i++) {
			arr[i] = st.nextToken();
		}
		
		for (int i = 0; i < 10; i++) {
			String startNum = Integer.toString(i);
			visited = new boolean[10];
			visited[i] = true;
			dfs(i, 0, startNum);
			visited[i] = false;
		}
        System.out.println(ans.get(ans.size()-1));
        System.out.println(ans.get(0));
	}
	
	static void dfs(int start, int cnt, String num) {
		if (num.length() == n) {
			ans.add(num);
			return;
		}
		for (int i =0; i < 10; i++) {
			if (!visited[i]) {
				String tmp = arr[cnt];
				if (tmp.equals(">")) {
					if (start > i) {
                        visited[i] = true;
                        dfs(i, cnt+1, num+i);
                        visited[i] = false;
                    }
                } else {
                    if (start < i) {
                        visited[i] = true;
                        dfs(i, cnt+1, num+i);
                        visited[i] = false;
                    }
                }
			}
		}
	}
}
