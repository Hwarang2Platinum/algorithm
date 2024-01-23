import java.util.Arrays;

// 퀵 정렬
public class QuickSort {
  static int[] array = {5,7,9,0,3,1,6,2,4,8};
  public static void quickSort(int start, int end) {
    // 원소가 한개인 경우 종료
    if(start >= end) return;
    // 첫 인덱스를 피봇으로 설정
    int pivot = start;
    // 왼쪽은 피봇 다음꺼부터 설정
    int left = start + 1;
    // 오른쪽은 마지막
    int right = end;
    // 왼쪽은 pivot보다 작은 값, 오른쪽은 pivot보다 큰값
    // 이제 왼쪽, 오른쪽에서 하나씩 좁혀가면서 교차될 때까지
    while(left <= right) {
      // pivot보다 큰 값이 나올 때까지 왼쪽 인덱스 증가
      while(left<=end && array[left]<=array[pivot]) left++;
      // pivot보다 작은 값이 나올 때까지 오른쪽 인덱스 감소
      while(right>start && array[right]>=array[pivot]) right--;

      // 만약 엇갈린 상태라면, pivot과 right 값 교체
      if(left > right) {
        int tmp = array[right];
        array[right] = array[pivot];
        array[pivot] = tmp;
      } else { // 엇갈리지 않았다면, left, right 교체
        int tmp = array[right];
        array[right] = array[left];
        array[left] = tmp;
      }
    }
    // pivot이 들어간 위치인 right는 제 자리 찾았음
    // 해당 위치 왼편은 pivot보다 작은값 -> quickSort시작
    // 해당 위치 오른편은 pivot보다 큰값 -> quickSort시작
    quickSort(start, right - 1);
    quickSort(right+1, end);
  }

  public static void main(String[] args) {
    System.out.println("변경 전 : " + Arrays.toString(array));

    quickSort(0, array.length-1);

    System.out.println("변경 후 : " + Arrays.toString(array));

  }

}