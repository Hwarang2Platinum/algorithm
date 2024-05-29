import java.io.*;
import java.util.*;

public class BOJ14888_CM {
    static int N;
    static int[] arr; //숫자 저장
    static int[] operator; //연산자 저장 + - * / 개수
    static int Max = Integer.MIN_VALUE;
    static int Min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        operator = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
        op(arr[0],1);

        System.out.println(Max);
        System.out.println(Min);
    }

    public static void op (int num, int index) {
        if (index == N) { // N-1개 연산자 전부 사용시
            Max = Math.max(Max,num);
            Min = Math.min(Min, num);
            return;
        }

        for (int i = 0; i < 4; i++) { //연산자 다 넣어본다
            if(operator[i]>0){ //개수 체크
                if(i == 0){
                    operator[i]--; //연산자 사용
                    op(num+arr[index], index+1);
                    operator[i]++; // 연산자 복구
                }else if(i == 1){
                    operator[i]--;
                    op(num-arr[index], index+1);
                    operator[i]++;
                }else if(i == 2){
                    operator[i]--;
                    op(num*arr[index], index+1);
                    operator[i]++;
                }else {
                    operator[i]--;
                    op(num/arr[index], index+1);
                    operator[i]++;
                }

            }

        }


    }

}
