import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> tmp = new ArrayList<>();
    static int N, K, ans;
    static int[] li;

    static void find(int n) {
        if (n == N) { return; }
        for(int i = n; i < N; i++) {
            tmp.add(i);
            ans += tmp.stream().map(e -> li[e]).reduce(Integer::sum).orElse(0) == K ? 1 : 0;
            find(i+1);
            tmp.remove(tmp.size()-1);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        li = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            li[i] = Integer.parseInt(st.nextToken());
        }
        find(0);
        System.out.println(ans);
    }
}