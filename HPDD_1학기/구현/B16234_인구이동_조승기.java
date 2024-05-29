import java.io.*;
import java.util.*;

public class Main {
    static int[][] isv;
    static int[][] li;

    static int count, sum;
    static int[] ix = {0, 0, 1, -1};
    static int[] iy = {1, -1, 0, 0};
    static int N, L, R;
    static void dfs(int x, int y, int n) {
        sum += li[x][y];
        count++;
        isv[x][y] = n;
        for(int i = 0; i < ix.length; i++) {
            int X = x + ix[i];
            int Y = y + iy[i];
            if (X < 0 || Y < 0 || X >= N || Y >= N || isv[X][Y] != 0) { continue; }
            if (Math.abs(li[x][y] - li[X][Y]) > R || Math.abs(li[x][y] - li[X][Y]) < L) { continue; }
            isv[X][Y] = n;
            dfs(X, Y, n);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        li = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isMoved = true;
        int ans = 0;

        while (isMoved) {
            isMoved = false;
            isv = new int[N][N];
            int n = 1;
            ArrayList<Integer> sums = new ArrayList<>();
            ArrayList<Integer> counts = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sum = 0; count = 0;
                    if (isv[i][j] != 0) { continue; }

                    dfs(i, j, n);
                    if (count != 1) {
                        sums.add(sum);
                        counts.add(count);
                        n += 1;
                    } else {
                        isv[i][j] = -1;
                    }
                }
            }
            ArrayList<Integer> nextValues = new ArrayList<>();

            for(int i = 0; i < sums.size(); i++) {
                nextValues.add(sums.get(i) / counts.get(i));
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if (isv[i][j] > 0) {
                        li[i][j] = nextValues.get(isv[i][j]-1);
                        if (!isMoved) { ans++; }
                        isMoved = true;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
/*
1
4
40 30 30 50
 */