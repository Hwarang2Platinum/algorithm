import java.io.*;
import java.util.*;

public class B1967_트리의지름_오화랑_DFS오류 {
    static class pair {
        int n, w;

        public pair(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }

    static ArrayList<ArrayList<pair>> graph;
    static boolean[] visited;
    static int maxDiameter;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int nNum = Integer.parseInt(input.readLine());
        graph = new ArrayList<>(nNum + 1);
        for (int i = 0; i <= nNum; i++)
            graph.add(new ArrayList<>());

        int sNode, eNode, weight;
        int maxNotTerminalNode = 1;
        for (int i = 0; i < nNum - 1; i++) {
            st = new StringTokenizer(input.readLine());
            sNode = Integer.parseInt(st.nextToken());
            eNode = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            graph.get(sNode).add(new pair(eNode, weight));
            graph.get(eNode).add(new pair(sNode, weight));
            maxNotTerminalNode = sNode;
        }
        for (int i = maxNotTerminalNode + 1; i < nNum; i++) {
            for (int j = i + 1; j <= nNum; j++) {
                visited = new boolean[nNum + 1];
                getMaxDiameter(i, j, 0);
            }
        }
        System.out.println(maxDiameter);
    }

    public static void getMaxDiameter(int start, int end, int count) {
        if (start == end) {
            maxDiameter = Math.max(maxDiameter, count);
        }
        visited[start] = true;
        for (pair tempP : graph.get(start)) {
            if (visited[tempP.n])
                continue;
            getMaxDiameter(tempP.n, end, count + tempP.w);
        }
    }
}