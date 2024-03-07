import java.io.*;
import java.util.*;

public class P_유전법칙_오화랑 {
    static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int gen = Integer.parseInt(st.nextToken());
        int th = Integer.parseInt(st.nextToken());
        reproduce(gen, th);
        System.out.println(answer);
    }
    public static void reproduce(int gen, int th) {
        if (gen == 1) { answer = "2"; return; }
        if (gen == 2) {
            switch (th) {
                case 1: answer = "1"; break;
                case 2: answer = "2"; break;
                case 3: answer = "2"; break;
                case 4: answer = "3"; break;
            }
            return;
        }
        int Q100 = (int) Math.pow(4, gen - 1);
        int Q25 = (int) Math.pow(4, gen - 2);
        int Q75 = Q100 - Q25;
        int Q50 = Q100 - 2 * Q25;
        if (th <= Q25) answer = "1";
        else if (th <= Q50) reproduce(gen - 1, th - Q25);
        else if (th <= Q75) reproduce(gen - 1, th - Q50);
        else answer = "3";
    }
}

/*
class Solution {
    static String answer;
    public String[] solution(int[][] queries) {
        String[] answers = new String[queries.length];
        for (int i = 0 ; i < queries.length ; i++) {
            int gen = queries[i][0];
            int th = queries[i][1];
            reproduce(gen, th);
            answers[i] = answer;
        }
        return answers;
    }
    public static void reproduce(int gen, int th) {
        if (gen == 1) { answer = "Rr"; return; }
        if (gen == 2) {
            switch (th) {
                case 1: answer = "RR"; break;
                case 2: answer = "Rr"; break;
                case 3: answer = "Rr"; break;
                case 4: answer = "rr"; break;
            }
            return;
        }
        int Q100 = (int) Math.pow(4, gen - 1);
        int Q25 = (int) Math.pow(4, gen - 2);
        int Q75 = Q100 - Q25;
        int Q50 = Q100 - 2 * Q25;
        if (th <= Q25) answer = "RR";
        else if (th <= Q50) reproduce(gen - 1, th - Q25);
        else if (th <= Q75) reproduce(gen - 1, th - Q50);
        else answer = "rr";
    }
}
 */