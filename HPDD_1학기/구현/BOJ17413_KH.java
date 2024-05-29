package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//문자열 S가 주어졌을 때, 이 문자열에서 단어만 뒤집으려고 한다.
//
//먼저, 문자열 S는 아래와과 같은 규칙을 지킨다.
//
//알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
//문자열의 시작과 끝은 공백이 아니다.
//'<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
//태그는 '<'로 시작해서 '>'로 끝나는 길이가 3 이상인 부분 문자열이고, '<'와 '>' 사이에는 알파벳 소문자와 공백만 있다.
//단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다. 
//태그는 단어가 아니며, 태그와 단어 사이에는 공백이 없다.


public class BOJ17413_KH {
	// 단어구분은 공백 혹은태
	// 단어의 종류가 두개 
	// 1. 일반단어 
	// 2. 태그단어 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String[] strArr = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		
//		for(int i=0;i<strArr.length;i++) {
		char[] chs = br.readLine().toCharArray();
		for(int j=0; j<chs.length;j++) {
			System.out.print(chs[j]);
			if(chs[j]=='<') {
				System.out.println("1");
				while(true) {
					if(j== chs.length-1)break;
					System.out.println("1-inwhile:"+chs[j]);
					sb.append(chs[j]);
					if(chs[j++]=='>') {
						j--;
						break;
					}
				}
			}else {
				System.out.println("2");
				Stack<Character> stackChar = new Stack<>();
				while(true) {
					if(j== chs.length-1) {
						while(!stackChar.isEmpty()) {
							sb.append(stackChar.pop());
						}
						break;
					}
					System.out.println("2-inwhile:"+chs[j]);
					if(chs[j]==' ') {
						while(!stackChar.isEmpty()) {
							sb.append(stackChar.pop());
						}
						sb.append(' ');
						break;
					}
					if(chs[j]=='<') {
						j--;
						while(!stackChar.isEmpty()) {
							sb.append(stackChar.pop());
						}
						break;
					}
					stackChar.add(chs[j++]);
				}
				
			}
		}
		sb.append(" ");
//		}
		
		System.out.println(sb.toString());
//		Stack<Character> stackChar = new Stack<>();
//		Stack<Character> tmpStack = new Stack<>();
//		Queue<Character> queue = new LinkedList();
		

	}

}
