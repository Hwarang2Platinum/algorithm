import java.io.*;
import java.util.*;

public class B1918_후위표기식_오화랑 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String expression = input.readLine();
        Stack<Character> expTemp = new Stack<>();
        char current;
        for (int i = 0; i < expression.length(); i++) {
            current = expression.charAt(i);
            if (current >= 65 && current <= 90) {
                sb.append(current);
                continue;
            }

            if (expTemp.isEmpty()) {
                expTemp.add(current);
                continue;
            }

            if (current == '(')
                expTemp.add(current);

            else if (current == ')') {
                while (expTemp.peek() != '(') {
                    sb.append(expTemp.pop());
                }
                expTemp.pop();
            }

            else if (current == '*' || current == '/') {
                while (!expTemp.isEmpty()) {
                    if (expTemp.peek() == '(') {
                        break;
                    } else if (expTemp.peek() == '+' || expTemp.peek() == '-') {
                        break;
                    } else {
                        sb.append(expTemp.pop());
                    }
                }
                expTemp.add(current);
            }

            else {
                while (!expTemp.isEmpty()) {
                    if (expTemp.peek() == '(') {
                        break;
                    }
                    sb.append(expTemp.pop());
                }
                expTemp.add(current);
            }
        }
        while (!expTemp.isEmpty()) {
            sb.append(expTemp.pop());
        }
        System.out.println(sb);
    }
}