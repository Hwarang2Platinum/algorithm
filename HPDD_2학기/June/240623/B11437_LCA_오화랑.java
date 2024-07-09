import java.io.*;
import java.util.*;

public class B11437_LCA_오화랑 {
    static class Edge {
        int a, b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static class Solution {
        int N, Q;
        ArrayDeque<Edge> remain = new ArrayDeque<>();
        int[] Parent, Depth;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = null;

            this.N = Integer.parseInt(input.readLine());
            this.Parent = new int[this.N + 1];
            this.Depth = new int[this.N + 1];

            int a, b;
            this.Parent[1] = 1; // Root의 Parent 표시
            for (int i = 1; i < this.N; i++) {
                st = new StringTokenizer(input.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                addTree(a, b);
            }

            Edge temp;
            while (!remain.isEmpty()) {
                temp = remain.poll();
                a = temp.a;
                b = temp.b;
                addTree(a, b);
            }

            this.Q = Integer.parseInt(input.readLine());
            for (int i = 0; i < this.Q; i++) {
                st = new StringTokenizer(input.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                sb.append(checkParents(a, b)).append("\n");
            }
            System.out.print(sb);
        }

        void addTree(int a, int b) {
            if (this.Parent[a] == 0 && this.Parent[b] == 0) {
                // 모두 Parent가 존재하지 않아 판단이 어려운 Case
                this.remain.offer(new Edge(a, b));
            } else if (this.Parent[a] == 0) {
                // B는 이미 Parent가 존재하는 Case
                this.Parent[a] = b;
                this.Depth[a] = this.Depth[b] + 1;
            } else {
                // A가 이미 Parent가 존재하는 Case
                this.Parent[b] = a;
                this.Depth[b] = this.Depth[a] + 1;
            }
        }

        int checkParents(int a, int b) {
            if (this.Depth[a] < this.Depth[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            while (this.Depth[a] > this.Depth[b]) {
                a = this.Parent[a];
            }

            while (a != b) {
                a = this.Parent[a];
                b = this.Parent[b];
            }
            return a;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}