import java.io.*;
import java.util.*;
/**
 * BOJ 1918 후위 표기식

 * 1. 피연산자는 바로바로 뽑기
 * 2. 연산자는 스택에 넣기
 *      - 피연산자를 모두 사용하면 스택을 pop
 *      - 피연산자를 넣을 때 연산자 우선순위가 스택의 탑보다 작으면 스택을 먼저 pop
 * 3. 여는 괄호 나오면 스택에 넣다가 닫는 괄호 나오면 스택 pop (여는괄호 나올때까지)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        StringBuilder ans = new StringBuilder();

        Map<Character, Integer> priority = new HashMap<>();
        priority.put('+',2);
        priority.put('-',2);
        priority.put('*',3);
        priority.put('/',3);
        priority.put('(',1);
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if(c=='-' || c=='+' || c=='*'|| c=='/'){
                while(!stack.isEmpty() && priority.get(c) <= priority.get(stack.peek())){
                    ans.append(stack.pop());
                }
                stack.add(c);
            } else if (c=='(') {
                stack.add(c);
            } else if (c==')') {
                while(stack.peek()!='('){
                    ans.append(stack.pop());
                }
                stack.pop();
            }else{
                ans.append(c);
            }
        }
        while (!stack.isEmpty()){
            ans.append(stack.pop());
        }
        System.out.println(ans);
    }

}