import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 	51016	636
public class B7579_앱_김인엽 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Memory[] memories = new Memory[N];

        // 메모리
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            memories[i] = new Memory(Integer.parseInt(st.nextToken()));
        }
        // 코스트
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            memories[i].cost = Integer.parseInt(st.nextToken());
        }
        int INF = 10_000_001;
        int[] dp = new int[M+1];
        int lastMemory = memories[0].memory; // 메모리 누적
        Arrays.fill(dp, INF); // 처음에 최댓값으로 채우기
        for(int j=0; j<=Math.min(lastMemory, M); j++) { // 처음 들어온 메모리 처리
            dp[j] = memories[0].cost;
        }
        for(int i=1; i<N; i++) { // 이후부터 들어오는 메모리 처리
            lastMemory += memories[i].memory; // 현재 들어온 메모리 누적
            for (int j = Math.min(lastMemory, M); j >= 0; j--) { // M과 메모리 누적값 중 min값안에서 반복
                if(j <= memories[i].memory) { // 현재 들어온 메모리보다 작다면, 현재 들어온 메모리의 코스트와 기존 값 비교
                    dp[j] = Math.min(dp[j], memories[i].cost);
                } else if(j <= lastMemory) { // 누적값보다 작다면, 현재 메모리 - 들어온 메모리 의 값과 현재 메모리 값 더한 값 과 기존 값 비교
                    dp[j] = Math.min(dp[j], dp[j-memories[i].memory] + memories[i].cost);
                }
            }
        }
        System.out.println(dp[M]);
    }

    static class Memory {
        int memory, cost;

        public Memory(int memory) {
            this.memory = memory;
        }

        public Memory(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }
    }
}
