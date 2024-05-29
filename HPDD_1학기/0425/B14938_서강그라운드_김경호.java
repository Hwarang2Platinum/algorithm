import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] adjMatrix = new int[n+1][n+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if(i==j) continue;
                adjMatrix[i][j] = 123456789;
            }
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjMatrix[a][b] = c;
            adjMatrix[b][a] = c;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n ; i++) {
                for (int j = 1; j <= n; j++) {
                    if(adjMatrix[i][k]+adjMatrix[k][j] < adjMatrix[i][j])
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int tmp = 0;
            for (int j = 1; j <= n; j++) {
                if(adjMatrix[i][j] > m) continue;
                tmp+=items[j];
            }
            ans = Math.max(ans,tmp);
        }
        System.out.println(ans);
    }


}
