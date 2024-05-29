import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int T = Integer.parseInt(br.readLine());
       StringBuilder sb = new StringBuilder();
       for(int t=0;t<T;t++){
           String str = br.readLine();
           int start = 0; int end = str.length()-1;
           sb.append(checkPel(str, start, end, 0)+"\n");
       }
        System.out.println(sb.toString());
    }
    public static int checkPel(String str, int start, int end, int pseudo){
        int local = 2;
        if(start>end) return pseudo;

        if(str.charAt(start) == str.charAt(end)){
            local = Math.min(local,checkPel(str,++start,--end,pseudo));
        }else{
            if(pseudo==0){
                local = Math.min(local,checkPel(str,start+1,end,pseudo+1));
                local = Math.min(local,checkPel(str,start,end-1,pseudo+1));
            }
        }

        return local;
    }

}