import java.util.Arrays;

// 버블정렬
public class BubbleSort {

  public static void main(String[] args) {
    // 젤 큰값을 먼저 줄세우기
    int[] nums = {1,5,6,3,2,8,10,4,7,9};
    System.out.println("정렬 전: " + Arrays.toString(nums));
    for(int i=nums.length-1; i>=0; i--) {
      for(int j=0; j<i; j++) {
        // 만약 앞에 값이 뒷값보다 크다면, 교체
        if(nums[j] > nums[j+1]) {
          int tmp = nums[j];
          nums[j] = nums[j+1];
          nums[j+1] = tmp;
        }
      }
    }
    System.out.println("정렬 후:" + Arrays.toString(nums));
  }

}