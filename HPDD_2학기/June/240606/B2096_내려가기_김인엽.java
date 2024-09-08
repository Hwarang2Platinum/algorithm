import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] dpMax = new int[3];
        int[] dpMin = new int[3];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] nums = new int[3];
            nums[0] = Integer.parseInt(st.nextToken());
            nums[1] = Integer.parseInt(st.nextToken());
            nums[2] = Integer.parseInt(st.nextToken());

            if(i == 0) {
                for(int j=0; j<3; j++) {
                    dpMax[j] = nums[j];
                    dpMin[j] = nums[j];
                }
            } else {
                int[] lastMax = dpMax.clone();
                int[] lastMin = dpMin.clone();
                for(int j=0; j<3; j++) { // 새로운 뎁스
                    int tmpMax = 0;
                    int tmpMin = Integer.MAX_VALUE;
                    for(int k=0; k<3; k++) { // 이전 뎁스
                        if(Math.abs(j-k) == 2) continue;
                        tmpMax = Math.max(tmpMax, lastMax[k]);
                        tmpMin = Math.min(tmpMin, lastMin[k]);
                    }
                    dpMax[j] = tmpMax + nums[j]; // 위에서 젤 큰거랑 현재꺼 더하기
                    dpMin[j] = tmpMin + nums[j];
                }
            }
        }

        System.out.println(Arrays.stream(dpMax).max().orElse(1000) + " " + Arrays.stream(dpMin).min().orElse(1000));
    }

}
