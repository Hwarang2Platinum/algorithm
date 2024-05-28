package March;

import java.util.HashMap;
import java.util.Map;

public class P_의상_송인범 {
	
	public static int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer>h = new HashMap<String, Integer>();
        for(String [] c: clothes) {
        	h.put(c[1], h.getOrDefault(c[1], 0)+1); //종류별 추가
        }
        
        for(int cnt : h.values()) { 
        	answer*=(cnt+1); //쓰거나 안쓰거나
        }
        
        return answer-1;
    }

	public static void main(String[] args) {
		System.out.println(solution(new String[][] {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));

	}

}
