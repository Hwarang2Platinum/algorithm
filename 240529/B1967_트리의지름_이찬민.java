import java.io.*;
import java.util.*;
public class B1967_트리의지름_이찬민 {
    static int N, one, max_dist;
    static ArrayList<Node>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            tree[start].add(new Node(end, dist));
            tree[end].add(new Node(start, dist));
        }
        max_dist = 0;
        visited = new boolean[N + 1];
        bfs(1); // 루트에서 가장 먼 노드구함 one에 저장

        visited = new boolean[N + 1];
        bfs(one);

        System.out.println(max_dist);

    }

    public static int bfs(int start) {
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(start, 0));
        visited[start] = true;
        while (!dq.isEmpty()) {
            Node cur = dq.poll();
            if (max_dist < cur.dist) {
                max_dist = cur.dist;
                one = cur.pair; // 가장 먼 노드 구함
            }

            for (Node node : tree[cur.pair]) {
                if(visited[node.pair]) continue;
                dq.add(new Node(node.pair, cur.dist + node.dist));
                visited[node.pair] = true;
            }
        }
        return max_dist;
    }

    static class Node {
        int pair, dist;

        public Node(int pair, int dist) {
            this.pair = pair;
            this.dist = dist;
        }
    }
}
