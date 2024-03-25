package study;

import java.io.*;

public class B17609_회문_김인엽 {
    static int result = 3000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            String string = br.readLine();
            int len = string.length(); // 문자열 길이
            int left = 0; // 왼쪽
            int right = len - 1; // 오른쪽
            result = 2;
            checkPalindrome(string, left, right, 0);
            bw.write(result + "\n");
        }
        bw.close();
    }

    private static void checkPalindrome(String string, int left, int right, int changeCnt) {
        if(left == right || left == right+1) {
            result = Math.min(result, changeCnt);
            return;
        }
        if(changeCnt >= 2) {
            result = Math.min(result, 2);
            return;
        }
        if(string.charAt(left) == string.charAt(right)) {
            checkPalindrome(string, left+1, right-1, changeCnt);
        } else {
            if(string.charAt(left+1) == string.charAt(right)) {
                if(left+1 == right) {
                    result = Math.min(result, changeCnt+1);
                    return;
                }
                checkPalindrome(string, left+2, right-1, changeCnt+1);
            }
            if(string.charAt(left) == string.charAt(right-1)) {
                checkPalindrome(string, left+1, right-2, changeCnt+1);
            }
        }
    }
}
