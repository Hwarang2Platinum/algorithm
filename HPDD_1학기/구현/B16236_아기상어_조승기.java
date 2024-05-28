import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair>{
        int x, y, w;
        Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {
            if (w == o.w) {
                if (x == o.x) {
                    return Integer.compare(y, o.y);
                }
                return Integer.compare(x, o.x);
            }
            return Integer.compare(w, o.w);
        }

        @Override
        public String toString() {
            return x + " " + y + " " + w + "!!";
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int li[][] = new int[N][N];
        StringTokenizer st;
        int sharkSize = 2;
        int sx = 0, sy = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                li[i][j] = Integer.parseInt(st.nextToken());
                if (li[i][j] == 9) {
                    sx = i;
                    sy = j;
                    li[i][j] = 0;
                }
            }
        }

        int time = 0;
        int eatCount = 0;
        int[] ix = {0,0,1,-1};
        int[] iy = {1,-1,0,0};

        while (true) {
            PriorityQueue<Pair> q = new PriorityQueue<>();
            q.add(new Pair(sx, sy, 0));

            boolean[][] isv = new boolean[N][N];
            boolean isNotFind = true;
            isv[sx][sy] = true;

            while (isNotFind && !q.isEmpty()) {
                PriorityQueue<Pair> newq = new PriorityQueue<>();
                while(!q.isEmpty()) {
                    Pair p = q.poll();
                    if (li[p.x][p.y] < sharkSize && li[p.x][p.y] != 0) {
                        isNotFind = false;
                        li[p.x][p.y] = 0;
                        eatCount += 1;
                        sx = p.x;
                        sy = p.y;
                        if (eatCount == sharkSize) {
                            sharkSize++;
                            eatCount = 0;
                        }
                        time += p.w;
                        break;
                    }

                    for (int i = 0; i < ix.length; i++) {
                        int x = p.x + ix[i];
                        int y = p.y + iy[i];
                        if (x < 0 || y < 0 || x >= N || y >= N || isv[x][y] || li[x][y] > sharkSize) { continue; }
                        q.add(new Pair(x, y, p.w + 1));
                        isv[x][y] = true;
                    }
                }
            }
//            for(int i = 0; i < N; i++) {
//                for(int j = 0; j < N; j++) {
//                    System.out.print((sx == i && sy == j ? "#" : li[i][j]) + " ");
//                }
//                System.out.println();
//            }
//            System.out.println(time + " " + q + " " +eatCount + " " + sharkSize);
//            System.out.println();
            if (isNotFind) { break; }
        }

        System.out.println(time);
    }
}