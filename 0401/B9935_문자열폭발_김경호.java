import java.util.*;
import java.io.*;
class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        String bStr = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            sb.append(target.charAt(i));
            if(sb.length()-bStr.length()>=0&&sb.substring(sb.length()-bStr.length(),sb.length()).equals(bStr)){
                sb.delete(sb.length()-bStr.length(),sb.length());
            }
        }
        if(sb.length()==0){
            System.out.println("FRULA");
            return;
        }
        System.out.println(sb);
    }

}
