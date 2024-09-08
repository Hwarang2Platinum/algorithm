import java.util.*;
import java.io.*;

public class Main {
    static int k;
    static boolean isFirst;
    static String min, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        char[] seq = new char[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            seq[i] = st.nextToken().charAt(0);
        }
        isFirst = true;
        min=""; max = "";
        bt(0,new boolean[10], seq, "", 0);
        System.out.println(max);
        System.out.println(min);
    }

    public static void bt(int depth, boolean[] isv, char[] seq, String ans, int bef){
        if(depth == k+1){
            if(isFirst){
                min = ans;
                isFirst =false;
            }
            max = ans;
            return;
        }
        // 일단 넣어 그리고 넘겨줄 때 다음에 뭐가 들어가야 하는지에 대한 정보를 넘겨주자
        if(depth==0){
            for (int i =0; i <= 9 ; i++) {
                isv[i] = true;
                bt(depth+1,isv,seq,ans + i,i);
                isv[i] = false;
            }
            return;
        }
        if(seq[depth-1]=='<'){
            for (int i = bef+1; i <= 9 ; i++) {
                if(isv[i]) continue;
                isv[i] = true;
                bt(depth+1,isv,seq,ans + i,i);
                isv[i] = false;
            }
        }else{
            for (int i = 0; i < bef ; i++) {
                if(isv[i]) continue;
                isv[i] = true;
                bt(depth+1,isv,seq,ans + i,i);
                isv[i] = false;
            }
        }

    }
}