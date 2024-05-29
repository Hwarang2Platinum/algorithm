import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayList<Belt> li = new ArrayList<>();

        for(int i = 0; i < 2*N; i++) {
            li.add(new Belt(Integer.parseInt(st.nextToken())));
        }

        int time = 0;
        int upidx = 0;

        while (true) {
            upidx = (upidx == 0 ? 2*N-1 : upidx-1);

            time++; // 회전한다
            int downIndx = (upidx + N - 1) % (2 * N);

            li.get(downIndx).robot = false;
            // 로봇 이동
            for(int i = 0; i < N; i++) {
                int idx = (upidx + N - i) % (2 * N);
                Belt belt = li.get(idx);
                Belt next = li.get((idx + 1) % (2 * N));
                if (belt.robot && !next.robot && next.dur > 0) {
                    next.dur--;
                    next.robot = true;
                    belt.robot = false;
                }
            }

            li.get(downIndx).robot = false;
            if (li.get(upidx).dur > 0) {
                li.get(upidx).robot = true;
                li.get(upidx).dur--;
            }

            int zeroCount = 0;
            for (Belt belt : li) {
                zeroCount += belt.dur == 0 ? 1 : 0;
            }
            if (zeroCount >= K) { break;}
        }
        System.out.println(time);
    }

    static class Belt {
        boolean robot;
        int dur;
        Belt(int a) {
            dur = a;
        }

        @Override
        public String toString() {
            return " " + dur + " " + (!robot ? "X" : "O");
        }
    }
}
