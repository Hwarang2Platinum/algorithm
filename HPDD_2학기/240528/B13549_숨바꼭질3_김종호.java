import java.io.*;
import java.util.*;


/**
 * Main
 */
public class Main {

    static int N, X;
    static Queue<Pair> q;
    static boolean[] memo;

    public class Pair{
        int data, count;

        Pair(int d, int c){
            data = d;
            count = c;
        }
    }
    
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        // 방문한 지역이었다면 더이상 체크하지 않기 위해 
        memo = new boolean[100000 + 1];

        q = new ArrayDeque<>();
        q.add(new Pair(N, 0));
        while (true){
            Pair p = q.poll();
            int data = p.data;
            int count = p.count;
            // 방문 체크
            if (memo[data])
                continue;
            memo[data] = true;
            if (data == X){
                System.out.println(count);
                return;
            }
            if (data > 0){
                if (!memo[data - 1])
                    q.add(new Pair(data - 1, count + 1));
            }
            if (data <= 50000){
                if (!memo[data * 2])
                    q.add((new Pair(data * 2, count)));
            }
            if (data < 100000){
                if (!memo[data + 1])
                    q.add(new Pair(data + 1, count + 1));
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}