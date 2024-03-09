import java.util.*;

class Solution {
    static int cnt;
    public int solution(String[][] clothes) {
        subset(0,0,clothes,new HashSet<>());
        return cnt;
    }

    public void subset(int depth, int start, String[][] clothes, Set<String> visited){
        if(depth == clothes.length){
            cnt++;
        }
        for(int i=start;i<clothes.length;i++){
            if(visited.contains(clothes[i][1])) continue;
            System.out.println(Arrays.toString(clothes[i]));
            visited.add(clothes[i][1]);
            subset(depth+1,i+1,clothes,visited);
            visited.remove(clothes[i][1]);
        }
    }
}