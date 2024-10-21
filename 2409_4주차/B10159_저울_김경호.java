import java.util.*;
import java.io.*;

public class Main {
    static int[] ans;
    static List<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N+1];
        ans = new int[N+1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int bigOne = Integer.parseInt(st.nextToken());
            int smallOne = Integer.parseInt(st.nextToken());

            adjList[bigOne].add(smallOne);
        }

        for (int i = 1; i < N+1; i++) {
            boolean[] isv = new boolean[N+1];
            isv[i] = true;
            dfs(i,i, isv);
        }

        for (int i = 1; i < N+1; i++) {
            System.out.println(N-ans[i]);
        }

    }

    public static void dfs(int start, int cur, boolean[] isv){
        ans[start]++;
        if(start!=cur)
            ans[cur]++;

        for(int next : adjList[cur]){
            if(isv[next]) continue;
            isv[next] = true;
            dfs(start,next,isv);
        }

    }

}
