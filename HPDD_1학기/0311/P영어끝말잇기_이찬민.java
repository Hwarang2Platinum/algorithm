import java.io.*;
import java.util.*;
public class P영어끝말잇기_이찬민 {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};

        Map<String, String> map = new HashMap<>();

        for(int i = 0; i < words.length; i++) {
            if(i > 0) { //0일떄 스킵
                String prevWord = words[i - 1];
                String nowWord = words[i];

                char prevLast = prevWord.charAt(prevWord.length() - 1);
                char nowFirst = nowWord.charAt(0);

                if(map.containsKey(nowWord) || prevLast != nowFirst) {
                    answer[0] = (i % n) + 1; // 번호
                    answer[1] = (i / n) + 1; // 몇바퀴?
                    break;
                }
            }
            map.put(words[i], "shit");
        }

        return answer;
    }
}
