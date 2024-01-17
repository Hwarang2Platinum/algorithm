import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Greedy_1543_HR {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String word = input.readLine();
        String search = input.readLine();
        int start = 0; int count = 0;
        while (start <= word.length() - search.length()){
            if (search.equals(word.substring(start, start + search.length()))) {
                count++;
                start += search.length();
            }
            else
                start++;
        }
        System.out.println(count);
    }
}
