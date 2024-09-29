class Solution {
    static int[] parent;
    public int solution(int n, int[][] computers) {
        int answer = 0;

        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = -1;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;
                if(computers[i][j]==1) union(i,j);
            }
        }

        for(int i=0;i<n;i++){
            if(parent[i]<0) answer++;
        }

        return answer;
    }

    static int find(int x){
        if(parent[x]<0){
            return x;
        }else{
            return find(parent[x]);
        }
    }

    static int union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX==rootY) return -1;

        if(parent[rootX] > parent[rootY]){
            parent[rootY] += parent[rootX];
            parent[rootX] = rootY;
        }else{
            parent[rootX] += parent[rootY];
            parent[rootY] = rootX;
        }

        return 1;
    }

}