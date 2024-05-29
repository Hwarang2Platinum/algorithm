package tempDone;

import java.io.*;
import java.util.*;

public class B1967_트리의지름_오화랑 {
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
        for (int i = 0; i < nNum - 1; i++) {
            st = new StringTokenizer(input.readLine());
            sNode = Integer.parseInt(st.nextToken());
            eNode = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            graph.get(sNode).add(new pair(eNode, weight));
            graph.get(eNode).add(new pair(sNode, weight));
        }

        for (int i = 1; i <= nNum; i++) {
            visited = new boolean[nNum + 1];
            getMaxDiameter(i, 0);
        }
        System.out.println(maxDiameter);
    }

    public static void getMaxDiameter(int current, int count) {
        visited[current] = true;
        maxDiameter = Math.max(maxDiameter, count);
        for (pair tempP : graph.get(current)) {
            if (!visited[tempP.n])
                getMaxDiameter(tempP.n, count + tempP.w);
        }
    }
}