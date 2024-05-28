import java.util.*;

class P_합승택시요금_이찬민 { // 틀림

    static final int MAX = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> arr;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        arr = new ArrayList<>();
        for(int i = 0 ; i <= n ; i ++){
            arr.add(new ArrayList<>());
        }

        for(int[] i : fares){
            arr.get(i[0]).add(new Node(i[1], i[2]));
            arr.get(i[1]).add(new Node(i[0], i[2]));
        }

        int[] startA = new int[n + 1];
        int[] startB = new int[n + 1];
        int[] start = new int[n + 1];

        Arrays.fill(startA, MAX);
        Arrays.fill(startB, MAX);
        Arrays.fill(start, MAX);

        start = dijkstra(s, start); //출발지부터 i까지 최소
        startA = dijkstra(a, startA); // i부터 A 목적지까지 최소
        startB = dijkstra(b, startB); // i부터 B 목적지까지 최소

        for(int i = 1; i <= n ; i ++) {
            answer = Math.min(answer, startA[i] + startB[i] + start[i]);
        }

        return answer;
    }

    static int[] dijkstra (int start, int[] costs) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int curIndex = now.index;
            int curWeight = now.weight;

            if(curWeight > costs[curIndex]) continue;

            ArrayList<Node> nextNodes = arr.get(curIndex); //노드 여러개 전달됨
            for (Node nextNode : nextNodes) {
                int cost = costs[curIndex] + nextNode.weight; //다음 코스트

                if (cost < costs[nextNode.index]) {
                    costs[nextNode.index] = cost;
                    pq.add(new Node(nextNode.index, cost));
                }
            }
        }
        return costs;
    }

    static class Node implements Comparable<Node>{
        int index;
        int weight;
        Node(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
}