import java.io.*;
import java.util.*;

public class B13418_힉교탐방하기_오화랑 {
    static class Edge {
        int from, to, isDown;

        public Edge(int from, int to, int isDown) {
            this.from = from;
            this.to = to;
            this.isDown = isDown;
        }

        public String toString() {
            return "[ from" + this.from + " to " + this.to + " isDown " + this.isDown + " ]";
        }
    }

    static class Solution {
        int N, M, minF, maxF, totalDiff;
        int[] parents;
        PriorityQueue<Edge> edgeList1;
        PriorityQueue<Edge> edgeList2;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.N = Integer.parseInt(st.nextToken());
            this.M = Integer.parseInt(st.nextToken());
            this.parents = new int[this.N + 1];
            this.edgeList1 = new PriorityQueue<>(this.M + 1, (o1, o2) -> Integer.compare(o2.isDown, o1.isDown));
            this.edgeList2 = new PriorityQueue<>(this.M + 1, (o1, o2) -> Integer.compare(o1.isDown, o2.isDown));

            int f, t, d;
            for (int i = 0; i <= this.M; i++) {
                st = new StringTokenizer(input.readLine());
                f = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                this.edgeList1.offer(new Edge(f, t, d));
                this.edgeList2.offer(new Edge(f, t, d));
            }

            this.minF = Kruskal(edgeList1);
            this.maxF = Kruskal(edgeList2);
            this.totalDiff = this.maxF * this.maxF - this.minF * this.minF;
            System.out.println(this.totalDiff);
        }

        int Kruskal(PriorityQueue input) {
            int upCnt, lineCnt;
            Edge temp;
            PriorityQueue<Edge> edgeList = input;

            upCnt = lineCnt = 0;
            makeSet();
            while (!edgeList.isEmpty()) {
                temp = edgeList.poll();
                if (!union(temp.from, temp.to))
                    continue;
                if (temp.isDown == 0)
                    upCnt++;
                if (++lineCnt == this.N)
                    break;
            }
            return upCnt;
        }

        void makeSet() {
            for (int i = 0; i <= this.N; i++)
                this.parents[i] = i;
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB)
                return false;
            else
                parents[rootB] = rootA;
            return true;
        }

        int find(int a) {
            if (a == parents[a])
                return a;
            else
                return parents[a] = find(parents[a]);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
