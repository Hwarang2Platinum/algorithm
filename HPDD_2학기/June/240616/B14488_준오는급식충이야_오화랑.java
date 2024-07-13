import java.io.*;
import java.util.*;

// Problem

// 준오와 친구들은 매일 복도 어딘가의 한 지점에 모여서 함께 밥을 먹으러 간다.
// 종이 울리면 준오와 친구들은 복도 어딘가의 지점에서 만나야 한다.
// 홍수가 밀려오면, 함께 밥을 먹으러 갈 수 없음
// 홍수가 밀려올 시간과 동시에 만나면 같이 먹으러 갈 수 있는 것으로 간주
// N : 친구들의 수 ( 50000 )
// T : 소수점 4째자리의 실수 ( 1 ~ 1_000_000_000)
// 둘째 줄 : N명 학생들의 위치 ( 1 ~ 1_000_000_000) 미터단위
// 셋째 줄 : N명 학생들의 속도 ( 1 ~ 1_000_000_000) 초당 속도
// 만나면 1, 만날 수 없으면 0
// 범위가 너무 크다..

public class B14488_준오는급식충이야_오화랑 {
    static class Solution {
        int N;
        double T;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st1 = new StringTokenizer(input.readLine());
            StringTokenizer st2 = null;
            this.N = Integer.parseInt(st1.nextToken());
            this.T = Double.parseDouble(st1.nextToken());

            st1 = new StringTokenizer(input.readLine());
            st2 = new StringTokenizer(input.readLine());
            int location, speed;
            double start = Double.MIN_VALUE;
            double end = Double.MAX_VALUE;
            for (int i = 0; i < this.N; i++) {
                location = Integer.parseInt(st1.nextToken());
                speed = Integer.parseInt(st2.nextToken());

                start = Math.max(start, location - this.T * speed);
                end = Math.min(end, location + this.T * speed);
            }
            if (start < end + 1e-08)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
