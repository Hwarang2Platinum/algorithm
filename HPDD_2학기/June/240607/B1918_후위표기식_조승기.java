import java.util.*;
import java.io.*;

public class B1918_후위표기식_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> dic = new HashMap<Character, Integer>() {{
            put('(', 0); put(')', 0);
            put('*', 2); put('/', 2);
            put('+', 1); put('-', 1);
        }};
        String line = br.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char c: line.toCharArray()) {
            if (!dic.containsKey(c)) { // A to Z
                sb.append(c);
                continue;
            }

            if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                stack.pop();
                continue;
            }

            if (c == '(' || stack.isEmpty()) {
                stack.push(c);
                continue;
            }

            while (!stack.isEmpty() && dic.get(stack.peek()) >= dic.get(c))
                sb.append(stack.pop());
            stack.push(c);
        }

        while (!stack.isEmpty()){
            if (stack.peek() != '(')
                sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}
