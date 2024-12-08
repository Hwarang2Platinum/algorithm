import java.util.*;
import java.io.*;

public class B22949_회전미로탐색_조승기 {
    static int K;
    static char[][][] li;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        li = new char[4][4 * K][4 * K];
        Pair s = new Pair(0, 0, 0, 0);

        for (int i = 0; i < 4 * K; i++) {
            String line = br.readLine();
            for (int j = 0; j < 4 * K; j++) {
                li[0][i][j] = line.charAt(j);
                if (line.charAt(j) == 'S') {
                    s = new Pair(i, j, 0, 0);
                }
            }
        }

        for (int l = 1; l < 4; l++) {
            for (int i = 0; i < 4 * K; i++) {
                for (int j = 0; j < 4 * K; j++) {
                    int[] rotated = rotatedOf(i, j);
                    li[l][rotated[0]][rotated[1]] = li[l - 1][i][j];
                }
            }
        }

        boolean[][][] isv = new boolean[4][4 * K][4 * K];

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(s);
        int[] ix = new int[]{0, 0, 1, -1, 0};
        int[] iy = new int[]{1, -1, 0, 0, 0};
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (li[p.rot][p.x][p.y] == 'E') {
                System.out.println(p.w);
                return;
            }

            for (int i = 0; i < 5; i++) {
                int X = p.x + ix[i];
                int Y = p.y + iy[i];
                int a = areaOf(X, Y);
                if (X < 0 || Y < 0 || X >= 4*K || Y >= 4*K) continue;
                int rot = areaOf(p.x, p.y) == a ? p.rot : 0;
                if (li[rot][X][Y] == '#') continue;
                int[] r = rotatedOf(X, Y);
                X = r[0];
                Y = r[1];
                rot = (rot + 1) % 4;
                if (isv[rot][X][Y]) continue;
                isv[rot][X][Y] = true;
                q.add(new Pair(X, Y, p.w + 1, rot));
            }
        }

        System.out.println(-1);
    }
    static class Pair {
        int x, y, w, rot;
        public Pair(int x, int y, int w, int rot) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.rot = rot;
        }
    }

    public static int areaOf(int x, int y) {
        return 524287 * (x / 4) + y / 4;
    }

    public static int[] rotatedOf(int x, int y) {
        int i = x / 4;
        int j = y / 4;
        int a = x % 4;
        int b = y % 4;
        return new int[]{i * 4 + b, j * 4 + 3 - a};
    }
    public static void debug() {
        for (int i = 0; i < 4 * K; i++) {
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4 * K; j++) {
                    System.out.print(li[k][i][j]);
                }
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("======");
    }
}

/*
2
12345678
91234567
89123456
78912345
67891234
56789123
45678912
34567891

2
1--21--2
|ab||ab|
|cd||cd|
3--43--4
1--21--2
|ab||ab|
|cd||cd|
3--43--4


2
@@@@1--2
@@@@|ab|
@@@@|cd|
@@@@3--4
@@@@@@@@
@@@@@@@@
@@@@@@@@
@@@@@@@@


 */

/*
    00 01 02 03
    10 11 12 13
    21 22 23 24
    31 32 33 34

    31 21 10 00
    32 22 11 01
    33 23 12 02
    34 24 13 03
    ===========
    X = 3
    Y = 7

    04 05 06 07
    14 15 16 17
    24 25 26 27
    34 35 36 37

    34 24 14 04
    35 25 15 05
    36 26 16 06
    37 27 17 07
     */