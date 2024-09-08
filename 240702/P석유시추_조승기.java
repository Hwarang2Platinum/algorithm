import java.util.*;

class Solution {
    
    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } 
    
    static int[] ix = new int[] { 0, 0, 1, -1};
    static int[] iy = new int[] { 1, -1, 0, 0};
    static int N, M;
    
    public static int bfs(int[][] land, int[][] isv, int x, int y) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(x, y));
        int ret = 1;
        while(!q.isEmpty()) {
            Pair p = q.poll();

            for(int i = 0; i < 4; i++) {
                int X = p.x + ix[i];
                int Y = p.y + iy[i];
                if (X < 0 || Y < 0 || X >= N || Y >= M) continue;
                if (land[X][Y] == 0 || isv[X][Y] != 0) continue;
                isv[X][Y] = isv[p.x][p.y];
                q.add(new Pair(X, Y));
                ret++;
            }
        }
        return ret;
    }
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        
        int[][] isv = new int[N][M];
        ArrayList<Integer> li = new ArrayList<Integer>();
        li.add(0);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (isv[i][j] != 0 || land[i][j] == 0) continue;
                isv[i][j] = li.size();
                li.add(bfs(land, isv, i, j));
                // for(int a = 0; a < N; a++) {
                    // for(int b = 0; b < M; b++) {
                        // System.out.print(isv[a][b]);
                    // }       
                    // System.out.println();
                // }
                // System.out.println();
            }
        }
        // System.out.println(li);
        
        int ans = 0;
        for(int j = 0; j < M; j++) {
            Set<Integer> set = new HashSet<>();
            for(int i = 0; i < N; i++) {
                set.add(isv[i][j]);
            }
            int local = 0;
            for(int a: set) {
                local += li.get(a);
            }
            ans = Math.max(local, ans);
        }
        return ans;
    }
}