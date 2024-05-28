import java.util.Arrays;

// 삽입 정렬
public class InsertionSort {

  public static void main(String[] args) {
    int[] nums = { 1, 5, 6, 3, 2, 8, 10, 4, 7, 9 };
    System.out.println("정렬 전: " + Arrays.toString(nums));
    // 앞에 두개부터 차근차근 정렬된 개수를 늘려나가는 그런 느낌
    for(int i=1; i<nums.length; i++) {
      // 현재꺼에서 그 전꺼까지 정렬하기
      for(int j=i; j>=0; j--) {
        // 만약 질서를 거역하면 교체
        if(nums[j] < nums[j-1]) {
          int tmp = nums[j-1];
          nums[j-1] = nums[j];
          nums[j] = tmp;
        }
        // 새로 들어온 친구가 제 자리에 잘 있다면(그 전보다 더 크다면),
        // 그 전은 이미 정렬되어있으니 break
        else {
          break;
        }
      }
    }

    System.out.println("정렬 후:" + Arrays.toString(nums));
  }

}