package Programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class P_영어끝말잇기_김인엽 {

  static public int[] solution(int n, String[] words) {
    int cycle = 0; // 차례
    int number = 0; // 번호
    Stack<String> stack = new Stack<>(); // 이전 단어들 모음
    for(int i=0; i<words.length; i++) {
      // 만약 이전에 나왔던 단어거나 / 끝말잇기가 아닐경우 break
      if(!stack.isEmpty() && (stack.contains(words[i]) || stack.peek().charAt(stack.peek().length()-1) != words[i].charAt(0))) {
        cycle = i / n + 1;
        number = (i+1) % n == 0 ? n : (i+1) % n;
        break;
      }
      stack.add(words[i]);
    }

    return new int[]{number, cycle};
  }

  public static void main(String[] args) {
  int n=3;  String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//    int n=5; String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
//    int n=2; String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
    System.out.println(Arrays.toString(solution(n, words)));
  }
}
