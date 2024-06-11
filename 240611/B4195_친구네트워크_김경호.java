import java.io.*;
import java.util.*;
/**
 * BOJ 4195 친구네트워크
 * 유니온 파인드 + Hash
 *
 * Map => <이름, parent의 인덱스>
 * 인덱스 구한 후 유니온 파인드 로직
 */
public class Main {
    static Map<String,Integer> mapping;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = null;
            int idx=0;
            int N = Integer.parseInt(br.readLine());
            mapping = new HashMap();
            parent = new int[2*N+1];
            for (int i = 0; i < 2 * N + 1; i++) {
                parent[i] = -1;
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if(!mapping.containsKey(a)) {
                    mapping.put(a,idx++);
                }
                if(!mapping.containsKey(b)) {
                    mapping.put(b,idx++);
                }
                int aIdx = mapping.get(a);
                int bIdx = mapping.get(b);

                sb.append(union(aIdx, bIdx))
                        .append("\n");
            }
        }
        System.out.print(sb);
    }

    public static int find(int x){
        if(parent[x]<0){
            return x;
        }else {
            return find(parent[x]);
        }
    }

    public static int union(int x, int y){
        int root_x = find(x);
        int root_y = find(y);

        if(root_x==root_y) return -parent[root_x];

        if(parent[root_x] < parent[root_y]){
            parent[root_x] += parent[root_y];
            parent[root_y] = root_x;
            return -parent[root_x];
        }else{
            parent[root_y] += parent[root_x];
            parent[root_x] = root_y;
            return -parent[root_y];
        }
    }

}