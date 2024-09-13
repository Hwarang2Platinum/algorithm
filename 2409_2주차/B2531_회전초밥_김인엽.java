import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 슬라이딩 윈도우를 위한 준비
        int[] sushiCnt = new int[d + 1]; // 초밥의 종류별 카운트 배열
        int uniqueSushi = 0; // 현재 먹고 있는 초밥 종류의 수
        int maxSushi = 0; // 먹을 수 있는 초밥 종류의 최댓값

        // 처음 k개의 초밥을 선택
        for (int i = 0; i < k; i++) {
            if (sushiCnt[arr[i]] == 0) uniqueSushi++; // 새로운 종류면 종류 수 증가
            sushiCnt[arr[i]]++;
        }

        // 쿠폰 초밥 고려
        maxSushi = sushiCnt[c] == 0 ? uniqueSushi + 1 : uniqueSushi;

        // 슬라이딩 윈도우
        for (int i = 1; i < N; i++) {
            // 이전 초밥 제거
            int removeSushi = arr[i - 1];
            sushiCnt[removeSushi]--;
            if (sushiCnt[removeSushi] == 0) uniqueSushi--; // 제거로 인해 종류가 사라지면 감소

            // 새 초밥 추가
            int addSushi = arr[(i + k - 1) % N];
            if (sushiCnt[addSushi] == 0) uniqueSushi++; // 새로운 종류면 종류 수 증가
            sushiCnt[addSushi]++;

            // 최대 갱신
            int currentSushi = sushiCnt[c] == 0 ? uniqueSushi + 1 : uniqueSushi;
            maxSushi = Math.max(maxSushi, currentSushi);
        }

        // 결과 출력
        System.out.println(maxSushi);
    }

}