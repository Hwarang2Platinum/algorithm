package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InsertionSort_Cm {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // N개 배열로 입력받기
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            int target = arr[i];
            int j=i-1;
            while (j >= 0 && arr[j] > target) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = target;
        }

        System.out.println(Arrays.toString(arr));



    }
}
