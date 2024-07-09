import java.util.*;
import java.io.*;

public class B15651_N과M3_조승기 {
    static int N, K;
    static ArrayList<Integer> tmp = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static void find() {
        if (tmp.size() == K){
            for (int i = 0; i < K; i++) {
                sb.append(tmp.get(i) + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            tmp.add(i);
            find();
            tmp.remove(tmp.size() - 1);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        find();
        System.out.println(sb);
    }
}
