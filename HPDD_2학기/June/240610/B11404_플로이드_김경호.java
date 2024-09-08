import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] adjMatrix = new int[n+1][n+1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if(i==j) continue;
                adjMatrix[i][j] = 10000001;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjMatrix[from][to] = Math.min(adjMatrix[from][to],cost);
        }

        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < n+1; j++) {
                    if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]){
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                System.out.print(adjMatrix[i][j]>=10000001?0+" ":+adjMatrix[i][j]+" ");
            }
            System.out.println();
        }

    }
}