import java.io.*;
import java.util.*;

// Hwarang Think
// 단순하게 DFS로 끝까지 가는 느낌으로 처리해도 좋을 것 같긴 하다.
// 걸리는 점은 20 * 20 Matrix가 최대라는 점 -> 시간 초과가 나지 않을까?

public class B1987_알파벳_오화랑 {
    static class Solution {
        int R, C, maxCnt;
        int[][] Map;
        int[][] Move = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        boolean[] visited;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.R = Integer.parseInt(st.nextToken());
            this.C = Integer.parseInt(st.nextToken());
            this.Map = new int[this.R][this.C];
            this.visited = new boolean[26];

            String line;
            for (int i = 0; i < R; i++) {
                line = input.readLine();
                for (int j = 0; j < C; j++) {
                    this.Map[i][j] = line.charAt(j) - 'A';
                }
            }
            visited[Map[0][0]] = true;
            DFS(0, 0, 1);
            System.out.println(maxCnt);
        }

        void DFS(int cX, int cY, int count) {
            int nX, nY;
            for (int[] eachM : Move) {
                nX = cX + eachM[0];
                nY = cY + eachM[1];
                if (nX < 0 || nY < 0 || nX >= this.R || nY >= this.C)
                    continue;
                if (visited[Map[nX][nY]])
                    continue;
                visited[Map[nX][nY]] = true;
                DFS(nX, nY, count + 1);
                visited[Map[nX][nY]] = false;
            }
            maxCnt = Math.max(maxCnt, count);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
