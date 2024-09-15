package tempDone;

import java.io.*;
import java.util.*;

public class B14940_쉬운최단거리_오화랑 {
    static class pair {
        int x, y, count;

        public pair(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static class Solution {
        int N, M;
        int[][] move = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };
        int[][] map, distMap;
        boolean[][] visited;
        Queue<pair> distQueue = new ArrayDeque<>();

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.N = Integer.parseInt(st.nextToken());
            this.M = Integer.parseInt(st.nextToken());
            this.map = new int[this.N][this.M];
            this.distMap = new int[this.N][this.M];
            this.visited = new boolean[this.N][this.M];

            int sX, sY;
            sX = sY = 0;
            for (int i = 0; i < this.N; i++) {
                st = new StringTokenizer(input.readLine());
                for (int j = 0; j < this.M; j++) {
                    this.map[i][j] = Integer.parseInt(st.nextToken());
                    if (this.map[i][j] == 2) {
                        sX = i;
                        sY = j;
                    }
                    if (this.map[i][j] == 1) {
                        this.distMap[i][j] = -1;
                    }
                }
            }
            getDist(sX, sY);
            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    sb.append(distMap[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());
        }

        void getDist(int sX, int sY) {
            distQueue.offer(new pair(sX, sY, 0));
            visited[sX][sY] = true;
            pair temp;
            int nX, nY;
            while (!distQueue.isEmpty()) {
                temp = distQueue.poll();
                distMap[temp.x][temp.y] = temp.count;
                for (int[] eachMove : move) {
                    nX = temp.x + eachMove[0];
                    nY = temp.y + eachMove[1];
                    if (nX < 0 || nY < 0 || nX >= this.N || nY >= this.M)
                        continue;
                    if (visited[nX][nY] || this.map[nX][nY] == 0)
                        continue;
                    visited[nX][nY] = true;
                    distQueue.offer(new pair(nX, nY, temp.count + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}