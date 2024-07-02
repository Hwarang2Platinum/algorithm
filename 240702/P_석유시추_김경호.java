import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};
        int n = land.length;
        int m = land[0].length;
        int[] answers = new int[m];
        boolean[][] isv = new boolean[n][m];
        
        for(int i=0;i<m;i++){
            
            for(int j=0;j<n;j++){
                Set<Integer> set = new HashSet();
                if(land[j][i]==0 || isv[j][i]) continue;
                int tmp = 0;
                Queue<int[]> q = new ArrayDeque();
                q.add(new int[]{j,i});
                isv[j][i] = true;
                
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    tmp++;
                    set.add(cur[1]);
                    for(int k=0;k<4;k++){
                        int nextR = cur[0] + dr[k];
                        int nextC = cur[1] + dc[k];
                        
                        if(nextR<0||nextR>n-1||nextC<0||nextC>m-1) continue;
                        if(land[nextR][nextC]==0 || isv[nextR][nextC]) continue;
                        isv[nextR][nextC] = true;
                        
                        q.add(new int[]{nextR,nextC});
                    }
                }
                for(int tmpAns : set){
                    answers[tmpAns] += tmp;
                }
                
            }
            
            
        }
        // System.out.println(Arrays.toString(answers));
        for(int i=0;i<m;i++){
            answer = Math.max(answer, answers[i]);
        }
        return answer;
    }
}
