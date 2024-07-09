import java.io.*;
import java.util.*;

public class B15651_N과M3_이찬민 {
    static StringBuilder sb = new StringBuilder();
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        joongbok(N, M, 0);
        System.out.println(sb);
    }
    public static void joongbok(int N, int M, int depth){
        if (M == depth) {
            for(int num : arr) {
                sb.append(num+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++){
            arr[depth] = i+1;
            joongbok(N, M , depth+1);
        }
    }
}