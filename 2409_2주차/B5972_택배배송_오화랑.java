package tempDone;

/**
 * * https://www.acmicpc.net/problem/5972
 * IMP : 지나가는 길에, 소를 만나면 여물을 줘야 함 -> 비용 최소화
 * IMP : 지나간 길의 길이는 고려하지 않음
 * IMP : 시작점 Node 1 , 끝점 Node N
 * ! : 1 <= N <= 50000
 * ! : 1 <= M <= 50000
 * IMP : 결국 최단 거리 혹은 최소 비용을 구하는 문제 -> Dijsktra Algorithm or PQ BFS
 * ! PQ BFS는 메모리 초과 / 시간 초과 문제에 봉착
 * * Solution : Dijsktra Algorithm
 */
import java.io.*;
import java.util.*;

public class B5972_택배배송_오화랑 {
    static class Node {
        int node, cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static class Solution {
        int N, M;
        int[] dist;
        boolean[] visited;
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.cost, r2.cost));

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.N = Integer.parseInt(st.nextToken());
            this.M = Integer.parseInt(st.nextToken());
            this.dist = new int[this.N + 1];
            this.visited = new boolean[this.N + 1];

            for (int i = 0; i < this.N + 1; i++) {
                this.graph.add(new ArrayList<>());
                dist[i] = 50_050_000;
            }

            int from, to, cost;
            for (int i = 0; i < this.M; i++) {
                st = new StringTokenizer(input.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                cost = Integer.parseInt(st.nextToken());
                this.graph.get(from).add(new Node(to, cost));
                this.graph.get(to).add(new Node(from, cost));
            }
            getRoute();
        }

        void getRoute() {
            int sNode = 1;
            int eNode = this.N;
            dist[sNode] = 0;
            Node temp;
            pq.offer(new Node(sNode, 0));
            while (!pq.isEmpty()) {
                temp = pq.poll();
                if (visited[temp.node])
                    continue;
                visited[temp.node] = true;
                if (temp.node == eNode) {
                    System.out.println(temp.cost);
                    return;
                }
                for (Node next : graph.get(temp.node)) {
                    if (dist[next.node] > temp.cost + next.cost) {
                        dist[next.node] = temp.cost + next.cost;
                        pq.offer(new Node(next.node, dist[next.node]));
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