import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int T;
    static boolean[] isNotPrime;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        isNotPrime = new boolean[1_000_000 + 1];

        for (int i = 2; i < 500_000; i++){
            if (!isNotPrime[i]){
                for (int j = i * 2; j <= 1000000; j += i){
                    isNotPrime[j] = true;
                }
            }
        }

        ArrayList<Integer> primeList = new ArrayList<>();
        HashSet<Integer> primeSet = new HashSet<>();
        for (int i = 2; i < 1_000_000 + 1; i++){
            if (!isNotPrime[i]){
                primeList.add(i);
                primeSet.add(i);
            }
        }
        while(true){
            int K = Integer.parseInt(br.readLine());
            if (K == 0)
                break;
            int a = -1, b = -1;
            for (int i = 0; i <= primeList.size() / 2; i++){
                a = primeList.get(i);
                if (primeSet.contains(K - a)){
                    b = K -a;
                    break;
                }
            }
            if (b > 0){
                sb.append(K).append(" = ").append(a).append(" + ").append(b).append("\n");
            }else{
                sb.append("Goldbach's conjecture is wrong.\n");
            }
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}