import java.util.*;
import java.io.*;

/*
* 학교 B
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[100001];

        int l=100000; int r=0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x] = y;
            l = Math.min(l, x);
            r = Math.max(r, x);
        }

        int ans = 0;
        if(l<S) ans += S-l;
        if(r>S) ans += r-S;
        int curK = K;
        while(l<S){
            if(arr[l]>curK) {
                arr[l] -= curK;
                curK = K;
                ans += 2*(S-l);
            }else {
                curK -= arr[l];
                arr[l] = 0;
                while(arr[l]==0 && l<S) {
                    ans++;
                    l++;
                }
            }
        }

        curK = K;
        while(r>S){
            if(arr[r]>curK) {
                arr[r] -= curK;
                curK = K;
                ans += 2*(r-S);
            }else {
                curK -= arr[r];
                arr[r] = 0;
                while(arr[r]==0 && r>S) {
                    ans++;
                    r--;
                }
            }
        }

        System.out.println(ans);
    }
}