import java.util.*;
import java.io.*;

public class Main {

    /**
     * . : 빈 필드
     * # : 울타리
     * o : 양
     * v : 늑대
     * 한 칸에서 수평, 수직만으로 울타리를 지나지 않고 다른 칸으로 이동 가능하면, 같은 영역이라고 함 -> 이어져있는건 한 영역
     * 우리 속 양은 늑대에게 시비걸 수 있음
     * - 영역 안의 양 > 늑대 : 이김 (늑대를 우리에서 쫓아냄)
     * - 영역 안의 양 <= 늑대 : 늑대가 양 먹방
     * return : 아침이 도달했을 때 살아남은 양과 늑대 수 출력
     */
    static int R, C;
    static char[][] arr;

    final static char BLANK = '.';
    final static char FENCE = '#';
    final static char SHEEP = 'o';
    final static char WOLF = 'v';
    final static char VISITED = 'H';

    static int sheepCnt = 0;
    static int wolfCnt = 0;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 입력 처리
        arr = new char[R][C];
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        // 울타리가 안 나올 때 탐색 시작
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(arr[i][j] == FENCE) continue;
                if(arr[i][j] == VISITED) continue;
                bfs(i, j);
            }
        }

        System.out.println(sheepCnt + " " + wolfCnt);
    }

    static void print() {
        System.out.println();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bfs(int x, int y) {
        int tmpSheepCnt = 0;
        int tmpWolfCnt = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        if(arr[x][y] == SHEEP) tmpSheepCnt++;
        else if(arr[x][y] == WOLF) tmpWolfCnt++;
        arr[x][y] = VISITED;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int cx = cur[0] + dx[i];
                int cy = cur[1] + dy[i];
                if(cx < 0 || cx >= R || cy < 0 || cy >= C) continue; // 영역 체크
                if(arr[cx][cy] == FENCE) continue;
                if(arr[cx][cy] == VISITED) continue;
                if(arr[cx][cy] == SHEEP) {
                    tmpSheepCnt++;
                }
                if(arr[cx][cy] == WOLF) {
                    tmpWolfCnt++;
                }
                arr[cx][cy] = VISITED;
                queue.add(new int[]{cx, cy});
            }
        }
        if(tmpSheepCnt > tmpWolfCnt) {
            sheepCnt += tmpSheepCnt;
        } else {
            wolfCnt += tmpWolfCnt;
        }
    }

}
