import java.io.*;
import java.util.*;

public class B2096_내려가기_이찬민 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N][3];
        int maxDp[][] = new int[N][3];
        int minDp[][] = new int[N][3];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        maxDp[0][0] = arr[0][0];
        minDp[0][0] = arr[0][0];
        maxDp[0][1] = arr[0][1];
        minDp[0][1] = arr[0][1];
        maxDp[0][2] = arr[0][2];
        minDp[0][2] = arr[0][2];

        for(int i=1; i<N; i++) {
            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + arr[i][0];
            maxDp[i][1] = Math.max(Math.max(maxDp[i-1][0], maxDp[i-1][1]), maxDp[i-1][2]) + arr[i][1];
            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2])+arr[i][2];

            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + arr[i][0];
            minDp[i][1] = Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]) + arr[i][1];
            minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2])+arr[i][2];

        }

        int max = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));
        int min = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));

        System.out.println(max +" " + min);
    }
}
