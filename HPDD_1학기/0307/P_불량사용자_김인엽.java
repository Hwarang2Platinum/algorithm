package study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P_불량사용자_김인엽 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        System.out.println(solution.solution(user_id, banned_id));
    }
}

class Solution {
    static List<String>[] bannedUsers;
    static int answer = 0;
    static Set<Set<String>> answerSet = new HashSet<>();
    // 해당 유저 아이디가 밴 아이디에 들어갈 수 있는지 확인
    public static void check(String user_id, String[] banned_id) {
        for(int i=0; i<banned_id.length; i++) {
            boolean isEqual = true;
            // 길이가 다르면 넘어가기
            if(banned_id[i].length() != user_id.length()) continue;
            // 이제 하나하나 비교
            for(int j=0; j<banned_id[i].length(); j++) {
                // 별표면 패스
                if(banned_id[i].charAt(j) == '*') continue;
                // 나머지는 같은지 확인(만약 다르다는게 판별나면 끝)
                if(banned_id[i].charAt(j) != user_id.charAt(j)) {
                    isEqual = false;
                    break;
                }
            }
            if(isEqual) bannedUsers[i].add(user_id);
        }
    }
    public static void dfs(int depth, HashSet<String> tmpBannedUsers) {
        if(depth == bannedUsers.length) {
//            answer++;
            answerSet.add(tmpBannedUsers);
            return;
        }
        for(String bannedUser : bannedUsers[depth]) {
            if(tmpBannedUsers.contains(bannedUser)) continue;
            HashSet<String> newBannedUsers = (HashSet<String>) tmpBannedUsers.clone();
            newBannedUsers.add(bannedUser);
            dfs(depth+1, newBannedUsers);
        }
    }
    public int solution(String[] user_id, String[] banned_id) {
        bannedUsers = new ArrayList[banned_id.length];
        for (int i = 0; i < bannedUsers.length; i++) {
            bannedUsers[i] = new ArrayList<>();
        }

        // 각 밴 아이디별 가능한 user들 탐색
        for (String u_id : user_id) {
            check(u_id, banned_id);
        }

        dfs(0, new HashSet<>());

        return answerSet.size();
    }
}
