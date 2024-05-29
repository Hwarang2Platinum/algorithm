import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
    }
    static int N;
    static int K;
    static int ans;
    static ArrayList<Integer> tmp = new ArrayList<>();
    static void find(int n) {
        if (n == N) {
            if (tmp.size() == K && !visits.contains(tmp.hashCode())) {
                ans++;
                visits.add(tmp.hashCode());
            }
            return;
        }
        find(n + 1);
        for(int banpos: pos[n]) {
            if (!isv[banpos]) {
                isv[banpos] = true;
                tmp.add(n);
                find(n + 1);
                tmp.remove(tmp.size() - 1);
                isv[banpos] = false;
            }
        }
    }
    static HashSet<Integer>[] pos;
    static boolean[] isv;
    static HashSet<Integer> visits = new HashSet<>();

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        N = user_id.length;
        pos = new HashSet[user_id.length];
        isv = new boolean[user_id.length];
        K = banned_id.length;

        for (int i = 0; i < user_id.length; i++) {
            String user = user_id[i];
            pos[i] = new HashSet<>();

            for (int j = 0; j < banned_id.length; j++) {
                String ban = banned_id[j];
                if (ban.length() != user.length()) { continue; }
                boolean isFail = false;
                for(int k = 0; k < ban.length() && !isFail; k++){
                    if (ban.charAt(k) == '*') { continue; }
                    if (ban.charAt(k) != user.charAt(k)) {
                        isFail = true;
                        break;
                    }
                }
                if (!isFail) {
                    pos[i].add(j);
                }
            }
        }
        find(0);
        return ans;
    }
}