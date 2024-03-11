import java.util.Arrays;

public class P_전력망을둘로나누기_조승기 {
    class Solution {
        static int[] uf;

        static int find(int n) {
            if (uf[n] < 0)  return n;
            return uf[n] = find(uf[n]);
        }
        static void merge(int a, int b) {
            int A = find(a);
            int B = find(b);
            if (A != B) {
                uf[A] += uf[B];
                uf[B] = A;
            }
        }
        public static int solution(int n, int[][] wires) {
            uf = new int[n];
            int ans = 101;

            for(int i = 0; i < wires.length; i++) {
                Arrays.fill(uf, -1);
                for(int j = 0; j < wires.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    merge(wires[j][0]-1, wires[j][1]-1);
                }
                System.out.println(Arrays.toString(uf));
                var arr = Arrays.stream(uf).filter(e->e<0).toArray();
                ans = Math.min(ans, Math.abs(arr[0] - arr[1]));
            }
            return ans;
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(Solution.solution(7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}));
    }
}
