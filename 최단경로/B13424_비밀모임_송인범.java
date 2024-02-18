package feburary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라를 활용한 문제
// 한 노드에서 다른 노드까지의 최적경로를 찾는 것이 아닌 두 노드의 출발점을 고려해 적합한 목적 노드를 찾아야 되는 문제
// 모든 노드 갯수 만큼 반복문을 돌려 각각의 노드당 최단 경로를 더해 찾으려 했으나 시간 초과
public class B13424_비밀모임_송인범 {
    static List<List<Node>> graph = new ArrayList<>();
    static int N, M;
    
    static class Node implements Comparable<Node> {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 그래프 초기화
            graph.clear();
            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }

            int K = Integer.parseInt(br.readLine());
            int[] participants = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                participants[i] = Integer.parseInt(st.nextToken());
            }

            int where = dijkstra(participants);
            System.out.println(where);
        }
    }
    // 참고로 다익스트라는 구현할 줄 몰라서 인터넷 참조;;(반성하겠슴)
    
    static int dijkstra(int[] participants) {
        int[] result = new int[N + 1];
        Arrays.fill(result, 1, N + 1, 0);  
        
        for (int i = 0; i < participants.length; i++) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int[] distance = new int[N + 1];
            Arrays.fill(distance, 1, N + 1, 999999999);
            distance[participants[i]] = 0;
            pq.offer(new Node(participants[i], 0));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int currentIndex = current.index;
                int currentWeight = current.weight;

                for (Node link : graph.get(currentIndex)) {
                    int newDistance = currentWeight + link.weight;
                    if (newDistance < distance[link.index]) {
                        distance[link.index] = newDistance;
                        pq.offer(new Node(link.index, newDistance));
                    }
                }
            }

            // 한 명의 참가자에 대한 최단 거리를 누적
            for (int j = 1; j <= N; j++) {
                result[j] += distance[j];
            }
        }

        int where = 1;
        int minNum = result[1]; 
        for (int i = 2; i <= N; i++) {
            if (result[i] < minNum) {
                minNum = result[i];
                where = i;
            }
        }

        return where;
    }
}
