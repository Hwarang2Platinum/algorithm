import java.util.Arrays;

public class MergeSort {
  static int[] nums = {1,5,6,3,2,8,10,4,7,9};
  // 정렬된 배열
  static int[] sorted = new int[nums.length];

  public static void merge(int left, int mid, int right) {
    int i, j, k;
    i = left; // 왼쪽 배열 시작점
    j = mid+1; // 오른쪽 배열 시작점
    k = left; // 임시 배열의 인덱스

    // 두 배열 중 하나를 다 옮길 때까지 반복
    while(i <= mid && j <= right) {
      // 더 작은 값부터 임시 배열에 넣기 -> 인덱스 증가
      if(nums[i] < nums[j]) {
        sorted[k] = nums[i];
        i++;
      } else {
        sorted[k] = nums[j];
        j++;
      }
      // 기존 k에 이미 넣었으니 증가
      k++;
    }

    // 남아있는 데이터 삽입
    // i가 mid보다 커졌다는건, 왼쪽 부분 배열을 모두 넣었고, 오른쪽이 남았다!
    if(i > mid) {
      // 오른쪽 부분배열 털기
      for(int t = j; t<=right; t++) {
        sorted[k] = nums[t];
        k++;
      }
    } else {
      // 왼쪽 부분배열 털기
      for(int t = i; t<=mid; t++) {
        sorted[k] = nums[t];
        k++;
      }
    }
    // 정렬된 배열을 기준 배열에 삽입
    for(int t = left; t<=right; t++) {
      nums[t] = sorted[t];
    }
  }

  // 분할 -> 병합
  public static void mergeSort(int left, int right) {
    if(left < right) {
      int mid = (left + right) / 2;
      mergeSort(left, mid);
      mergeSort(mid+1, right);
      merge(left, mid, right);
    }
  }

  public static void main(String[] args) {
    System.out.println("정렬 전 : " + Arrays.toString(nums));
    mergeSort(0, nums.length-1);

    System.out.println("정렬 후 : " + Arrays.toString(nums));
  }
}