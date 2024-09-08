package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B6588 {
	static final int max = 1000000;
	static BufferedReader br = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] isNotPrime = new boolean [max+1];
		for (int i = 2; i*i <= max; i++) {
			if (!isNotPrime[i]) {
				for (int j = i*i; j <= max; j += i) {
					isNotPrime[j] = true;
				}
			}
		}
		
		while (true) {
			boolean isGoldbach = false;
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			for (int i = 2; i<= n/2; i++) {
				if (!isNotPrime[i] && !isNotPrime[n-i]) {
					System.out.println(n + " = " + i + " + " + (n - i));
					isGoldbach = true;
					break;
				}
			}
			if (!isGoldbach) System.out.println("Goldbach's conjecture is wrong.");
		}
	}
}
