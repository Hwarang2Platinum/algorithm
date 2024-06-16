import java.io.*;
import java.util.*;

// Hwrang Think
// N : 세로선 개수 ( 2 ~ 10 )
// M : 가로선 개수 ( 1 ~ 30 ) 
// H : 가로선을 놓을 수 있는 위치 0 ~ (N - 1) * M
// => 사다리 조작이란? -> i번째에서 떨이지면 i번째로 떨어지게!!
// N : 세로선 개수 ( 출발 위치는 최대 10개 -> 완전탐색 가능할 듯)
// 그어야 하는 가로선 개수가 3개를 넘어가면 불가능!
// 270_C_3 => 3_244_140 (완탐이 그래도 가능한 부분)
// 3_244_140 * 30 => 총 계산 시간 97,324,200개

public class B15684_사다리조작_오화랑_코드최적화_시간최적화요구 {
    static class possLine {
        int x, y;

        public possLine(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Solution {
        int N, H, M, madeOf;
        int[][] Map;
        ArrayList<possLine> pLineList = new ArrayList<>();
        possLine[] selected;
        boolean isMade;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.N = Integer.parseInt(st.nextToken());
            this.M = Integer.parseInt(st.nextToken());
            this.H = Integer.parseInt(st.nextToken());
            this.Map = new int[this.H + 1][this.N + 1];

            int from, to;
            for (int i = 0; i < this.M; i++) {
                st = new StringTokenizer(input.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                Map[from][to] = to + 1;
                Map[from][to + 1] = to;
            }

            for (int i = 1; i <= this.H; i++) {
                for (int j = 1; j < this.N; j++) {
                    if (Map[i][j] == 0 && Map[i][j + 1] == 0) {
                        pLineList.add(new possLine(i, j));
                    }
                }
            }

            if (checkMade()) {
                System.out.println(0);
                return;
            }

            madeOf = -1;
            for (int c = 1; c <= 3; c++) {
                this.selected = new possLine[c];
                chooseLine(0, 0, c);
                if (isMade) {
                    madeOf = c;
                    break;
                }
            }
            System.out.println(madeOf);
        }

        void chooseLine(int start, int cnt, int max) {
            if (cnt == max) {
                for (possLine each : selected) {
                    this.Map[each.x][each.y] = each.y + 1;
                    this.Map[each.x][each.y + 1] = each.y;
                }
                isMade = checkMade();
                for (possLine each : selected) {
                    this.Map[each.x][each.y] = 0;
                    this.Map[each.x][each.y + 1] = 0;
                }
                return;
            }

            for (int i = start; i < pLineList.size(); i++) {
                selected[cnt] = pLineList.get(i);
                chooseLine(i + 1, cnt + 1, max);
                if (isMade)
                    return;
            }
        }

        boolean checkMade() {
            int cX, cY;
            for (int i = 1; i <= this.N; i++) {
                cX = 1;
                cY = i;
                while (cX < this.H) {
                    if (Map[cX][cY] != 0) {
                        cY = Map[cX][cY];
                    }
                    cX++;
                }
                if (Map[cX][cY] != i) {
                    if (!(Map[cX][cY] == 0 && cY == i))
                        return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}