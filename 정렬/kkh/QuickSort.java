package algorithm;

import java.util.Arrays;

public class QuickSort {
	// pivot 잡기
	public static void sort(int[] arr) {
		
		quickSort(arr, 0 ,arr.length-1);
		
	}

	private static void quickSort(int[] arr, int l, int r) {
		int ll = l, rr = r;
		int pivot_idx = l;
		int pivot = arr[l++];
		// pivot 보다 작은 것들은 pivot왼쪽
		// pivot 보다 큰 것들은 pivot오른쪽으로
		while(l<r){
			while(l<r&&arr[r]>pivot) {
				r--;
			}
			// 작은값이 나오는 순간 종료
			while(l<r&&arr[l]<pivot) {
				l++;
			}
			// 큰 값이 나오는 순간 종료
			swap(arr,l,r);
		}
		swap(arr,l,pivot_idx);
		
		if(ll<l-1) quickSort(arr, ll, l-1);
		if(l+1<rr) quickSort(arr, l+1, rr);
		
	}

	private static void swap(int[] arr, int l, int r) {
		// TODO Auto-generated method stub
		int tmp = arr[l];
		arr[l] = arr[r];
		arr[r] = tmp;
	}
}
