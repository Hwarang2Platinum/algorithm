import java.util.ArrayList;
import java.util.HashMap;

public class P_의상_조승기 {
    class Solution {
        public static int solution(String[][] clothes) {
            ArrayList<Integer> tmp = new ArrayList<>();
            HashMap<String, Integer> map = new HashMap<>();
            int ans = 1;

            for (int i = 0; i < clothes.length; i++) {
                if (map.containsKey(clothes[i][1])) {
                    int index = map.get(clothes[i][1]);
                    tmp.set(index, tmp.get(index)+1);
                } else {
                    tmp.add(1);
                    map.put(clothes[i][1], tmp.size()-1);
                }
            }

            for(int i = 0; i < tmp.size(); i++) {
                ans *= tmp.get(i) + 1;
            }
            return ans-1;
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(Main.Solution.solution(new String[][]{{"A","B"}}));
    }
}
