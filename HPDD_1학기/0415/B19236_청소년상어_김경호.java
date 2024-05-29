import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 물고기 - 번호(1~16), 방향(8방)
 * 1. 상어 진입(0,0) 물고기 먹방, 방향은 (0,0)의 물고기 방향과 같음
 * 2. 물고기 이동
 *      - 작은 물고기 부터 이동
 *      - 한칸 이동
 *      - 상어가 있는 칸은 이동 할 수 없음
 *          - 이동할 수 있는 방향을 찾을 때까지 45도 반시계 회전
 *          - 이동할 수 있는 방향 없으면 이동 x
 *      - 이동 칸에 물고기가 있으면 위치 교환
 * 3. 상어 이동
 *      - 현재 방향으로 이동 가능 (여러칸 한번에 이동 가능)
 *      - 물고기가 있으면 물고기를 먹고, 물고기의 방향을 자신의 방향으로 가짐
 *      - 물고기 없는 칸으로 이동할 수 없음
 *      - 갈 곳 없으면 2번 물고기 이동으로 back
 *      - 이동할 칸이 없으면 집으로 감
 *
 */

public class Main {
    static int[] dr = {-1, -1, 0 ,1 ,1 ,1 ,0, -1};
    static int[] dc = {0, -1, -1 ,-1 ,0 ,1 ,1, 1};
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        Fish[][] map = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                map[i][j] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1, i, j);
            }
        }
        ans = 0;
        int firstDirection = map[0][0].dir;
        dfs(0,0,new int[]{0,0}, firstDirection, map);

        System.out.println(ans);
    }

    private static void dfs(int depth,int sum, int[] shark, int direction, Fish[][] map) {
        // 먹방
        sum += map[shark[0]][shark[1]].num;

        map[shark[0]][shark[1]] = null;

        ans = Math.max(ans,sum);

        // 물고기 이동
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(map[i][j]==null) continue;
                pq.add(map[i][j]);
            }
        }
        while (!pq.isEmpty()) {
            Fish f = pq.poll();
            for (int i = 0; i < 8; i++) {
                int nextR = f.r + dr[(f.dir+i)%8];
                int nextC = f.c + dc[(f.dir+i)%8];
                // 상어가 있거나 나가는 경우
                if(nextR<0 || nextR>=4 || nextC<0 || nextC>=4
                        || (nextR == shark[0] && nextC == shark[1])) continue;
                // 물고기가 있는 경우 (위치 교환)
                if(map[nextR][nextC] != null){
                    int tmpr = map[nextR][nextC].r; int tmpc = map[nextR][nextC].c;
                    int fr = f.r; int fc = f.c;
                    map[nextR][nextC].r = fr;
                    map[nextR][nextC].c = fc;
                    f.dir = (f.dir+i)%8;
                    f.r = tmpr;
                    f.c = tmpc;

                    // swap
                    Fish tmp = map[nextR][nextC];
                    map[nextR][nextC] = map[fr][fc];
                    map[fr][fc] = tmp;
                }
                // 빈칸인 경우 그냥 이동
                else{
                    map[nextR][nextC] = f;
                    map[f.r][f.c] = null;
                    f.r = nextR;
                    f.c = nextC;
                    f.dir = (f.dir+i)%8;
                }
                break;
            }
        }

//

        // 상어 이동 dfs
        int rDirection = dr[direction];
        int cDirection = dc[direction];

        for (int i = 1; i < 4; i++) {
            int nextR = shark[0] + i*rDirection;
            int nextC = shark[1] + i*cDirection;
            if(nextR<0 || nextR>=4 || nextC<0 || nextC>=4) break;
            if(map[nextR][nextC]==null) continue;
            Fish[][] newMap = new Fish[4][4];
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {
                    Fish tmpf = map[k][j];
                    if(tmpf==null) continue;
                    newMap[k][j] = new Fish(tmpf.num,tmpf.dir,tmpf.r,tmpf.c);
                }
            }
            dfs(depth+1,sum, new int[]{nextR,nextC}, newMap[nextR][nextC].dir, newMap);
        }

    }

    public static class Fish implements Comparable<Fish> {
        @Override
        public int compareTo(Fish o) {
            return this.num - o.num;
        }

        int num, dir, r, c;

        public Fish(int num, int dir, int r, int c) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "["+String.valueOf(num)+"," + dir+"]";
        }
    }

}