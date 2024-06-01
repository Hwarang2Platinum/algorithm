import java.util.*;
import java.io.*;

public class B17780_새로운게임_조승기 {
    static int[] ix = new int[]{0, 0, -1, 1};
    static int[] iy = new int[]{1, -1, 0, 0};
    static int[] counterDir = new int[]{1, 0, 3, 2};
    static int N, K;
    static int[][] board;
    static Piece[] pieces;
    static int RED = 1;
    static int BLUE = 2;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        pieces = new Piece[K];
        ArrayDeque<Integer>[][] stack = new ArrayDeque[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                stack[i][j] = new ArrayDeque<>();
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            pieces[i] = new Piece(x, y, d);
            stack[x][y].add(i);
        }



        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j < K; j++) {
                Piece piece = pieces[j];
                int x = piece.x;
                int y = piece.y;

                if (stack[x][y].getFirst() != j) continue; // Is Bottom

                int toX = x + ix[piece.dir];
                int toY = y + iy[piece.dir];

                if (toX < 0 || toY < 0 || toX >= N || toY >= N || board[toX][toY] == BLUE) {
                    piece.dir = counterDir[piece.dir];
                    toX = x + ix[piece.dir];
                    toY = y + iy[piece.dir];

                    if (toX < 0 || toY < 0 || toX >= N || toY >= N || board[toX][toY] == BLUE) continue;
                }

                while (!stack[x][y].isEmpty()) {
                    int idx = board[toX][toY] == RED ? (stack[x][y].removeLast()) : (stack[x][y].removeFirst());
                    Piece p = pieces[idx];
                    p.x = toX;
                    p.y = toY;
                    stack[toX][toY].addLast(idx);
                }

                if (stack[toX][toY].size() >= 4) {
                    System.out.println((i+1));
                    return;
                }
            }

        }
        System.out.println(-1);
    }

    static class Piece {
        int x, y, dir;
        public Piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
