import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B9935_문자열폭발_김인엽 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String string = br.readLine();
        String toBomb = br.readLine();
        int len = toBomb.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            stack.push(string.charAt(i));

            if(stack.size() >= len) { // 길이가 toBomb이상이면 -> 탐색
                boolean isValid = true;
                for(int j=0; j<len; j++) {
                    if(stack.get(stack.size() - len + j) != toBomb.charAt(j)) {
                        isValid = false;
                        break;
                    }
                }

                if(isValid) {
                    for(int j=0; j<len; j++) stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Character c : stack) sb.append(c);
        if(stack.isEmpty()) bw.write("FRULA");
        else bw.write(sb.toString());
        bw.write("\n");
        bw.close();
    }
}
