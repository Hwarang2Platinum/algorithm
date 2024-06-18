import java.math.BigDecimal;
import java.util.*;
import java.io.*;

public class B14488_준오는급식충이야_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        BigDecimal T = new BigDecimal(st.nextToken());
        st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        Pair[] stu = new Pair[N];
        for (int i = 0; i < N; i++) {
            stu[i] = new Pair(new BigDecimal(st.nextToken()), new BigDecimal(st1.nextToken()));
        }
        Arrays.sort(stu);
        if (N == 1) {
            System.out.println(1);
            return;
        }

        BigDecimal currmin = stu[0].n.add(stu[0].v.multiply(T));

        for (int i = 1; i < N; i++) {
            BigDecimal curr = stu[i].n.add(stu[i].v.multiply(T));
            BigDecimal currm = stu[i].n.subtract(stu[i].v.multiply(T));
            if (currmin.compareTo(currm) < 0) {
                System.out.println(0);
                return;
            }
            if (curr.compareTo(currmin) < 0) {
                currmin = curr;
            }
        }
        System.out.println(1);
    }

    static class Pair implements Comparable<Pair> {
        BigDecimal n, v;
        public Pair(BigDecimal n, BigDecimal v) {
            this.n = n;
            this.v = v;
        }

        @Override
        public int compareTo(Pair o) {
            return (n.subtract(o.n)).intValue();
        }
    }
}
