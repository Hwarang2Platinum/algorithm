import java.util.*;

class Solution {
    int[] uf;
    
    int find(int n) {
        if (uf[n] < 0)
            return n;
        return uf[n] = find(uf[n]);
    }
    
    void merge(int a, int b) {
        int A = find(a);
        int B = find(b);
        
        if (A != B)
            uf[A] = B;
    }
    
    public int solution(int n, int[][] computers) {
        uf = new int[n];
        Arrays.fill(uf, -1);
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                if (computers[i][j] == 1)
                    merge(i, j);
        int ans = 0;
        for(int i = 0; i < n; i++)
            ans += uf[i] < 0 ? 1 : 0;
        
        return ans;
    }
}