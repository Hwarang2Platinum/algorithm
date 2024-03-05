import java.util.ArrayList;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] arr = new char[skill.length()];
        for (int i = 0; i < skill.length(); i++) {
            arr[i] = skill.charAt(i);
        }
        for (String skill_tree : skill_trees) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int i = 0; i < skill_tree.length(); i++) {
                for (int j = 0; j < skill.length(); j++) {
                    if(skill_tree.charAt(i)==arr[j]){
                        temp.add(arr[j]);
                    }
                }
            }
            boolean flag = false;
            for (int i = 0; i < temp.size(); i++) {
                if (arr[i] != temp.get(i)) {
                    flag = true;
                    break;
                }
            }
            if(!flag)answer++;

        }
        return answer;
    }
}