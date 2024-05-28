import java.util.*;

class Solution {
    static char[] nameChs;
    static int min;
    static boolean[] isv;
    public int solution(String name) {
        int answer =0;
        int cnt;
        nameChs = name.toCharArray();
        min=Integer.MAX_VALUE;
        cnt = 0;
        isv = new boolean[name.length()];

        for(int i=0;i<name.length();i++){
            nameChs[i] -= 'A';
            answer += 26- nameChs[i] > nameChs[i]?nameChs[i]:26-nameChs[i];
            if(nameChs[i]==0) {
                isv[i] = true;
                cnt++;
            }
        }

        if(!isv[0]) cnt++;
        isv[0] = true;
        dfs(cnt,0,0);
        return answer+min;
    }
    static void dfs(int depth, int cursor, int ans){
        if(depth == nameChs.length){
            min = Math.min(min, ans);
            return;
        }
        int left = cursor;
        int leftCnt =0;
        int right = cursor;
        int rightCnt =0;
        while(true){
            if(--left < 0) left = nameChs.length-1;
            leftCnt++;
            if(!isv[left]) break;
        }
        while(true){
            if(++right > nameChs.length-1) right = 0;
            rightCnt++;
            if(!isv[right]) break;
        }

        isv[left] = true;
        dfs(depth+1, left, ans + leftCnt);
        isv[left] = false;

        isv[right] = true;
        dfs(depth+1, right, ans + rightCnt);
        isv[right] = false;
    }
}