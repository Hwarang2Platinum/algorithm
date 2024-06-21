import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    static List<int[]> edgeList;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]> edgeList = new ArrayList<>();
        int inc = 0; int dec = 0;
        for (int i = 0; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if(C==0) inc++;
            else dec++;
            edgeList.add(new int[]{A,B,C});
        }
        int mn,mx;
        parent = new int[N+1];
        Arrays.fill(parent,-1);
        Collections.sort(edgeList, ((o1, o2) -> o2[2]-o1[2]));
        int tmp=0;

        for (int i = 0; i < dec; i++) {
            int[] edge = edgeList.get(i);
            union(edge[0], edge[1]);
        }
        for (int i = dec; i < dec+inc; i++) {
            int[] edge = edgeList.get(i);
            if(union(edge[0], edge[1])) tmp++;
        }
        mn = (tmp)*(tmp);

        Arrays.fill(parent,-1);
        Collections.sort(edgeList, ((o1, o2) -> o1[2]-o2[2]));
        tmp = 0;
        for (int i = 0; i < inc; i++) {
            int[] edge = edgeList.get(i);
            if(union(edge[0], edge[1])) tmp++;
        }
        mx = tmp*tmp;
        System.out.println(mx - mn);
    }

    public static int find(int x){
        if(parent[x]<0) {
            return x;
        }else{
            return find(parent[x]);
        }
    }

    public static boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false;

        if(parent[rootX] < parent[rootY]){
            parent[rootX] += parent[rootY];
            parent[rootY] = rootX;
        }else{
            parent[rootY] += parent[rootX];
            parent[rootX] = rootY;
        }
        return true;
    }

}