import java.io.*;
import java.util.*;

class Solution {

    public int solution(String[] words) {
        int answer = 0;
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        // System.out.println(Arrays.toString(words));
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 1; j <= words[i].length(); j++) {
                String sub = words[i].substring(0, j);
                map.putIfAbsent(sub, 0);
                map.put(sub, map.get(sub) + 1);
            }
        }
        // System.out.println(map);
        for (int i = 0; i < words.length; i++) {
            for (int j = 1; j <= words[i].length(); j++) {
                String sub = words[i].substring(0, j);
                // System.out.println(sub + ", " + map.get(sub));
                if (map.get(sub) == 1 || j == words[i].length()) {
                    answer += j;
                    break;
                }
            }
        }

        return answer;
    }
}