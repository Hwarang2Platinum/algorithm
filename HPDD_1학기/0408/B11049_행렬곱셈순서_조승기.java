import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Pair[] li;
    static int[][] dp;
    static int inf = Integer.MAX_VALUE;
    static int find(int a, int b) {
        if (dp[a][b] != inf) return dp[a][b];
        if (a==b) {
            dp[a][b] = 0;
            return 0;
        }

        for (int i = a; i < b; ++i) {
            dp[a][i] = find(a, i);
            dp[i + 1][b] = find(i + 1, b);
            dp[a][b] = Math.min(dp[a][b],  dp[a][i] + dp[i+1][b] + li[a].x * li[i].y * li[b].y);
        }
        return dp[a][b];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        li = new Pair[N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], inf);
            StringTokenizer st = new StringTokenizer(br.readLine());
            li[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(find(0, N - 1));
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
