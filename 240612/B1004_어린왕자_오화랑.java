import java.io.*;
import java.util.*;

public class B1004_어린왕자_오화랑 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Solution {
        int tCase, circleCnt;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = null;
            this.tCase = Integer.parseInt(input.readLine());

            int sX, sY, eX, eY;
            Point start, end;
            for (int t = 1; t <= this.tCase; t++) {
                st = new StringTokenizer(input.readLine());
                sX = Integer.parseInt(st.nextToken());
                sY = Integer.parseInt(st.nextToken());
                eX = Integer.parseInt(st.nextToken());
                eY = Integer.parseInt(st.nextToken());
                start = new Point(sX, sY);
                end = new Point(eX, eY);

                this.circleCnt = Integer.parseInt(input.readLine());
                int tX, tY, tR;
                boolean startIn, endIn;
                int result = 0;
                for (int i = 0; i < circleCnt; i++) {
                    st = new StringTokenizer(input.readLine());
                    tX = Integer.parseInt(st.nextToken());
                    tY = Integer.parseInt(st.nextToken());
                    tR = Integer.parseInt(st.nextToken());
                    startIn = inCheck(start, tX, tY, tR);
                    endIn = inCheck(end, tX, tY, tR);
                    if (startIn != endIn) {
                        result++;
                    }
                }
                sb.append(result).append("\n");
            }
            System.out.print(sb);
        }

        boolean inCheck(Point point, int tX, int tY, int tR) {
            return dist(point.x, point.y, tX, tY) <= tR;
        }

        double dist(int x1, int y1, int x2, int y2) {
            return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
