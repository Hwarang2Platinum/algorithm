import java.io.*;
import java.util.*;

// ! Hwarang Think !

// 사심 경로는 남초 대학교와 여초 대학교들을 연결하는 도로로만 이루어져 있다.
// 사용자들이 다양한 사람과 미팅할 수 있도록, 어떤 대학교에서는 모든 대학교로 이동이 가능한 경로
// 시간을 낭비하지 않고 미팅할 수 있도록 경로의 길이는 최단 거리가 되어야 함
// 남초와 여초 학교만 연결하는 도로로만 이루어져 있어야 한다. 즉, A - B - A - B - A - B 형태 ( 형태는
// ABABABABABAB 형태 )
// 어떤 대학교에서든 모든 대학교로 이동이 가능한 경로 ( 모든 대학교가 포함되어야 한다 )
// 시간을 낭비하지 않는 최단거리
// 남초 -> 여초 -> 남초 -> 여초 남초

// 같은 성별에 대한 Edge를 빼주면, 이제 서로 다른 성별로 향하는 Edge만 남아 있을 것이다.
// 이를 바탕으로 MST를 진행하면, 최소 신장 트리가 완성된다.
// 이 문제를 왜 MST로 해결해야 할까? -> 완탐으로 풀면 100%로 터지는 문제 구성 -> 그래도 최소 신장 트리로 해결될 것을 보장할
// 수 있는가?
// 이 문제는 Cycle이 구조적으로 생길 수가 없다. A -> B -> A1 -> B1로 이어지는 구조이기에, 전에 왔던 곳으로 돌아가는
// 것을 제거하면, 쭉 앞으로만 가게되는 구성이다.
// 이는 Cycle을 유발할 수 있는, 같은 학교로 A -> A와 B -> B르 가는 경로를 없앴기 때문이다.
// MST로 풀어내보자. => Kruskal? => EdgeList를 써야 수월하다. 구현해보자.

public class B14621_나만안되는연애_오화랑 {
    static class edge {
        int sNode, eNode, cost;

        public edge(int sNode, int eNode, int cost) {
            this.sNode = sNode;
            this.eNode = eNode;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "edge [sNode=" + sNode + ", eNode=" + eNode + ", cost=" + cost + "]";
        }
    }

    static PriorityQueue<edge> edgeList;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int nCnt = Integer.parseInt(st.nextToken());
        int eCnt = Integer.parseInt(st.nextToken());
        int[] sInfoList = new int[nCnt + 1];
        edgeList = new PriorityQueue<edge>(eCnt, (o1, o2) -> Integer.compare(o1.cost, o2.cost));
        st = new StringTokenizer(input.readLine());
        int start = 1;
        while (st.hasMoreTokens()) { // Man / Women 정보를 바탕으로, 각 Node의 Information을 받아온다.
            if (st.nextToken().equals("M"))
                sInfoList[start++] = 0;
            else
                sInfoList[start++] = 1;
        }

        int sNode, eNode, cost;
        for (int i = 0; i < eCnt; i++) { // 사심 경로를 결정할 때, 같은 Man or Women 특성을 가졌다면 설정 불가 => Edge 구성에서 빼준다.
            st = new StringTokenizer(input.readLine());
            sNode = Integer.parseInt(st.nextToken());
            eNode = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            if (sInfoList[sNode] == sInfoList[eNode])
                continue;
            edgeList.add(new edge(sNode, eNode, cost));
        }

        // ! MST PQ로 구현하면서 이렇게 했다가 오류뜸 !
        // makeSet(nCnt);
        // int totalCost, cnt;
        // boolean mstMade = false;
        // totalCost = cnt = 0;
        // for (edge eachE : edgeList) {
        // System.out.println(eachE);
        // if (!union(eachE.sNode, eachE.eNode))
        // continue;
        // totalCost += eachE.cost;
        // if (++cnt == nCnt - 1) {
        // mstMade = true;
        // break;
        // }
        // }
        makeSet(nCnt);
        int totalCost, cnt;
        boolean mstMade = false;
        totalCost = cnt = 0;
        edge tempE;
        while (!edgeList.isEmpty()) {
            tempE = edgeList.poll();
            if (!union(tempE.sNode, tempE.eNode))
                continue;
            totalCost += tempE.cost;
            if (++cnt == nCnt - 1) {
                mstMade = true;
                break;
            }
        }
        if (mstMade)
            System.out.println(totalCost);
        else
            System.out.println(-1);
    }

    public static void makeSet(int nCnt) {
        parents = new int[nCnt + 1];
        for (int i = 1; i <= nCnt; i++)
            parents[i] = i;
    }

    public static int find(int eachNode) {
        if (eachNode == parents[eachNode])
            return eachNode;
        return parents[eachNode] = find(parents[eachNode]);
    }

    public static boolean union(int aNode, int bNode) {
        int aRoot = find(aNode);
        int bRoot = find(bNode);
        if (aRoot == bRoot)
            return false;
        parents[bRoot] = aRoot;
        return true;
    }
}