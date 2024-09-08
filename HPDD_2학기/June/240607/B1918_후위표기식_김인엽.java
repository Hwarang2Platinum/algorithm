import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> stack = new Stack<>();

        Map<Character, Integer> priority = new HashMap<>();
        // 곱/나눗셈이 더 높은 우선순위
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('(', 0);

        String string = br.readLine();
        for(int i=0; i<string.length(); i++) {
            Character ch = string.charAt(i);
            if(ch >= 'A' && ch <= 'Z') { // 알파벳 대문자가 들어온다면
                bw.write(ch+""); // 쓰기
            } else if(ch == '(') { // 괄호 시작이면
                stack.add(ch); // 스택에 넣기
            } else if(ch == ')') { // 괄호 끝나면
                while(true) {
                    Character tmp = stack.pop(); // 스택 팝
                    if(tmp == '(') break; // 괄호 시작까지 계속 팝
                    bw.write(tmp+""); // 쓰기
                }
            } else { // 표기식이면
                while(!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(ch)) {
                    bw.write(stack.pop() + "");
                }
                stack.add(ch);
            }
        }
        while(!stack.isEmpty()) { // 스택에 남아있는거 처리
            bw.write(stack.pop()+"");
        }
        bw.close();
    }

}
