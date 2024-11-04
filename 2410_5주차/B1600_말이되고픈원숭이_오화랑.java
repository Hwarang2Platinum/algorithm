
/**
 * IMP : https://www.acmicpc.net/problem/1600
 * * 1. 원숭이는 능력이 부족해서, 총 K 번만 "말"과 같이 움직일 수 있음
 * * 2. 그 외에는 인접한 칸으로만 움직일 수 있음 ( 대각선 X )
 * * 3. 최소한으로 이동해서, 시작에서 도착까지 이동할 수 있는 방법을 알아내라
 * * K : "말"처럼 이동할 수 있는 횟수 ( 0 <= K <= 30 )
 * * W : 가로 길이, H : 세로 길이 ( 1 <= W, H <= 200)
 * * 장애물은 이동할 수 없음 
 */

import java.io.*;
import java.util.*;

public class B1600_말이되고픈원숭이_오화랑 {
    static class Pair {
        int left, x, y, count;

        public Pair(int left, int x, int y, int count) {
            this.left = left;
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static class visit {
        int horseLeft;
        boolean visited;

        public visit(int horseLeft, boolean visited) {
            this.horseLeft = horseLeft;
            this.visited = visited;
        }

        public String toString() {
            return "{ left : " + this.horseLeft + ", visited : " + this.visited + "}";
        }
    }

    static class Solution {
        int K, W, H;
        int[][] Map;
        int[][] monkeyMove = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int[][] horseMove = { { -2, 1 }, { -1, 2 }, { 2, 1 }, { 1, 2 }, { 2, -1 }, { 1, -2 }, { -2, -1 }, { -1, -2 } };
        Queue<Pair> queue = new ArrayDeque<>();

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            K = Integer.parseInt(input.readLine());
            StringTokenizer st = new StringTokenizer(input.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            // Init
            Map = new int[H][W];
            visit[][] horseVisited = new visit[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(input.readLine());
                for (int j = 0; j < W; j++) {
                    Map[i][j] = Integer.parseInt(st.nextToken());
                    horseVisited[i][j] = new visit(0, false);
                }
            }

            // Solve
            int result = -1;
            queue.offer(new Pair(K, 0, 0, 0));
            horseVisited[0][0].horseLeft = K;
            horseVisited[0][0].visited = true;

            Pair current;
            int nX, nY;
            while (!queue.isEmpty()) {
                current = queue.poll();
                if (current.x == H - 1 && current.y == W - 1) {
                    result = current.count;
                    break;
                }
                if (current.left > 0) {
                    for (int[] eachHorseMove : horseMove) {
                        nX = current.x + eachHorseMove[0];
                        nY = current.y + eachHorseMove[1];
                        if (nX < 0 || nY < 0 || nX >= H || nY >= W)
                            continue;
                        if (Map[nX][nY] == 1)
                            continue;
                        if (horseVisited[nX][nY].visited) {
                            if (horseVisited[nX][nY].horseLeft >= (current.left - 1))
                                continue;
                            horseVisited[nX][nY].horseLeft = current.left - 1;
                            horseVisited[nX][nY].visited = true;
                            queue.offer(new Pair(current.left - 1, nX, nY, current.count + 1));
                        } else {
                            horseVisited[nX][nY].horseLeft = current.left - 1;
                            horseVisited[nX][nY].visited = true;
                            queue.offer(new Pair(current.left - 1, nX, nY, current.count + 1));
                        }
                    }
                }
                for (int[] eachMonkeyMove : monkeyMove) {
                    nX = current.x + eachMonkeyMove[0];
                    nY = current.y + eachMonkeyMove[1];
                    if (nX < 0 || nY < 0 || nX >= H || nY >= W)
                        continue;
                    if (Map[nX][nY] == 1)
                        continue;
                    if (horseVisited[nX][nY].visited) {
                        if (horseVisited[nX][nY].horseLeft >= (current.left))
                            continue;
                        horseVisited[nX][nY].horseLeft = current.left;
                        horseVisited[nX][nY].visited = true;
                        queue.offer(new Pair(current.left, nX, nY, current.count + 1));
                    } else {
                        horseVisited[nX][nY].horseLeft = current.left;
                        horseVisited[nX][nY].visited = true;
                        queue.offer(new Pair(current.left, nX, nY, current.count + 1));
                    }
                }
            }
            System.out.println(result);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
