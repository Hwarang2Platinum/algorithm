package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertionSort {
	// index 2부터 시작 하나 아래와 비교해서
	// 현재값이 더 크면 비교대상 idx+1에 저장 
	// 현재값이 더 작으면 비교대상의 idx --
	public static void sort(int[] arr) {
		int val = 0;
		int j = 0;
		for(int i = 1; i<arr.length ;i++) {
			val = arr[i];
			j = i-1;
			while(j>=0 && arr[j]>val) {
				int tmp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = tmp;
				j--;
			}
		}
	}
}
