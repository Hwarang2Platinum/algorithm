import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        // A*A*A ... = c*몫 + 나머지
        long ans = func(A, B, C);
        System.out.println(ans);
    }

    public static long func(long A, long B, long C) {
        if(B==0) return 1;
        if(B==1) return A%C;
        long oddVal = 1;
        if(B%2!=0) oddVal = A%C;
        long val = func(A, B/2, C)%C;
        return (((val*val)%C)*oddVal)%C;
    }
}