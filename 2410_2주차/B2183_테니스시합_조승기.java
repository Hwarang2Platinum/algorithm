import java.util.*;
import java.io.*;
public class B2183_테니스시합_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        String line = st.nextToken();
        System.out.println(line.charAt(line.length() - 1));
    }
}
