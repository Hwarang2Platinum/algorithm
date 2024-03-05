import java.util.*;
class Solution {
    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        HashMap<Character, Integer> di = new HashMap<>();
        for(int i = 0; i < skill.length(); i++) {
            di.put(skill.charAt(i), i);
        }

        HashSet<Character>[] sets = new HashSet[skill.length()];
        sets[0] = new HashSet<>();

        for(int i = 1; i < skill.length(); i++) {
            sets[i] = (HashSet<Character>) sets[i-1].clone();
            sets[i].add(skill.charAt(i-1));
        }

        for(String s: skill_trees) {
            boolean isFail = false;
            HashSet<Character> localSet = new HashSet<>();

            for(int i = 0; i < s.length() && !isFail; i++) {
                if (di.containsKey(s.charAt(i))) {
                    for (Character before: sets[di.get(s.charAt(i))]) {
                        if (!localSet.contains(before)) {
                            isFail = true;
                        }
                    }
                } else {
                    localSet.add(s.charAt(i));
                    continue;
                }
                for(int j = i+1; j < s.length() && !isFail; j++) {
                    if (!di.containsKey(s.charAt(j))) { continue; }
                    if (di.get(s.charAt(i)) > di.get(s.charAt(j))) {
                        isFail = true;
                    }
                }
                localSet.add(s.charAt(i));
            }
            answer += isFail ? 0 : 1;
        }
        return answer;
    }
}