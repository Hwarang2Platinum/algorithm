import java.util.*;
import java.io.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] adjList = new ArrayList[N+1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[b].add(a);
        }
        int mx = 0;
        Queue<Integer> ans = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            int ret = bfs(i, adjList);
            if(mx == ret){
                ans.add(i);
            } else if (mx < ret) {
                mx = ret;
                ans.clear();
                ans.add(i);
            }
        }

        while (!ans.isEmpty()){
            System.out.print(ans.poll() + " ");
        }


    }

    static int bfs(int start, List<Integer>[] adjList){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] isv = new boolean[N+1];
        q.add(start);
        isv[start] = true;

        int ret = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for(int next : adjList[cur]){
                if(isv[next]) continue;
                isv[next] = true;
                ret++;
                q.add(next);
            }
        }
        return ret;
    }

}
