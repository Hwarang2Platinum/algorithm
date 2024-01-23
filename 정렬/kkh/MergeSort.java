package algorithm;

public class MergeSort {
	public static void sort(int[] arr) {
		// 반으로 쪼갠다
		// 쪼갠 거를 합친다.(정렬하며)
		mergeSort(arr, 0, arr.length); 
		
	}
	public static void mergeSort(int[] arr, int l, int r) {
		if(l==r || l==r-1) return;
		int mid = (l+r) / 2;
		mergeSort(arr, l, mid);
		mergeSort(arr, mid, r);
		merge(arr,l,mid,r);
	}

	private static void merge(int[] arr, int l, int m, int r) {
		int r_start = m;
		int l_start = l;
		int[] tmp_arr = new int[r-l];
		int idx = 0;
		while(l_start<m && r_start<r) {
			if(arr[l_start]<arr[r_start]) {
				tmp_arr[idx++] = arr[l_start++];
			}else {
				tmp_arr[idx++] = arr[r_start++];
			}
		}
		while(l_start<m) {
			tmp_arr[idx++] = arr[l_start++];
		}
		while(r_start<m) {
			tmp_arr[idx++] = arr[r_start++];
		}
		for(int i=l; i<r; i++) {
			arr[i] = tmp_arr[i-l];
		}
	}
}
