import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int MAX = 1_000_000;
        boolean[] isPrime = new boolean[MAX + 1];
        List<Integer> primeNumbers = new ArrayList<>();

        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 홀수 소수만 리스트에 추가
        for (int i = 3; i <= MAX; i += 2) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }

        while(true) {
            int n = Integer.parseInt(br.readLine());

            if(n == 0) break; // 0이면 끝

            boolean isValid = false;
            for(int i=3; i<=n/2; i+=2) {
                if(isPrime[i] && isPrime[n-i]) {
                    isValid = true;
                    bw.write(n + " = " + i + " + " + (n - i) + "\n");
                    break;
                }
            }

            if(!isValid) {
                bw.write("Goldbach's conjecture is wrong.\n");
            }
            bw.flush();
        }
        bw.close();
    }
}
