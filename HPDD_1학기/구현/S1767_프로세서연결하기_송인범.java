import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class S1767_프로세서연결하기_송인범 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<Core> coreList;
    static int[][] arr;
    static int MinWire;
    static int MaxNum;
    static int N;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            // 비교 해서 최소 값을 출력
            MinWire = Integer.MAX_VALUE;
            MaxNum = 0;
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            coreList = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
 
                    // 양끝에 있는 코어들은 방문 체크
                    if (arr[i][j] == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue;
                        coreList.add(new Core(i, j));
                    }
                }
            }
 
            // 시도 횟수
            DFS(0, 0, 0);
            System.out.println("#" + tc + " " + MinWire);
        }
    }
 
    public static void DFS(int cnt, int idx, int WireAdd) {
        if (idx == coreList.size()) {
            // 코어에 더 많이 연결 시 update
            if (MaxNum < cnt) {
                MaxNum = cnt;
                MinWire = WireAdd;
            } else if (MaxNum == cnt) {
                MinWire = Math.min(MinWire, WireAdd);
            }
            return;
        }
         
        // 남아있는 거 + 내가 여태까지 연결한 노드의 갯수 < 최댓값보다 작으면 안된다...
        if(cnt+(coreList.size()-idx)< MaxNum)return;
 
        // 4방향으로 계산하는 로직
        Core cur = coreList.get(idx);
        for (int i = 0; i < 4; i++) {
            int temp = 0;
            int curX = cur.x;
            int curY = cur.y;
 
            while (true) {
                curX = curX + dx[i];
                curY = curY + dy[i];
 
                if (curX < 0 || curY < 0 || curX >= N || curY >= N) break;
                if (arr[curX][curY] == 1) {
                    temp = 0;
                    break;
                }
 
                temp++;
            }
 
            int x = cur.x;
            int y = cur.y;
            for (int j = 0; j < temp; j++) {
                x += dx[i];
                y += dy[i];
                arr[x][y] = 1;
            }
 
            if (temp == 0) DFS(cnt, idx + 1, WireAdd);
            else  {
            DFS(cnt + 1, idx + 1, WireAdd + temp);
            // 원본맵을 다시 0으로 되돌림
             
            x = cur.x;
            y = cur.y;
            for (int j = 0; j < temp; j++) {
                x+= dx[i];
                y+= dy[i];
                arr[x][y] = 0;
                }
            }
        }
    }
 
    public static class Core {
        int x;
        int y;
 
        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}