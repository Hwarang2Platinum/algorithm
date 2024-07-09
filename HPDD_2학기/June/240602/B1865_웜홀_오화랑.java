import java.io.*;
import java.util.*;

// N(1 ≤ N ≤ 500), 도로의 개수 M(1 ≤ M ≤ 2500), 웜홀의 개수 W(1 ≤ W ≤ 200)
// T는 10,000보다 작거나 같은 자연수 또는 0이다
// A Node에서 B Node로의 경로에 대한 이야기가 아님 => A Node에서 A Node로 시간이 음수로 돌아오는 경로가 존재하는가? -> 음의 Cycle이 존재해야 한다
// 출발 위치가 정해져 있지 않다. 500번을 다 돌아야 하나?
// 계산의 중간값은.. 끝까지 갔다가 다시 되돌아오는 것을 생각하면 1000번 * 10000 => 1천만 -> Int 내에서 처리 가능
// 그럼 dist의 최대값을 어떻게 설정할까? => 1000 * 10000 => 10_000_000 + 1

// Bellman ford Algorithm -> 꽤나 논란이 많은 문제인 것 같다.
// 나도 조금 생각을 정석적으로 하다보니까, 헷갈린 것도 많았고 조금 돌아간 것 도 있다.
// 대표적으로 음수 Cycle을 찾기 위해서, visited를 써가면서 갱신되지않은 새로운 점을 시작으로 해야 하는 부분이 들어가 있다.
// 고수의 글을 찾아보니, 현재 Code의 방식대로 푸는 게 아마 정석에 가까운 것 같다.
// 꼭 한번씩 이 글은 읽는 것이 좋을 것 같다. https://www.acmicpc.net/board/view/72995
// 근데 솔직히 벨만포드가 그렇게 자주 나오는 알고리즘은 아니지만, 해당 글은 Graph에서 방문되지 않음과 Infinity에 대한 설명을 잘 하고 있어서
// 다른 Graph 알고리즘을 풀 때도, 도움이 될 것 같다.
// + SPFA라는 Algorithm으로 최적화도 가능할 것 같다.

public class B1865_웜홀_오화랑 {
    static class edge {
        int from, to, cost;

        public edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static ArrayList<edge> edgeList;
    static int[] dist;
    static int MAX = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(input.readLine());
        int N, M, W, from, to, cost;
        int count, start;
        boolean nCycle;
        boolean allVisit;
        boolean[] visited;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edgeList = new ArrayList<>(N + 1);
            dist = new int[N + 1];

            // Reading edgeList Info
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(input.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                cost = Integer.parseInt(st.nextToken());
                edgeList.add(new edge(from, to, cost));
                edgeList.add(new edge(to, from, cost));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(input.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                cost = -1 * Integer.parseInt(st.nextToken());
                edgeList.add(new edge(from, to, cost));
            }

            // check Negative Cylce
            start = 1;
            nCycle = false;
            allVisit = false;
            visited = new boolean[N + 1];
            while (true) {
                if (nCycle)
                    break;
                if (allVisit)
                    break;

                Arrays.fill(dist, MAX);
                dist[start] = 0;
                visited[start] = true;

                for (int i = 1; i <= N; i++) {
                    for (edge each : edgeList) {
                        if (dist[each.from] == MAX)
                            continue;
                        if (dist[each.from] + each.cost < dist[each.to]) {
                            dist[each.to] = dist[each.from] + each.cost;
                            if (!visited[each.to])
                                visited[each.to] = true;
                            if (i == N)
                                nCycle = true;
                        }
                    }
                }

                allVisit = true;
                for (int i = 1; i <= N; i++) {
                    if (!visited[i]) {
                        start = i;
                        allVisit = false;
                        break;
                    }
                }
            }
            if (nCycle)
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }
        System.out.print(sb);
    }
}
