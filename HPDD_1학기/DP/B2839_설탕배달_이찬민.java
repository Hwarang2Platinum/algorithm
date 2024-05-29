import java.io.*;
import java.util.*;
public class B2839_설탕배달_이찬민 {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int sugar = N;
        int result = 0;
        //-------------------------------------------- 204ms
//        while (sugar>0) {
//            if (sugar < 3) {
//                result = -1;
//                break;
//            }
//            if (sugar % 5 == 0) {
//                result += sugar / 5;
//                break;
//            } else {
//                sugar -= 3;
//                result++;
//            }
//        }
        //---------------------------------------------  204ms
        if (N == 4 || N == 7) {
            result = -1;
        } else if (N % 5 == 0) {
            result = N / 5;
        } else if (N % 5 == 1) {
            result = N / 5 + 1;
        } else if (N % 5 == 2) {
            result = N / 5 + 2;
        } else if (N % 5 == 3) {
            result = N / 5 + 1;
        } else if (N % 5 == 4) {
            result = N / 5 + 2;
        }

        System.out.println(result);
    }
}

