import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            // System.out.println(st);
            for (int j = 0; j < N; j++){
                int k = Integer.parseInt(st.nextToken());
                if (pq.size() < N)
                pq.add(k);
                else if (pq.peek() < k){
                    pq.poll();
                    pq.add(k);
                }
            }
        }
        System.out.println(pq.peek());
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}