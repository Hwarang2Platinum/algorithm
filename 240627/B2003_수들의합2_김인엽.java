import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int currentSum = 0;
        int answer = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);

        for(int num : nums) {
            currentSum += num;
            if(countMap.containsKey(currentSum - M)) {
                answer += countMap.get(currentSum - M);
            }
            countMap.put(currentSum, countMap.getOrDefault(currentSum, 0) + 1);
        }
        System.out.println(answer);
    }
}
