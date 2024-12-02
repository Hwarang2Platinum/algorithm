import java.util.*;
import java.io.*;

public class B3040_백설공주와일곱난쟁이_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] li = new int[9];
        for (int i = 0; i < 9; i++) {
            li[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                int sum = 0;
                for (int k = 0; k < 9; k++) {
                    sum += k != i && k != j ? li[k] : 0;
                }
                if (sum != 100) continue;
                for (int k = 0; k < 9; k++) {
                    if (k == i || k == j) continue;
                    System.out.println(li[k]);
                }
            }
        }
    }
}
