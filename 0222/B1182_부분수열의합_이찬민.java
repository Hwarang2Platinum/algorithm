import java.io.*;
import java.util.*;

public class B1182_부분수열의합_이찬민 {
    static int N,S, result;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result =0;
        booboon(0,0);
        if (S == 0) {
            System.out.println(result-1);// S=0 일 경우 아무것도 안뽑아도 result 증가해버림
        }else {
            System.out.println(result);
        }


    }

    public static void booboon(int depth, int sum) {
        if (depth == N && sum==S) {
            result++;
            return;
        }
        else if (depth == N) {
            return;
        }


        booboon(depth+1, sum+arr[depth]); //넣는다
        booboon(depth+1, sum); //안넣는다
    }
}
