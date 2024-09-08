import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = 1000000;
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime , true);

        // 소수는 true
        // 0, 1은 소수가 아니므로 false
        isPrime [0] = isPrime [1] = false;
        for(int i=2; i*i<=n; i++){
            // 만약 i가 소수 혹은 아직 지워지지 않았다면
            if(isPrime[i]){
                // i의 배수 j들에 대해 isPrime[j] = false; 로 둔다.
                // i*i미만의 배수는 이미 지워졌으므로 신경쓰지 않는다.
                for(int j=i*i; j<=n; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        loop:while(true){
            String input = br.readLine();
            if(input.equals("0")) {
                System.out.print(sb.toString());
                return;
            }
            int x = Integer.parseInt(input);
            for (int i = 3; i <= x/2; i++) {
                if(!isPrime[i]||!isPrime[x-i]) continue;
                sb.append(x).append(" = ").append(i).append(" + ").append(x-i).append("\n");
                continue loop;
            }
            sb.append("Goldbach's conjecture is wrong.\n");
        }

    }
}