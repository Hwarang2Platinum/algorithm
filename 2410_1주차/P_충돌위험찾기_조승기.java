import java.util.*;

class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    HashSet<Integer> ans = new HashSet<>();
    int[][] points;
    int N = 0, M = 0;

    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        for (int i = 0; i < points.length; i++) {
            N = Math.max(N, points[i][0] + 1);
            M = Math.max(M, points[i][1] + 1);
        }
        for (int i = 0; i < routes.length; i++) {
            int w = 0;
            for (int j = 1; j < routes[i].length; j++) {
                w = bfs(routes[i][j - 1] - 1, routes[i][j] - 1, i, w);
            }
        }
        System.out.println(map);
        return ans.size();
    }

    static int[] ix = new int[]{1, -1, 0, 0};
    static int[] iy = new int[]{0, 0, 1, -1};

    public int bfs(int s, int e, int idx, int W) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(points[s][0], points[s][1], W));
        HashSet<Integer> isv = new HashSet<>();
        int[][] route = new int[N][M];
        isv.add(q.peek().x * 1000 + q.peek().y);

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.x == points[e][0] && p.y == points[e][1]) {
                int x = p.x;
                int y = p.y;
                int w = p.w;

                while (true) {
                    int dir = route[x][y];
                    int key = w * 1000000 + x * 1000 + y;

                    if (map.containsKey(key)) {
                        int otherRobotIdx = map.get(key);
                        if (otherRobotIdx != idx) {
                            ans.add(key);
                        }
                    }
                    map.put(key, idx);

                    if (x == points[s][0] && y == points[s][1]) {
                        break;
                    }
                    x -= ix[dir];
                    y -= iy[dir];
                    w -= 1;
                }
                return p.w;
            }

            for (int i = 0; i < 4; i++) {
                int x = p.x + ix[i];
                int y = p.y + iy[i];
                int v = x * 1000 + y;

                if (x >= N || y >= M || x < 0 || y < 0 || isv.contains(v)) continue;
                isv.add(v);
                route[x][y] = i;
                q.add(new Pair(x, y, p.w + 1));
            }
        }
        return W;
    }

    static class Pair {
        int x, y, w;

        public Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}