import java.io.*;
import java.util.*;
/**
 * 벨만포드 알고리즘
 * 벨만포드는 한 정점에서 다른 정점으로의 최소값을 판단
 * 그리고 음의 사이클까지 확인할 수 있다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());


        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[] dist = new int[N+1];


            List<int[]> edges = new ArrayList<>();

            for (int i = 0; i < M ; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                edges.add(new int[]{S,E,C});
                edges.add(new int[]{E,S,C});
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                edges.add(new int[]{S,E,-C});
            }

            int E = edges.size();
            boolean flag = false;
            boolean[] isv = new boolean[N+1];

            for (int k = 1; k <= N; k++) {
                if(isv[k]) continue;
                Arrays.fill(dist, 123456789);
                dist[k] = 0;
                for (int i = 0; i < N ; i++) {
                    for (int j = 0; j < E; j++) {
                        int[] edge = edges.get(j);
                        int s = edge[0];
                        int e = edge[1];
                        int c = edge[2];
                        isv[s] = true;
                        isv[e] = true;

                        if(dist[e] > dist[s]+c){
                            dist[e] = dist[s]+c;
                            if(i==N-1) flag = true;
                        }
                    }
                }
            }

            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }



    }

}