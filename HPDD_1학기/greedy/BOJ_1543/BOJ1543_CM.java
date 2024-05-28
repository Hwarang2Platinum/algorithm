import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1543_CM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String word = br.readLine();

        s = s.replaceAll(word,"1");
        int sum = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='1'){
                sum++;
            }
        }

        System.out.println(sum);
    }
}
