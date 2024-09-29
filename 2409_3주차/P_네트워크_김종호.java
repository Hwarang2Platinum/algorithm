import java.io.*;
import java.util.*;
// union - find

class Solution {
    
    static int[] p;
    
    public int find(int a){
        if (p[a] == a)
            return a;
        return p[a] = find(p[a]);
    }
    
    public void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if (fa == fb)
            return;
        p[fa] = fb;
    }
    
    public int solution(int n, int[][] computers) {
        p = new int[n];
        for (int i = 0; i < n; i++){
            p[i] = i;
        }
        for (int i =  0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i != j && computers[i][j] == 1){
                    union(i, j);
                }
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; i++){
            s.add(find(i));
        }
        return s.size();
    }
}