import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static Stack<Character> stack;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 2);
        map.put(')', 2);
        map.put('*', 1);
        map.put('/', 1);
        map.put('+', 0);
        map.put('-', 0);
        stack = new Stack<>();

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 피연산자일 경우
            if (Character.isAlphabetic(c)) {
                // 그냥 출력
                sb.append(c);
                continue;
            }
            // 연산자 일 경우
            // 스택에 아무것도 없으면
            if (stack.isEmpty()) {
                stack.add(c);
                continue;
            }
            // 괄호가 닫히면 안에 있는 애들은 다 넣어준다.
            if (c == ')') {
                while (stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                // ( 도 빼줌
                stack.pop();
                continue;
            }
            // 연산자 우선 순위
            int k = map.get(c);
            // 스택 안에 있는 자신보다 같거나 높은 연산자들은 뽑는다. (괄호 빼고)
            while (!stack.isEmpty() && map.get(stack.peek()) >= k && stack.peek() != '(') {
                sb.append(stack.pop());
            }
            stack.add(c);
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}