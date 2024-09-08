import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2529_부등호_김인엽 {
    static int K;
    static char[] arr;
    static String max = "0";
    static String min = "9876543210";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        arr = new char[K];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력받기
        for(int i=0; i<K; i++) {
            char ch = st.nextToken().charAt(0);

            arr[i] = ch;
        }

        // 완탐 돌리기
        check();

        System.out.println(max);
        System.out.println(min);
    }

    static void check() {
        int flag = 0;

        for(int i=0; i<10; i++) {
            dfs(0, flag | (1 << i), ""+i, i); // 0번째 위치에 i 넣기
        }
    }

    static void dfs(int depth, int flag, String answer, int last) {
        if(depth == K) { // 끝까지 다 갔으면 확인
            if(Long.parseLong(answer) > Long.parseLong(max)) {
                max = answer;
            }
            if(Long.parseLong(answer) < Long.parseLong(min)) {
                min = answer;
            }
            return;
        }

        for(int i=0; i<10; i++) {
            if((flag & (1 << i)) != 0) continue; // 이미 넣은 거는 넘기기

            boolean isValid = true; // 부등호 조건 체크
            switch(arr[depth]) {
                case '<':
                    if(last > i) isValid = false;
                    break;
                case '>':
                    if(last < i) isValid = false;
                    break;
            }

            if(!isValid) continue;

            dfs(depth+1, flag | (1 << i), answer + i, i);
        }
    }
}