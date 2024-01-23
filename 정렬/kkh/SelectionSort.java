package algorithm;

public class SelectionSort {
	// 현재 인덱스부터 뒤에까지 검사해서 최소값을 현재인덱스와 바꿈
	public static void sort(int[] arr) {
		int min = 0;
		int min_idx = 0;
		int tmp = 0;
		
		for(int i=0;i<arr.length ; i++) {
			min = Integer.MAX_VALUE;
			min_idx = i;
			for (int j = i; j < arr.length; j++) {
				if(arr[j] < min) {
					min = arr[j];
					min_idx = j;
				}
			}
			tmp = arr[i];
			arr[i] = arr[min_idx];
			arr[min_idx] = tmp;
		}
	}
	
}
