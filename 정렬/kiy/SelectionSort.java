// 선택정렬
public class SelectionSort {

  public static void main(String[] args) {
    int[] nums = {1,5,6,3,2,8,10,4,7,9};

    for(int i=0; i<nums.length; i++) {
      // 최솟값을 맨 앞으로 계속 보내기
      int minIdx = i;
      for(int j=i; j<nums.length; j++) {
        if(nums[j] < nums[minIdx]) {
          minIdx = j;
        }
      }
      // 현재 minIdx : i ~ 끝까지 중에서 제일 작은 값의 인덱스
      // 현재 값과 제일 작은값 위치 바꾸기
      int tmp = nums[i];
      nums[i] = nums[minIdx];
      nums[minIdx] = tmp;
    }

    for(int i=0; i<nums.length; i++) System.out.print(nums[i] + " ");
  }

}