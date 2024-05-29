import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1629_곱셈_김인엽 {
    static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(power(A, B));
    }

    public static long power(int A, int B) {
        if(B == 1) return A%C;
        long tmp = power(A, B/2)%C;
        if(B % 2 == 0) {
            return tmp*tmp%C;
        } else {
            return ((tmp*tmp%C)*A%C)%C;
        }
    }
}
