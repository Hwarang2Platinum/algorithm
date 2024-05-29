import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"});

    }
    static Set<List<String>> ans;
    public static int solution(String[] user_id, String[] banned_id) {
        Set<String> visited = new HashSet<>();
        ans = new HashSet<>();
        dfs(0,visited, user_id, banned_id);
        System.out.println(ans.toString());
        return ans.size();
    }

    private static void dfs(int depth, Set<String> visited, String[] user_id, String[] banned_id) {
        if(depth == banned_id.length){
            List<String> tmp = new ArrayList<>();
            for(String v: visited){
                tmp.add(v);
            }
            Collections.sort(tmp);
            ans.add(tmp);
            return;
        }
        String curBannedId = banned_id[depth];
        //curBannedId에 대한 정규표현식으로 user id검사
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        for(int i=0;i< curBannedId.length();i++){
            if(curBannedId.charAt(i)=='*') sb.append("[0-9a-zA-Z]");
            else sb.append(curBannedId.charAt(i));
        }
        sb.append("$");
        String regex = sb.toString();
        for(String user:user_id){
            if(user.matches(regex)) {
                if(visited.contains((user))) continue;
                visited.add(user);
                dfs(depth+1, visited, user_id, banned_id);
                visited.remove(user);
            }
        }
    }
}