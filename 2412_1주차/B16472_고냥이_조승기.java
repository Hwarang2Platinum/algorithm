import java.util.*;
import java.io.*;

public class B16472_고냥이_조승기 {
    static HashMap<Character, Integer> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] line = br.readLine().toCharArray();
        int[] li = new int[26];
        int ans = 0;
        ArrayDeque<Character> q = new ArrayDeque<>();
        int tc = 0;

        for (int i = 0; i < line.length; i++) {
            char c = line[i];
            int ci = line[i] - 'a';

            li[ci] += 1;
            tc += li[ci] == 1 ? 1 : 0;
            q.add(c);

            while (tc > N) {
                char tmpC = q.pollFirst();
                int tmpCi = tmpC - 'a';
                li[tmpCi] -= 1;
                tc -= li[tmpCi] == 0 ? 1 : 0;
            }
            ans = Math.max(ans, q.size());
        }
        System.out.println(ans);
    }
}
