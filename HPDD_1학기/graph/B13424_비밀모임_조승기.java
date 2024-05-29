import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            ArrayList<Pair>[] adj = new ArrayList[N];
            int[][] li = new int[N][N];
            int INTMAX = 912_345_678;

            for(int i = 0; i < N; i++) {
                adj[i] = new ArrayList<>();
                Arrays.fill(li[i], INTMAX);
                li[i][i] = 0;
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                int w = Integer.parseInt(st.nextToken());
                adj[a].add(new Pair(b, w));
                adj[b].add(new Pair(a, w));
                li[a][b] = w;
                li[b][a] = w;
            }

            for(int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (i == j) { continue; }
                        if (li[i][k] + li[k][j] < li[i][j]) {
                            li[i][j] = li[i][k] + li[k][j];
                        }
                    }
                }
            }

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] parti = new int[K];
            for(int k = 0; k < K; k++) {
                parti[k] = Integer.parseInt(st.nextToken())-1;
            }
            int ans = INTMAX;
            int index = -1;
            for(int i = 0; i < N; i++) {
                int local = 0;
                for(int k = 0; k < K; k++) {
                    local += li[parti[k]][i];
                }
                if (local < ans) {
                    ans = local;
                    index = i + 1;
                }
            }
            System.out.println(index);
        }
    }
    static class Pair {
        int a;
        int n;
        Pair(int a, int n) {
            this.a = a;
            this.n = n;
        }
    }
}