import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        Set<String> visited = new HashSet<>();
        visited.add(words[0]);
        for(int i=1;i<words.length;i++){
            if(words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)
                    ||visited.contains(words[i])){
                answer[1] = (i/n) +1;
                answer[0] = (i%n) +1;
                break;
            }
            visited.add(words[i]);
        }
        return answer;
    }
}