import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    
    static int N, d, k, c, ans;
    static int[] arr;
    static HashMap<Integer, Integer> map = new HashMap<>();



    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N + k - 1; i++){
            int x = arr[i % N];
            map.putIfAbsent(x , 0);
            map.put(x, map.get(x) + 1);
            if (i < k - 1)
                continue;
            if (i >= k){
                int prev = arr[(i - k) % N];
                map.put(prev, map.get(prev) - 1);
                if (map.get(prev) == 0){
                    map.remove(prev);
                }
            }
            int size = map.keySet().size();
            if (!map.containsKey(c)){
                size++;
            }
            ans = Math.max(ans, size);
           
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}