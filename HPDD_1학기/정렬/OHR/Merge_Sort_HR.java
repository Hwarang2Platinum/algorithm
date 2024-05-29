import java.util.*;
import java.io.*;

public class Merge_Sort_HR {
	public static void main(String[] args) throws IOException {
//		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer ST = new StringTokenizer(input.readLine());
//        ArrayList<Integer> num_list = new ArrayList<>();
//
//        while(ST.hasMoreTokens()){
//            num_list.add(Integer.parseInt(ST.nextToken()));
//        }
		ArrayList<Integer> num_list = SortHelper.RandomNumListGen2(1000);
        System.out.println(num_list.toString());
        
        mergeSort(num_list, 0, num_list.size() - 1);
        System.out.println(num_list.toString());
	}
	
	public static void mergeSort(ArrayList<Integer> num_list, int start, int end) { // 0 ~ 9 => 0 ~ 4 / 5 ~ 9
		if (start < end) {
			int mid = (end + start)/2; // 항상 floor value
			mergeSort(num_list, start, mid);
			mergeSort(num_list, mid + 1, end);
			merge(num_list, start, mid, end);
		}
	}
	
	public static void merge(ArrayList<Integer> num_list, int start, int mid, int end) { 
		int left_num = mid - start + 1; // 5
		int right_num = end - mid; // 5
		
		ArrayList<Integer> left_list = new ArrayList<>();
		ArrayList<Integer> right_list = new ArrayList<>();
		
		for (int indexL = 0 ; indexL < left_num ; indexL++)
			left_list.add(num_list.get(start + indexL));
		for (int indexR = 0 ; indexR < right_num ; indexR++)
			right_list.add(num_list.get(mid + indexR + 1));
		
		int indexL = 0 ; int indexR = 0;
		while(indexL < left_num && indexR < right_num) { // 0 1 2 3 4 / 5 6 7 8 9 
			if (left_list.get(indexL) <= right_list.get(indexR)) {
				num_list.set(start, left_list.get(indexL));
				indexL++;
			}
			else {
				num_list.set(start, right_list.get(indexR));
				indexR++;
			}
			start++;
		}
		while(indexL < left_num) {
			num_list.set(start, left_list.get(indexL));
			start++;
			indexL++;
		}
		while(indexL < left_num) {
			num_list.set(start, right_list.get(indexR));
			start++;
			indexR++;
		}
	}
}
