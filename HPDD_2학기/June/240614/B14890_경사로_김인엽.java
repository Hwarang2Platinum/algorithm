import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] arr;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        // 지도 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로 길 확인
        for (int i = 0; i < N; i++) {
            if (isPossible(i, 0, true)) {
                answer++;
            }
        }

        // 세로 길 확인
        for (int i = 0; i < N; i++) {
            if (isPossible(0, i, false)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
    // 모르겠어서 뤼튼한테 물어봤어요 ㅠㅠ
    static boolean isPossible(int x, int y, boolean isRow) {
        // 경사로 위치 추적
        boolean[] slope = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int current = isRow ? arr[x][y + i] : arr[x + i][y];
            int next = isRow ? arr[x][y + i + 1] : arr[x + i + 1][y];

            if (current == next) continue;

            if (Math.abs(current - next) > 1) return false;

            if (current > next) { // 내려가는 경사로
                for (int j = 1; j <= L; j++) {
                    // 1. 배열 범위 벗어나는 지 확인
                    // 2. 경사로를 놓으려는 위치 높이가 next와 다른지 확인 -> 다르면 못 놓음
                    // 3. 이미 경사로가 놓여져 있으면 false
                    if (i + j >= N || (isRow ? arr[x][y + i + j] : arr[x + i + j][y]) != next || slope[i + j]) {
                        return false;
                    }
                    slope[i + j] = true;
                }
            } else { // 올라가는 경사로
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || (isRow ? arr[x][y + i - j] : arr[x + i - j][y]) != current || slope[i - j]) {
                        return false;
                    }
                    slope[i - j] = true;
                }
            }
        }
        return true;
    }
}
