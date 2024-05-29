import java.io.*;
import java.util.*;

public class BOJ2012_CM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] want = new int[N];
        long rage = 0; //long으로 줘야 안터짐

        for(int i=0; i<N; i++){
            want[i] = Integer.parseInt(br.readLine()); // 입력받고 순서대로 정렬
        }
        Arrays.sort(want);

        for(int i=0; i<N; i++){
            rage += Math.abs((i+1)-want[i]);  // 1등부터 쭈우우욱 빼줌
        }

        System.out.println(rage);
    }


}

