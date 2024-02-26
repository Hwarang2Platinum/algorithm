import java.io.*;
import java.util.*;
public class B10972_다음순열_이찬민 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (nextPermutation(arr)) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

    public static boolean nextPermutation(int[] arr) {
        if(N==1)return false; //N이 1일때 생각해줘야함
        int i=N-1,j=N-1,k=N-1;
        while (arr[i - 1] >= arr[i]) {
            i--;
            if (i == 0) {
                return false;
            }
        }

        while (arr[i-1]>arr[j]) j--;
        swap(arr, i - 1, j);
        while (i < k) {
            swap(arr, i, k);
            i++;
            k--;
        }
        return true;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
