import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        TreeSet<Integer> ts = new TreeSet<Integer>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if(x==0) continue;
            ts.add(i);
        }

        int pointer = 0;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if(op == 1){
                int x = Integer.parseInt(st.nextToken()) - 1;
                if(ts.contains(x)){
                    ts.remove(x);
                }else {
                    ts.add(x);
                }
            } else if (op==2) {
                int x = Integer.parseInt(st.nextToken());
                x = x%N;
                if(pointer+x>=N){
                    pointer = (pointer+x) - N;
                }else{
                    pointer = pointer+x;
                }
            }else{
                if(ts.isEmpty()){
                    System.out.println(-1);
                }else{
                    if(ts.contains(pointer)) System.out.println(0);
                    else{
                        if(ts.higher(pointer) == null){
                            System.out.println(N-pointer+ts.first());
                        }else{
                            int m =ts.higher(pointer) ;
                            System.out.println(m-pointer);
                        }
                    }
                }
            }
        }

    }
}