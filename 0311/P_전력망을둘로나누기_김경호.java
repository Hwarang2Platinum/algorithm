import java.util.Arrays;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] wires) {
        int min = Integer.MAX_VALUE;
        parent = new int[n+1];
        for(int i=0;i< wires.length;i++){
            Arrays.fill(parent,-1);
            int[] target = wires[i];
            for(int j=0;j< wires.length;j++){
                if(j==i) continue;
                union(wires[j][0], wires[j][1]);
            }
//            System.out.println(i+" "+parent[target[0]] +" " +parent[target[1]]);
            min = Math.min(min, Math.abs(parent[find(target[0])] - parent[find(target[1])]));
        }

        return min;
    }

    public static int find(int x){
        if(parent[x]<0){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }

    public static void union(int x, int y){
        int root_x = find(x);
        int root_y = find(y);

        if(root_x == root_y) return;

        if(parent[root_x] > parent[root_y]){
            parent[root_y] += parent[root_x];
            parent[root_x] = root_y;
        }else{
            parent[root_x] += parent[root_y];
            parent[root_y] = root_x;
        }
    }
}