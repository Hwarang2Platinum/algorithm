import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] parent;
    static List<int[]> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeList.add(new int[]{s,e,w});
        }

        edgeList.sort(((o1, o2) -> o2[2] - o1[2]));
        parent = new int[N+1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        int ans = 1000000;
        for (int i = 0; i < M; i++) {
            int[] cur = edgeList.get(i);

            if(find(cur[0]) != find(cur[1])){
                union(cur[0],cur[1]);
                ans = Math.min(ans,cur[2]);
            }

            if(find(start) == find(end)){
                System.out.println(ans);
                return;
            }

        }
        System.out.println(0);

    }


    static int find(int x){
        if(parent[x] == x)
            return parent[x];

        return parent[x] = find(parent[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            parent[y] = x;
        else
            parent[x] = y;
    }

}
