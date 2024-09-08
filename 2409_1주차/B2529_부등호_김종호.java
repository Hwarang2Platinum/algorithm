import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N;
    static String max = "0000000000", min = "9999999999";
    static boolean[] visit;
    static char[] arr;
    static ArrayDeque<Character> dq = new ArrayDeque<>();

    public void dfs(int count){
        if (count >= N){
            StringBuilder sb = new StringBuilder();
            for (char c : dq){
                sb.append(c);
            }
            String tmp = sb.toString();
            if (max.compareTo(tmp) < 0){
                max = tmp;
            }
            if (min.compareTo(tmp) > 0){
                min = tmp;
            }
            return;
        }
        int lastNum = Integer.valueOf(dq.peekLast() - '0');
        if (arr[count] == '<'){
            for (int i = lastNum + 1; i < 10; i++){
                if (visit[i])
                    continue;
                visit[i] = true;
                dq.addLast((char)(i + '0'));
                dfs(count + 1);
                dq.pollLast();
                visit[i] = false;
            }
        }else{
            for (int i = lastNum - 1; i >= 0; i--){
                if (visit[i])
                    continue;
                visit[i] = true;
                dq.addLast((char)(i + '0'));
                dfs(count + 1);
                dq.pollLast();
                visit[i] = false;
            }
        }
    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new char[N];
        visit = new boolean[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            arr[i] = Character.valueOf(st.nextToken().charAt(0));
        }
        for (int i = 0; i < 10; i++){
            visit[i] = true;
            dq.addLast((char)(i + '0'));
            dfs(0);
            dq.pollLast();
            visit[i] = false;
        }
        System.out.println(max);
        System.out.println(min);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}