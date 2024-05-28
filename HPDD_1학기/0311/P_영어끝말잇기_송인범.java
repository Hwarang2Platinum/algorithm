package March;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P_영어끝말잇기_송인범 {
	
	public static int[] solution(int n, String[] words) {
		Set<String>dic = new HashSet<String>();
        int[] answer = {0,0};
        for(int i=1; i<words.length; i++) {
        	if(words[i-1].charAt(words[i-1].length()-1)!=words[i].charAt(0)|| dic.contains(words[i])) {
        		answer[0] = (i%n)+1;
        		answer[1] = (i/n)+1;
        		return answer;
        	}
        	dic.add(words[i-1]);
        }
      
        return answer;
    }
	
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(3, new String[] {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
	}
}
