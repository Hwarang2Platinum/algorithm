import java.io.*;
import java.util.Stack;

public class BOJ17413_IY {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Stack<String> word;

    // 뒤집기 함수
    public static void makeReverse() throws IOException {
        while(!word.empty()) {
            bw.write(word.pop());
        }
    }

    public static void main(String[] args) throws IOException {
        String strs = br.readLine();
        boolean isTag = false; // 현재 태그인지 아닌지 상태체크 플래그
        word = new Stack<>();

        for(String str: strs.split("")) {
            // < : bw에 write
            if(str.equals("<")) {
                // 이전까지 단어였다면, 뒤집기
                if(!isTag) {
                    makeReverse();
                }
                bw.write(str);
                // 현재 상태 : 태그
                isTag = true;
            } else if(str.equals(" ")) {
                if (!isTag) {
                    makeReverse();
                }
                // 띄어쓰기 넣기
                bw.write(str);
            } else if(str.equals(">")) {
                if(!isTag) {
                    makeReverse();
                }
                bw.write(str);
                // 태그 상태 종료
                isTag = false;
            } else {
                // 만약 태그라면 그대로 쓰기
                if(isTag) {
                    bw.write(str);
                } else {
                    // 아니면 스택에 넣기
                    word.push(str);
                }
            }
        }
        // 스택이 남았으면 뒤집기
        makeReverse();
        bw.close();
    }
}
