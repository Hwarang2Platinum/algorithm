import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int zc = 0;
        int oc = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> mq = new PriorityQueue<>();
        int ans = 0;

        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a == 1) {
                oc += 1;
                continue;
            }
            if (a > 0) {
                pq.add(a);
            } else if (a < 0) {
                mq.add(a);
            } else {
                zc++;
            }
        }
        while (mq.size() >= 2) {
            ans += (mq.poll() * mq.poll());
        }
        while (pq.size() >= 2) {
            ans += (pq.poll() * pq.poll());
        }
        if (!mq.isEmpty() && zc > 0) {
            mq.poll();
        }
        System.out.println((mq.isEmpty() ? 0 : mq.poll()) + (pq.isEmpty() ? 0 : pq.poll()) + oc + ans);
        
    }
}