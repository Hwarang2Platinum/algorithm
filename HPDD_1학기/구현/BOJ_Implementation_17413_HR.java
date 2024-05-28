package com.DFS_BFS;
import java.util.*;
import java.io.*;

public class BOJ_Implementation_17413_HR {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String str = input.readLine();
		
		Deque<Deque<Character>> wholeQueue = new ArrayDeque<>();
		Deque<Character> tempList = new ArrayDeque<>();
		boolean isTag = false;
		
		for(int index = 0 ; index < str.length() ; index++) {
			
			if (str.charAt(index) == '<') {
				isTag = true;
				wholeQueue.add(tempList);
				tempList = new LinkedList<>();
			}
			if (str.charAt(index) == '>') {
				isTag = false;
				tempList.add(str.charAt(index));
				wholeQueue.add(tempList);
				tempList = new ArrayDeque<>();
				continue;
			}
			if (str.charAt(index) == ' ' & !isTag) {
				wholeQueue.add(tempList);
				tempList = new ArrayDeque<>();
				tempList.add(str.charAt(index));
				wholeQueue.add(tempList);
				tempList = new ArrayDeque<>();
				continue;
			}
			tempList.add(str.charAt(index));
		wholeQueue.add(tempList);
		}
		for (int i = 0 ; i < wholeQueue.size() ; i++) {
			System.out.println(wholeQueue.getFirst());
		}
		
//		while (!wholeQueue.isEmpty()) {
//			Deque<Character> SorQ = wholeQueue.poll();
//			if (SorQ == null)
//				continue;
//			if (SorQ instanceof LinkedList<?>) {
//				for(int i = 0 ; i < SorQ.size() ; i++)
//					System.out.printf("%c",SorQ.getFirst());
//			}
//			else {
//				for(int i = 0 ; i < SorQ.size() ; i++)
//					System.out.printf("%c",SorQ.getLast());
//			}
//		}
	}
}
