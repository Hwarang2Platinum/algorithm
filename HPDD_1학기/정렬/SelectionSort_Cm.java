package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SelectionSort_Cm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // N개 배열로 입력받기
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for (int i=0; i<N-1; i++){
            int min = Integer.MAX_VALUE;
            int minIndex = 0;

            for (int j = i; j < N; j++) {
                if(arr[j]<min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i]= min;
            arr[minIndex]= temp;      // O(N^2)
        }


        System.out.println(Arrays.toString(arr));


    }

}
