import java.io.*;
import java.util.*;
// DDR -> 하나의 중점을 기준으로 위 / 아래 / 왼쪽 / 오른쪽으로 연결되어 있음
// 중점 : 0, 위 : 1, 왼쪽 : 2, 아래 : 3, 오른쪽 : 4
// 처음에는 두 발에 중앙을 모으고 있음.
// 생각할 여지가 많은 문제.. -> 최적화의 요소가 많이 남아 있다고 본다.

// 0 : (0, 0, 0)
// 1 : (1, 0, 2), (0, 1, 2)
// 2 : (2, 0, 5), (1, 2, 4), (2, 1, 4), (2, 0, 5) => 
// 3 : (2, 0, 6), (2, 2, 7) ... 

public class B2342_DanceDanceRevolution_오화랑 {
    static class pair {
        int x, y, z;

        public pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Solution {
        int[][] memo;
        int minResult;
        ArrayDeque<pair> next = new ArrayDeque<>();
        ArrayDeque<pair> queue = new ArrayDeque<>();

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.memo = new int[5][5]; // left & right

            int nM;
            queue.offer(new pair(0, 0, 0));
            while (st.hasMoreTokens()) {
                nM = Integer.parseInt(st.nextToken());
                if (nM == 0)
                    break;
                int qSize = queue.size();
                pair temp;
                while (qSize-- > 0) {
                    temp = queue.poll();
                    if (temp.x == nM)
                        queue.offer(new pair(nM, temp.y, temp.z + getPower(temp.x, nM)));
                    else if (temp.y == nM)
                        queue.offer(new pair(temp.x, nM, temp.z + getPower(temp.y, nM)));
                    else {
                        queue.offer(new pair(nM, temp.y, temp.z + getPower(temp.x, nM)));
                        queue.offer(new pair(temp.x, nM, temp.z + getPower(temp.y, nM)));
                    }
                    memo[temp.x][temp.y] = 0;
                }
                while (!queue.isEmpty()) {
                    temp = queue.poll();
                    if (memo[temp.x][temp.y] == 0)
                        memo[temp.x][temp.y] = temp.z;
                    else {
                        memo[temp.x][temp.y] = Math.min(memo[temp.x][temp.y], temp.z);
                    }
                }
                int minPower = Integer.MAX_VALUE;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (memo[i][j] > 0) {
                            minPower = Math.min(minPower, memo[i][j]);
                            queue.offer(new pair(i, j, memo[i][j]));
                        }
                    }
                }
                minResult = minPower;
            }
            System.out.println(minResult);
        }

        int getPower(int from, int to) {
            if (from == 1) {
                switch (to) {
                    case 1:
                        return 1;
                    case 2:
                        return 3;
                    case 3:
                        return 4;
                    case 4:
                        return 3;
                }
            } else if (from == 2) {
                switch (to) {
                    case 1:
                        return 3;
                    case 2:
                        return 1;
                    case 3:
                        return 3;
                    case 4:
                        return 4;
                }
            } else if (from == 3) {
                switch (to) {
                    case 1:
                        return 4;
                    case 2:
                        return 3;
                    case 3:
                        return 1;
                    case 4:
                        return 3;
                }
            } else if (from == 4) {
                switch (to) {
                    case 1:
                        return 3;
                    case 2:
                        return 4;
                    case 3:
                        return 3;
                    case 4:
                        return 1;
                }
            }
            return 2;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
