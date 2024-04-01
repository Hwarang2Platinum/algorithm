import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B9935_문자열폭발_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String li = br.readLine();
        String boom = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= li.length(); i++) {
            if (sb.length() >= boom.length()) {
                int s = sb.length() - boom.length();
                int e = sb.length();
                if (sb.substring(s, e).equals(boom))
                    sb.delete(s, e);
            }
            if (i == li.length()) continue;
            sb.append(li.charAt(i));
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}