package com.ssafy.day1.a_basic;

import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int n = sc.nextInt();
		for(int i=0; i<n; i++) {
			pq.add(sc.nextInt());
		}
		
		int ans = 0;
		
		while(pq.size() > 1) {
			int one = pq.poll();
			int two = pq.poll();
			int tmpSum = one + two;
			ans += tmpSum;
			pq.add(tmpSum);
		}
		
		System.out.println(ans);
	}

}
