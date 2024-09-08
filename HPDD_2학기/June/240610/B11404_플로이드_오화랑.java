import java.io.*;
import java.util.*;

// 2 ~ 100개의 도시가 있음
// 1 ~ 100,000개의 Bus가 존재 -> 각 버스의 Cost가 존재 ( Cost 1 ~ 100,000 )
// 모든 도시의 쌍에 대해서 A -> B로 가는데 최솟값을 구하는 Program
// 시작 도시와 도착 도시가 같은 경우는 없음
// 시작 도시 -> 도착 도시의 경로는 1개 이상 ( 해당 경우를 제대로 처리해줘야 함 )

public class B11404_플로이드_오화랑 {
    static class node {
        int next, cost;

        public node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static class Solution {
        int N, M;
        int INF = 10_000_001; // More than 100*100_000;
        ArrayList<PriorityQueue<node>> preGraph = new ArrayList<>();
        ArrayList<ArrayList<node>> graph = new ArrayList<>();
        int[][] dist;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = null;
            this.N = Integer.parseInt(input.readLine());
            this.M = Integer.parseInt(input.readLine());
            this.dist = new int[this.N + 1][this.N + 1];

            // Init the Graph for Reading Info
            for (int i = 0; i <= this.N; i++) {
                this.graph.add(new ArrayList<>());
                this.preGraph.add(new PriorityQueue<node>(
                        (o1, o2) -> Integer.compare(o1.next, o2.next) != 0 ? Integer.compare(o1.next, o2.next)
                                : Integer.compare(o1.cost, o2.cost)));
                Arrays.fill(this.dist[i], INF);
            }

            // Reading Graph Info -> Read By PQ Graph
            int from, to, cost;
            for (int i = 0; i < this.M; i++) {
                st = new StringTokenizer(input.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                cost = Integer.parseInt(st.nextToken());
                this.preGraph.get(from).add(new node(to, cost));
            }

            // Refine the Graph -> PQ Graph to Normal Graph
            node temp;
            for (int i = 1; i <= this.N; i++) {
                if (preGraph.get(i).isEmpty())
                    continue;
                to = this.preGraph.get(i).peek().next;
                cost = this.preGraph.get(i).peek().cost;
                this.graph.get(i).add(new node(to, cost));

                while (!preGraph.get(i).isEmpty()) {
                    temp = preGraph.get(i).poll();
                    if (temp.next > to) {
                        to = temp.next;
                        cost = temp.cost;
                        this.graph.get(i).add(new node(to, cost));
                    }
                }
            }

            // Init dist for Floyd Warshall Algorithm
            for (int i = 1; i <= this.N; i++) {
                dist[i][i] = 0;
                for (node eachNode : graph.get(i)) {
                    dist[i][eachNode.next] = eachNode.cost;
                }
            }
            Floyd();

            // Making String for Print Out the dist Matrix
            for (int i = 1; i <= this.N; i++) {
                for (int j = 1; j <= this.N; j++) {
                    if (dist[i][j] == INF) {
                        sb.append(0).append(" ");
                        continue;
                    }
                    sb.append(dist[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }

        // Floyd Warshall Algorithm
        void Floyd() {
            for (int via = 1; via <= this.N; via++) {
                for (int from = 1; from <= this.N; from++) {
                    for (int to = 1; to <= this.N; to++) {
                        dist[from][to] = Math.min(dist[from][to], dist[from][via] + dist[via][to]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
