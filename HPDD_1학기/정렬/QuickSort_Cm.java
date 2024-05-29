package org.example;

import java.io.*;
import java.util.*;


public class QuickSort_Cm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        QuickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));

    }
    // i, j가 크로스해서 지나가면 피벗과 작은값 교체  아니라면 그냥 ij 둘이 교체
    public static void QuickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = lo;
        int i = lo + 1;
        int j = hi;
        int temp;

        while (i <= j) {
            while (arr[i] <= arr[pivot] && i <= hi) {
                i++;
            }
            while (arr[j] >= arr[pivot] && j > lo) {
                j--;
            }
            if (i > j) { // i와 j 가 엇갈리면 피벗과 교체
                temp = arr[j];
                arr[j] = arr[pivot];
                arr[pivot] = temp;
            } else { // 엇갈리지 않았으면 i,j 끼리 교체
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        QuickSort(arr, lo, j - 1);
        QuickSort(arr, j + 1, hi);
    }
}
