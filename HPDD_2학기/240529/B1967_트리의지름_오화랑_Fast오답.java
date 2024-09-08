import java.io.*;
import java.util.*;

public class B1967_트리의지름_오화랑_Fast오답 {
    static class pair {
        int n, w;

        public pair(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }

    static ArrayList<ArrayList<pair>> graph;
    static int maxDiameter;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int nNum = Integer.parseInt(input.readLine());
        int[][] checkNode = new int[nNum + 1][3];
        graph = new ArrayList<>(nNum + 1);
        for (int i = 0; i <= nNum; i++)
            graph.add(new ArrayList<pair>());

        int sNode, eNode, weight;
        int maxNotTerminalNode = 1;
        for (int i = 0; i < nNum - 1; i++) {
            st = new StringTokenizer(input.readLine());
            sNode = Integer.parseInt(st.nextToken());
            eNode = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            graph.get(sNode).add(new pair(eNode, weight));
            maxNotTerminalNode = sNode;
        }

        int idx;
        for (int i = maxNotTerminalNode; i >= 1; i--) {
            idx = 0;
            for (pair eachP : graph.get(i)) {
                int temp = checkNode[eachP.n][0] + eachP.w;
                if (idx == 0) {
                    checkNode[i][0] = temp;
                    idx++;
                }
                if (temp > checkNode[i][0]) {
                    checkNode[i][0] = temp;
                } else if (checkNode[i][1] <= temp || temp <= checkNode[i][0]) {
                    checkNode[i][1] = temp;
                }
            }
            checkNode[i][2] = checkNode[i][0] + checkNode[i][1];
            maxDiameter = Math.max(maxDiameter, checkNode[i][2]);
        }
        System.out.println(maxDiameter);
    }
}
