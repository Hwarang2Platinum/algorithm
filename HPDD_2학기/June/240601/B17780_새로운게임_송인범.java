import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B17780_새로운게임_송인범 {
    static ArrayList<Integer>[][] rank;
    static node[] chessList;
    static int cnt;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        cnt = 0;

        // n x n
        int[][] arr = new int[n][n];
        rank = new ArrayList[n][n];
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            for (int idx2 = 0; idx2 < n; idx2++) {
                arr[idx][idx2] = Integer.parseInt(st.nextToken());
                rank[idx][idx2] = new ArrayList<>();
            }
        }

        // 배열 생성
        chessList = new node[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) -1;
            chessList[i] = new node(x, y, dir);
            rank[x][y].add(i);
        }

        while (true) {
            cnt++;
            if (cnt > 1000) {
                cnt = -1;
                break;
            }

            // 리스트 안에 있는 요소들 하나씩 이동.. 근데 제일 밑에만 이동 가능
            for (int i = 0; i < m; i++) {
                int x = chessList[i].row;
                int y = chessList[i].col;
                if (rank[x][y].get(0) != i) continue; // 제일 밑에 것이 아닐 경우!
                int d = chessList[i].dir;

                int ax = x + dx[d];
                int ay = y + dy[d];
                // 조건 탐색 시작
                if (ax < 0 || ax >= n || ay < 0 || ay >= n || arr[ax][ay] == 2) {
                    int newdir = changeDir(d);
                    chessList[i].dir = newdir;
                    ax = x + dx[newdir];
                    ay = y + dy[newdir];
                    if (ax < 0 || ax >= n || ay < 0 || ay >= n || arr[ax][ay] == 2)continue;
                    else if (arr[ax][ay] == 1) {
                        red(x,y,ax,ay);
                    }else{
                        white(x,y,ax,ay);
                    }
                } else if (arr[ax][ay] == 1 ) {
                    red(x ,y ,ax , ay);
                }
                 else {
                    white(x, y, ax, ay);
                }

                if (rank[ax][ay].size() >= 4) {
                    System.out.println(cnt);
                    return;
                }
            }
        }

        System.out.println(cnt);
    }

    static int changeDir(int dir) {
        switch (dir) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
        }
        return dir;
    }

    static void white(int x, int y, int ax, int ay){
        for (int idx = 0; idx < rank[x][y].size(); idx++) {
            int target = rank[x][y].get(idx);
            chessList[target].row = ax;
            chessList[target].col = ay;
            rank[ax][ay].add(target); // 순서대로 입력
        }
        rank[x][y].clear();
    }

    static void red(int x, int y, int ax, int ay){
        for (int idx = rank[x][y].size() - 1; idx >= 0; idx--) {
            int target = rank[x][y].get(idx);
            chessList[target].row = ax;
            chessList[target].col = ay;
            rank[ax][ay].add(target);
        }
        rank[x][y].clear();
    }

    static class node {
        int row;
        int col;
        int dir;

        public node(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }
}