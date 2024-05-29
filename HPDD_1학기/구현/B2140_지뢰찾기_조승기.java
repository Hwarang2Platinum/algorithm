import java.io.*;
import java.util.*;

public class Main {
    static char[][] li;
    static int[] ix = {0,0,1,-1,1,1,-1,-1};
    static int[] iy = {1,-1,0,0,1,-1,1,-1};
    static int count = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        li = new char[N][N];
        count = (N-2) * (N-2);
        for(int i = 0; i < N; i++) {
            li[i] = br.readLine().toCharArray();
        }

        for(int i = 1; i < N-1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (li[i][j] == '#') {
                    find(i, j);
                }
            }
        }

        int ans = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                ans += (li[i][j] == 'X' || li[i][j] == '#') ? 1 : 0;
            }
        }
        System.out.println(N <= 2 ? 0 :count);
    }
    static void find(int x, int y) {
        boolean isBoom = true;
        for(int i = 0; i < ix.length; i++) {
            int X = x + ix[i];
            int Y = y + iy[i];

            if (li[X][Y] == '0') {
                isBoom = false;
            }
        }
        if (!isBoom) {
            count--;
            return;
        }
        for(int i = 0; i < ix.length; i++) {
            int X = x + ix[i];
            int Y = y + iy[i];

            if (li[X][Y] >= '1' && li[X][Y] <= '9') {
                li[X][Y]--;
            }
        }
    }
}
/*
6
111000
2####1
3####1
2####1
1####1
122100
 */