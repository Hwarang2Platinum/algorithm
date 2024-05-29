import java.util.Arrays;
class Solution {
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = 423451269;
        int[][] li = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(li[i], INF);
        }
        for(int[] fare: fares) {
            li[fare[0]-1][fare[1]-1] = fare[2];
            li[fare[1]-1][fare[0]-1] = fare[2];
        }
        for(int i = 0; i < n; i++){
            li[i][i] = 0;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (li[i][k] + li[k][j] < li[i][j]) {
                        li[i][j] = li[i][k] + li[k][j];
                    }
                }
            }
        }

        int ans = li[s-1][a-1] + li[s-1][b-1];
        for(int k = 0; k < n; k++) {
            ans = Math.min(li[s-1][k] + li[k][a-1] + li[k][b-1], ans);
        }

        return ans;
    }
}