import javax.swing.*;
import java.util.*;
import java.io.*;

public class B21610_마법사상어비바라기_조승기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] li = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                li[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ArrayList<Pair> winds = new ArrayList<>();
        winds.add(new Pair(N - 1, 1));
        winds.add(new Pair(N - 1, 0));
        winds.add(new Pair(N - 2, 1));
        winds.add(new Pair(N - 2, 0));
        int[] wx = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] wy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1};
        for (int t = 0; t < M; t++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            ArrayList<Pair> newWinds = new ArrayList<>();
            for(Pair wind: winds) {
                int x = wind.x;
                int y = wind.y;

                for(int i = 0; i < s; i++){
                    x = x + wx[d];
                    y = y + wy[d];
                    x = x < 0 ? (N-1) : (x);
                    y = y < 0 ? (N-1) : (y);
                    x %= N;
                    y %= N;
                }
                newWinds.add(new Pair(x, y));
            }

            for(Pair wind: newWinds) {
                li[wind.x][wind.y]++;
            }
            HashMap<Pair, Integer> dig = new HashMap();

            for(Pair wind: newWinds) {
                dig.put(wind, 0);
                for(int i = 0; i < dx.length; i++){
                    int X = wind.x + dx[i];
                    int Y = wind.y + dy[i];
                    if (X < 0 || Y < 0 || X >= N || Y >= N || li[X][Y] == 0) {continue;}
                    Pair p = new Pair(X, Y);
                    dig.put(wind, dig.get(wind) + 1);

                }
            }
            boolean[][] isv = new boolean[N][N];
            for (Pair p: dig.keySet()) {
                li[p.x][p.y] += dig.get(p);
                isv[p.x][p.y] = true;
            }
            winds.clear();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Pair p = new Pair(i, j);
                    if (li[i][j] >= 2 && !isv[i][j]){
                        winds.add(p);
                        li[i][j] -= 2;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += li[i][j];
            }
        }
        System.out.println(ans);
    }
    static class Pair {
        int x; int y;
        Pair(int a, int b) { x = a; y = b;}

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}
