package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17299 {

	static int max = 1000000;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        int count[] = new int[max];
        int index[] = new int[n];
        int ans[] = new int[n];
        
        StringTokenizer st =  new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
	    index[i] = Integer.parseInt(st.nextToken());
            count[index[i]]++;
        }
        
        for(int i=0; i<n; i++) {
            while(!stack.empty() && count[index[i]]>count[index[stack.peek()]]) {
                ans[stack.pop()] = index[i];
            }
            stack.push(i);
        }
        
        while(!stack.empty()) {
            ans[stack.pop()] = -1;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
        	sb.append(ans[i] + " ");
        }
        System.out.println(sb.toString());
	}
}
