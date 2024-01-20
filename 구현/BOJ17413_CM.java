import java.io.*;
import java.util.*;


public class BOJ17413_CM {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String str = br.readLine();
        int len = str.length();

        boolean tag = false;
        for(int i=0; i<len; i++) {

            if(str.charAt(i) == '<') {
                tag = true;

                while( !stack.isEmpty() ) {
                    sb.append(stack.pop()); //스택이용해서 뒤집어서 넣기
                }

                sb.append(str.charAt(i));
            }
            else if(str.charAt(i) == '>') {
                tag = false;
                sb.append(str.charAt(i)); //태그 안은 뒤집으면 안돼~
            }
            else if(tag == true) {
                sb.append(str.charAt(i));  //태그 안쪽-> 그대로 넣기
            }
            else if(tag == false) {
                if(str.charAt(i) == ' ') {  //태그 바깥쪽-> 공백기준 뒤집어서 넣기

                    while( !stack.isEmpty() ) {
                        sb.append(stack.pop());
                    }

                    sb.append(str.charAt(i));
                }
                else {
                    stack.push(str.charAt(i));
                }
            }
        }

        while( !stack.isEmpty() ) { //마지막 다 털기
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

}
