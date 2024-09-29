import java.util.*;

class Solution {
    // 유니온 파인드 해봐야지
    
    public int solution(int n, int[][] computers) {
        int[] parents = new int[n];
        init(parents); // 부모 배열 초기화
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i == j) continue;
                if(computers[i][j] == 1) {
                    union(parents, i, j); // 합치기
                }
            }
        }
        Set<Integer> set = new HashSet<>(); // 셋에 넣기
        for(int i=0; i<n; i++) {
            set.add(find(parents, i));
        }
    
        return set.size(); // 셋 크기가 정답
    }
    
    private void init(int[] parents) {
        for(int i=0; i<parents.length; i++) {
            parents[i] = i;
        }
    }
    
    private int find(int[] parents, int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents, parents[v]);
    }
    
    private boolean union(int[] parents, int a, int b) {
        int aRoot = find(parents, a);
        int bRoot = find(parents, b);
        
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return false;
    }
}