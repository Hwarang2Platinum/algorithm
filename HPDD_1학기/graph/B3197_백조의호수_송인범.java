import java.io.*;
import java.util.*;

public class B3197_백조의호수_송인범 {
    static int R;
    static int C;
    static char[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] swans = new int[2][2];
    static Queue<Node> q;
    static Queue<Node> q2;
    static boolean[][] isVisited2;

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
       
        int k = 0;
        q = new ArrayDeque<>();
        for (int idx = 0; idx < R; idx++) {
            String line = br.readLine();
            for (int i = 0; i < C; i++) {
                arr[idx][i] = line.charAt(i);
                if (arr[idx][i] == '.')
                    q.add(new Node(idx, i));
                else if (arr[idx][i] == 'L') {
                    swans[k][0] = idx;
                    swans[k][1] = i;
                    q.add(new Node(idx, i));
                    k++;
                }
            }
        }

        q2 = new ArrayDeque<>();
        q2.add(new Node(swans[0][0], swans[0][1]));
        isVisited2 = new boolean[R][C];
        isVisited2[swans[0][0]][swans[0][1]] = true;

        int days = 0;
        while (BFS2(isVisited2)) {
            // 백조가 만나면 반복 종료
            BFS();
            days++;
        }

        System.out.println(days);
    }

    // 빙판 녹이는 BFS
    static void BFS() {
        int size = q.size();
        for (int idx = 0; idx < size; idx++) {
            Node current = q.poll();
            for (int i = 0; i < 4; i++) {
                int ax = current.x + dx[i];
                int ay = current.y + dy[i];
                if (ax < 0 || ay < 0 || ax >= R || ay >= C) continue;
                if (arr[ax][ay] == 'X') {
                    arr[ax][ay] = '.';
                    q.add(new Node(ax, ay));
                }
            }
        }
    }

    // 백조가 만날 때까지 탐색하는 BFS
    static boolean BFS2(boolean[][] isVisited2) {
        Queue<Node> nQ = new ArrayDeque<>();
        while (!q2.isEmpty()) {
            Node current = q2.poll();	
            for (int i = 0; i < 4; i++) {
                int ax = current.x + dx[i];
                int ay = current.y + dy[i];
                if (ax < 0 || ay < 0 || ax >= R || ay >= C || isVisited2[ax][ay]) continue;
                isVisited2[ax][ay] = true;
                if (ax == swans[1][0] && ay == swans[1][1]) {
                	return false; // 발견
                }
                else if (arr[ax][ay] == 'X') nQ.add(new Node(ax, ay));
                else q2.add(new Node(ax, ay));
            }
        }
        q2 = nQ;
        return true;
    }
}