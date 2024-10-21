import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] NGF = new int[1_000_001];
        int[] ans = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            NGF[arr[i]]++;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < N+1 ; i++) {
            while (!stack.isEmpty() && NGF[arr[stack.peek()]] < NGF[arr[i]]){
                ans[stack.pop()] = arr[i];
            }
            stack.add(i);
        }

        while (!stack.isEmpty()){
            ans[stack.pop()] = -1;
        }

        for(int i = 1; i<N+1; i++)
            sb.append(ans[i]).append(" ");

        System.out.println(sb);

    }

}
