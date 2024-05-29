import java.util.HashSet;
import java.util.Set;

class Solution {
	public static void main(String[] args) {
		solution("",new String[]{""});
	}
    public static int solution(String skill, String[] skill_trees) {
    	skill = "CBD";
    	skill_trees = new String[] {"BACDE", "CBADF", "AECB", "BDA"};
        Set<Character> a = new HashSet<>();
        for(int i=0;i<skill.length();i++){
            a.add(skill.charAt(i));
        }
        int ans = 0;
        for(String skill_tree:skill_trees){
            int idx = 0;
            boolean flag=true;
            for(int i=0; i<skill_tree.length();i++){
                if(a.contains(skill_tree.charAt(i))){
                    if(skill.charAt(idx)==skill_tree.charAt(i)){
                        idx++;
                    }else{
                    	flag = false;
                        break;
                    }
                }
            }
            if(flag) ans++;
        }
        System.out.println(ans);
        return ans;
    }
}