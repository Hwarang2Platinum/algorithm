import java.io.*;
import java.util.*;

// Tree가 입력으로 주어진다.
// Tree 정점의 개수 V 2 ~ 100_000
// 둘째 줄부터, 간선의 정보가 주어짐
// 아무 단말 Node를골라내고, 반드시 포함될 수 밖에 없는 Node를 찾아냈다.
// 해당 단말 Node에서 다시 한번, Dijkstra를 해서 최장 지름을 찾아냄.
// Re Commit

public class B1167_트리의지름_오화랑 {
    static class node {
        int next, cost;

        public node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static class Solution {
        int V, MAX, firstTerminalNode, mustNode;
        ArrayList<ArrayList<node>> graph = new ArrayList<>();
        int[] dist;
        boolean[] visited;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = null;
            V = Integer.parseInt(input.readLine());
            for (int i = 0; i <= V; i++)
                graph.add(new ArrayList<node>());

            int from, to, cost;
            for (int i = 1; i <= V; i++) {
                st = new StringTokenizer(input.readLine());
                from = Integer.parseInt(st.nextToken());
                while (st.hasMoreTokens()) {
                    to = Integer.parseInt(st.nextToken());
                    if (to == -1)
                        break;
                    else {
                        cost = Integer.parseInt(st.nextToken());
                        graph.get(from).add(new node(to, cost));
                    }
                }
            }

            for (int i = 1; i <= V; i++) {
                if (graph.get(i).size() == 1) {
                    firstTerminalNode = i;
                    dijkstra(firstTerminalNode);
                    dijkstra(mustNode);
                    System.out.println(MAX);
                    break;
                }
            }
        }

        void dijkstra(int current) {
            dist = new int[V + 1];
            visited = new boolean[V + 1];
            PriorityQueue<node> PQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
            PQ.offer(new node(current, dist[current]));

            node temp;
            while (!PQ.isEmpty()) {
                temp = PQ.poll();

                if (visited[temp.next])
                    continue;
                visited[temp.next] = true;

                if (temp.cost > MAX) {
                    MAX = temp.cost;
                    mustNode = temp.next;
                }

                for (node each : graph.get(temp.next)) {
                    if (visited[each.next])
                        continue;
                    if (dist[each.next] >= dist[temp.next] + each.cost)
                        continue;
                    dist[each.next] = dist[temp.next] + each.cost;
                    PQ.offer(new node(each.next, dist[each.next]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
