package study;

class P_스킬트리_김인엽 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String skill_tree : skill_trees) {
            String[] tmpSkillTree = skill_tree.split("");
            StringBuilder skillTreesWithSkill = new StringBuilder();
            for(String tmpSkill : tmpSkillTree) {
                if(skill.contains(tmpSkill)) {
                    skillTreesWithSkill.append(tmpSkill);
                }
            }

            boolean isAnswer = true;
            for(int i=0; i<skillTreesWithSkill.length(); i++) {
                if(skill.charAt(i) != skillTreesWithSkill.charAt(i)) {
                    isAnswer = false;
                    break;
                }
            }
            if(isAnswer) answer++;
        }

        return answer;
    }
}
