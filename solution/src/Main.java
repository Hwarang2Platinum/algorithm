import java.util.*;
import java.io.*;

public class Main {
    static int N, S;
    static long ans;
    static int[] nums;
    static Map<Integer,Integer> map;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        rightPowerSet(N/2,0);
        leftPowerSet(0,0);

        System.out.println(S==0?ans-1:ans);

    }

    static void rightPowerSet(int start, int sum){
        if(map.containsKey(sum)){
            map.put(sum, map.get(sum)+1);
        }else{
            map.put(sum,1);
        }

        for (int i = start; i < N; i++) {
            rightPowerSet(i+1, sum+nums[i]);
        }
    }
    static void leftPowerSet(int start, int sum){
        if(map.containsKey(S-sum)){
            ans += map.get(S-sum);
        }
        for (int i = start; i < N/2; i++) {
            leftPowerSet(i+1, sum+nums[i]);
        }
    }
}
