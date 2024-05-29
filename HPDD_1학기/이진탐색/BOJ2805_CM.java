import java.io.*;
import java.util.*;

public class BOJ2805_CM {

    static int N;
    static int M;
    static int[] tree;
    static long get;
    static long result;
    static int max_tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tree = new int[N];
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            max_tree = Math.max(max_tree, tree[i]);
        }
        Cut(0,max_tree);
        System.out.println(result);

    }

    public static void Cut(int lo, int hi){
        while(lo<hi) {
            get = 0;
            int mid = (lo + hi) / 2 + 1 ;
            for (int i = 0; i < N; i++) {
                if (tree[i] > mid) {
                    get += tree[i] - mid;
                }
            }
            if (get >= M) {
                Cut(mid,hi);
                return;
            } else {
                Cut(lo,mid-1);
                return;
            }
        }
        if(lo>=hi)
        result = lo;
    }
}
