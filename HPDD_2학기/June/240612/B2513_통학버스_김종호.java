import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    // N = 아파트 단지 수, K = 통학버스 정원, S = 학교 위치
    static int N, K, S, ans;

    /**
     * Pair
     */
    public class Pair implements Comparable<Pair> {
        int dist, count;

        Pair(int dist, int count) {
            this.dist = dist;
            this.count = count;
        }

        public int compareTo(Pair p) {
            return Integer.compare(p.dist, dist);
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        PriorityQueue<Pair> plus, minus;
        plus = new PriorityQueue<>();
        minus = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            if (pos > S) {
                plus.add(new Pair(pos - S, count));
            } else if (pos < S) {
                minus.add(new Pair(S - pos, count));
            }
        }
        while (!minus.isEmpty()) {
            int cap = K;
            // 제일 먼곳부터 들러서 쭉 돌아옴
            ans += minus.peek().dist * 2;
            while (!minus.isEmpty()) {
                Pair cur = minus.poll();
                // 태울수 있는 사람보다 태울 사람이 많으면
                if (cur.count >= cap) {
                    // 태울만큼 태우고 다시 출발
                    if (cur.count != cap)
                        minus.add(new Pair(cur.dist, cur.count - cap));
                    break;
                }
                // 더 태울 수 있으면
                cap -= cur.count;
            }
        }
        while (!plus.isEmpty()) {
            int cap = K;
            // 제일 먼곳부터 들러서 쭉 돌아옴
            ans += plus.peek().dist * 2;
            while (!plus.isEmpty()) {
                Pair cur = plus.poll();
                // 태울수 있는 사람보다 태울 사람이 많으면
                if (cur.count >= cap) {
                    // 태울만큼 태우고 다시 출발
                    if (cur.count != cap)
                        plus.add(new Pair(cur.dist, cur.count - cap));
                    break;
                }
                // 더 태울 수 있으면
                cap -= cur.count;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}