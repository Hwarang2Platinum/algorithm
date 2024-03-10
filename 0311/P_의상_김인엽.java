import java.util.HashMap;
import java.util.Map;

public class P_의상_김인엽 {

  public static void main(String[] args) {
    String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
//    String[][] clothes = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
    Solution solution = new Solution();
    System.out.println(solution.solution(clothes));

  }
}

class Solution {
  public int solution(String[][] clothes) {
    // 옷 종류 : 옷 개수
    Map<String, Integer> clothesMap = new HashMap<>();

    for(String[] clothe : clothes) {
      String type = clothe[1];
      // 만약 갖고있다면, 개수 추가
      if(clothesMap.containsKey(type)) {
        clothesMap.put(type, clothesMap.get(type) + 1);
      } else { // 없다면 새로 추가
        clothesMap.put(type, 1);
      }
    }

    int answer = 1;
    for (String key : clothesMap.keySet()) {
      answer *= clothesMap.get(key) + 1;
    }
    return answer - 1;
  }
}