import java.io.*;

public class B9095_123더하기_오화랑 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(input.readLine());
        int[] madeList = new int[12]; int target;
        madeList[1] = 1;
        madeList[2] = 2;
        madeList[3] = 4;

        for (int i = 4 ; i < 12 ; i++) {
            madeList[i] += (madeList[i-1] + madeList[i-2] + madeList[i-3]);
        }
        for (int i = 0 ; i < testCase ; i++) { // n은 11보다 작다
            target = Integer.parseInt(input.readLine());
            sb.append(madeList[target]).append("\n");
        }
        System.out.println(sb);
    }
}

// 1 2 3    4   5   6   7   8   9   10  11
// 1 2 4    7   13  24  44
// 1 -> 1
// 2 -> 11 / 2
// 3 -> 12 / 111 / 21 / 3
// 4 -> 13 / 112 / 22 / 121 / 1111 / 211 / 31
// 5 -> 113 / 23 / 122 / 1112 / 212 / 32 / 131 / 1121 / 221 / 1211 / 11111 / 2111 / 311
