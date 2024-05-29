import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> tmp = new LinkedList<>();
    static boolean[] isv = new boolean[9];
    static int N;
    static int ans;
    static int[][] li;
    static void find() {
        if (tmp.size() == 9) {
            solve(tmp);
            return;
        }

        for(int i = 1; i < 9; i++) {
            if (isv[i]) { continue; }

            isv[i] = true;
            tmp.add(i);
            if (tmp.size() == 3) { tmp.add(0); }
            find();
            isv[i] = false;
            if (tmp.get(tmp.size()-1) == 0) { tmp.remove(tmp.size()-1); }
            tmp.remove(tmp.size() - 1);
        }
    }

    static void solve(List<Integer> sequence) {
        int sunsu = 0;
        int inning = -1;
        int score = 0;
        while (++inning < N) {
            int out = 0;
            int base = 0;
            while (out < 3) {
                int hit = li[inning][sequence.get(sunsu)];
                sunsu = (sunsu + 1) % 9;
                if (hit == 0) { out++; continue; }

                base = base << hit;
                base |= 1 << (hit-1);

                score += Integer.bitCount(base & -8);
                base &= 7;

            }
        }
        ans = Math.max(score, ans);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        li = new int[N][9];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        find();
        System.out.println(ans);
    }
}
/*
1
4
40 30 30 50
 */