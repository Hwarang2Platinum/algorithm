package algorithm;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {10,3,7,2,8,4,1,1};
		SelectionSort.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		int[] arr2 = {10,3,7,2,8,4,1,1};
		InsertionSort.sort(arr2);
		System.out.println(Arrays.toString(arr2));
		
		int[] arr3 = {10,3,7,2,8,4,1,1};
		BubbleSort.sort(arr3);
		System.out.println(Arrays.toString(arr3));
		
		int[] arr4 = {10,3,7,2,8,4,1,1};
		MergeSort.sort(arr4);
		System.out.println(Arrays.toString(arr4));
		
		int[] arr5 = {10,3,7,2,8,4,1,1};
		QuickSort.sort(arr5);
		System.out.println(Arrays.toString(arr5));
	}
}
