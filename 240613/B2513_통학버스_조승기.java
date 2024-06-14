import java.util.*;
import java.io.*;

public class B2513_통학버스_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        PriorityQueue<Pair> lq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.x, o2.x)));
        PriorityQueue<Pair> rq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.x, o1.x));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            Pair pair = new Pair(x, n);

            if (x < S) {
                lq.add(pair);
            } else {
                rq.add(pair);
            }
        }

        int ans = 0;

        while (!lq.isEmpty() || !rq.isEmpty()) {
            int remain = K;
            int current = S;
            int moved = 0;
            PriorityQueue<Pair> q = lq.isEmpty() ? rq : lq;

            while (!q.isEmpty() && remain > 0) {
                int[] data = compute(q, remain, current);
                moved += data[0];
                remain = data[1];
                current = data[2];
            }

            ans += moved + Math.abs(S - current);

        }
        System.out.println(ans);
    }

    static int[] compute(PriorityQueue<Pair> q, int remain, int current) { // return movedDistance, remainSeat, current
        Pair p = q.poll();
        int distance = Math.abs(p.x - current);
        if (remain < p.n) {
            q.add(new Pair(p.x, p.n - remain));
            return new int[]{distance, 0, p.x};
        }

        return new int[]{distance, remain - p.n, p.x};
    }

    static class Pair {
        int x, n;
        public Pair(int x, int n) {
            this.x = x;
            this.n = n;
        }
    }
}
