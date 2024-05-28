package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17779_게리맨더링2_김인엽 {
    static int N; // 재현시 크기
    static int[][] arr; // 재현시
    static int answer = Integer.MAX_VALUE; // 정답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        StringTokenizer st;
        // 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 안쪽 하나씩 (r,c)로 두고 시도해보기
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        geryMandering(r, c, d1, d2);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static void geryMandering(int x, int y, int d1, int d2) {
        if (x + d1 + d2 > N || y + d2 > N || y - d1 < 1) return;

        boolean[][] visited = new boolean[N + 1][N + 1];
        // 1번~5번 선거구
        int one = 0, two = 0, three = 0, four = 0, five = 0;

        // 경계선 긋기
        for (int i = 0; i <= d1; i++) {
            visited[x + i][y - i] = true;
            visited[x + d2 + i][y + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            visited[x + i][y + i] = true;
            visited[x + d1 + i][y - d1 + i] = true;
        }

        for (int r = x+1; r < x+d1+d2; r++) {
            boolean isVisit = false;
            for (int c = 1; c <= N; c++) {
                if (visited[r][c]) {
                    isVisit = !isVisit;
                    continue;
                }
                if (isVisit) {
                    visited[r][c] = true;
                }
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (visited[r][c]) {
                    five += arr[r][c];
                } else if (r < x + d1 && c <= y) {
                    one += arr[r][c];
                } else if (r <= x + d2 && c > y && c <= N) {
                    two += arr[r][c];
                } else if (r >= x + d1 && r <= N && c < y - d1 + d2) {
                    three += arr[r][c];
                } else if (r > x + d2 && r <= N && c >= y - d1 + d2 && c <= N) {
                    four += arr[r][c];
                }
            }
        }

        // 만약 0인 선거구가 있으면 끝
        if (one * two * three * four * five == 0) return;

        int[] list = new int[]{one, two, three, four, five};

        answer = Math.min(answer, Arrays.stream(list).max().orElse(Integer.MAX_VALUE) - Arrays.stream(list).min().orElse(Integer.MAX_VALUE));
    }
}

