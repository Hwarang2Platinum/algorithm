import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = -1;
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> truthList = new ArrayList<>();
        int before=-1;
        for (int i = 0; i < K; i++) {
            int a = Integer.parseInt(st.nextToken());
            truthList.add(a);
            if(before==-1) {
                before = a;
                continue;
            }
            union(before,a);
            before = a;
        }


        int[] answer = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            before = -1;
            for (int j = 0; j < K; j++) {
                int a = Integer.parseInt(st.nextToken());
                if(before==-1) {
                    before = a;
                    continue;
                }
                union(before,a);
                before = a;
            }
            answer[i] = before;
        }

        Set<Integer> truthSet = new HashSet<>();
        for(int t:truthList){
            truthSet.add(find(t));
        }
        int ans = 0;
        for (int i = 0; i < M; i++) {
            if(truthSet.contains(find(answer[i]))) continue;
            ans++;
        }
        System.out.println(ans);
    }

    public static void union(int x, int y){
        int root_x = find(x);
        int root_y = find(y);

        if(root_x == root_y) return;

        if(parent[root_x] < parent[root_y]){
            parent[root_x] += parent[root_y];
            parent[root_y] = root_x;
        }else{
            parent[root_y] += parent[root_x];
            parent[root_x] = root_y;
        }
    }

    public static int find(int x){
        if(parent[x] < 0){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }
}