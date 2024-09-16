import java.util.*;
import java.io.*;

public class B15989_123더하기4_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] li = new int[10000][3];
        li[0][0] = 1;
        li[1][0] = 1;
        li[1][1] = 1;
        li[2][0] = 1;
        li[2][1] = 1;
        li[2][2] = 1;
        for (int i = 3; i < 10000; i++) {
            li[i][0] = li[i - 1][0];
            li[i][1] = li[i - 2][0] + li[i - 2][1];
            li[i][2] = li[i - 3][0] + li[i - 3][1] + li[i - 3][2];
        }
        for (int t = 0, T = Integer.parseInt(br.readLine()); t <T; t++) {
            int ans = 0;
            int n = Integer.parseInt(br.readLine())-1;
            for (int i = 0; i < 3; i++) {
                ans += li[n][i];
            }
            System.out.println(ans);
        }

    }
}
