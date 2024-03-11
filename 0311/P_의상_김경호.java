import java.util.*;

class Solution {
    static int cnt = 0;
    public int solution(String[][] clothes) {
        Map<String,Integer> ans = new HashMap<>();
        for(int i=0;i<clothes.length;i++){
            if(!ans.containsKey(clothes[i][1])){
                ans.put(clothes[i][1], 1);
            }else{
                ans.put(clothes[i][1], ans.get(clothes[i][1])+1);
            }
        }
        int[] values = new int[ans.values().size()];
        int idx=0;
        for(Integer v : ans.values()){
            values[idx++] = v;
        }
        bt(0,0,1,values);
        return cnt-1;
    }

    void bt(int depth, int start,int acum, int[] values){
        cnt+=acum;
        for(int i=start;i<values.length;i++){
            bt(depth+1,i+1,acum*values[i], values);
        }
    }
}