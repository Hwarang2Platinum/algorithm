import java.io.*;
import java.util.*;

public class B14890_경사로_오화랑 {
    static class Solution {
        int N, L, Cnt;
        int[][] Map;
        int[][] RMap;
        StringBuilder log;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            this.log = new StringBuilder();
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.N = Integer.parseInt(st.nextToken());
            this.L = Integer.parseInt(st.nextToken());
            this.Map = new int[N][N];
            this.RMap = new int[N][N];
            for (int i = 0; i < this.N; i++) {
                st = new StringTokenizer(input.readLine());
                for (int j = 0; j < this.N; j++) {
                    this.Map[i][j] = Integer.parseInt(st.nextToken());
                    this.RMap[j][i] = this.Map[i][j];
                }
            }
            for (int i = 0; i < this.N; i++) {
                log.append("Stage Row " + (i + 1)).append("\n");
                lineCheck(Map[i]);
                log.append("Stage Col " + (i + 1)).append("\n");
                lineCheck(RMap[i]);
            }
            System.out.println(Cnt);
            System.out.print(log);
        }

        void lineCheck(int[] line) {
            boolean isDown = false;
            boolean isPoss = true;
            int past, current, sameCnt;
            past = current = line[0];
            sameCnt = 1;
            for (int i = 1; i < this.N; i++) {
                current = line[i];
                if (isDown) {
                    if (past == current) {
                        sameCnt++;
                        if (sameCnt == this.L) {
                            log.append("DownEnd : " + (i + 1)).append("\n");
                            isDown = false;
                            sameCnt = 0;
                        }
                    } else {
                        log.append("DownFail : " + (i + 1)).append("\n");
                        isPoss = false;
                        break;
                    }
                } else {
                    if (past == current)
                        sameCnt++;
                    else if (past + 1 == current) {
                        log.append("Up : " + (i + 1)).append("\n");
                        if (sameCnt >= this.L)
                            sameCnt = 1;
                        else {
                            log.append("UpFail : " + (i + 1)).append("\n");
                            isPoss = false;
                            break;
                        }
                    } else if (past - 1 == current) {
                        log.append("Down " + (i + 1)).append("\n");
                        if (this.L == 1) {
                            sameCnt = 0;
                        } else {
                            isDown = true;
                            sameCnt = 1;
                        }
                    } else {
                        log.append("Fail : " + (i + 1)).append("\n");
                        isPoss = false;
                        break;
                    }
                }
                past = current;
            }
            if (isPoss && !isDown) {
                this.Cnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
