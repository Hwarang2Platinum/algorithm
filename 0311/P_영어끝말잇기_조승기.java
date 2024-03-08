import java.util.*;

public class P_영어끝말잇기_조승기 {
    class Solution {
        public static int[] solution(int n, String[] words) {
            HashSet<String> set = new HashSet<>();
            set.add(words[0]);
            for(int i = 1; i < words.length; i++) {
                if (!words[i - 1].endsWith(String.valueOf(words[i].charAt(0))) || set.contains(words[i])) {
                    return new int[]{(i % n) + 1, ((i) / n + 1)};
                }
                set.add(words[i]);
            }
            return new int[]{0, 0};
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution.solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));
        System.out.println(Arrays.toString(Solution.solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
        System.out.println(Arrays.toString(Solution.solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));
        System.out.println(Arrays.toString(Solution.solution(2, new String[]{"hi", "i", "ii", "i"})));
    }
}
