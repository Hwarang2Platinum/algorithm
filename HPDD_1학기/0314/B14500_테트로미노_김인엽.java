package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14500_테트로미노_김인엽 {
    static int[][] arr; // 맵
    static int N, M; // 가로 세로
    static int answer; // 정답

//    static int[] dx = {0, 0, 1, -1};
//    static int[] dy = {1, -1, 0, 0};
//    static int[][] dxOh = {{0, 0, 1}, {0, 0, -1}, {1, 1, -1}, {1, 1, -1}};
//    static int[][] dyOh = {{1, 1, -1}, {1, 1, -1}, {0, 0, -1}, {0, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        // 맵 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Tetromino[] tetrominos = {new Skyblue(), new Green(), new Orange(), new Yellow(), new Purple()};
        // 모든 칸에 대해 모든 테트로미노 경우의 수 확인
//        boolean[][] visited = new boolean[N][M]; // 이거 안써서 시초남
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (Tetromino tetromino : tetrominos) {
                    tetromino.check(i, j);
                }
                // 원래 visited를 계속 만들어줬는데 이때 시간이 오래걸리나..?
//                visited[i][j] = true;
//                dfs(i, j, 0, 0, visited);
//                visited[i][j] = false; // 이거랑
//
//                loopOh(i, j);
            }
        }
        System.out.println(answer);
    }

//    static void loopOh(int x, int y) {
//        for (int i = 0; i < dxOh.length; i++) {
//            int sum = arr[x][y];
//            boolean isValid = true;
//            int cx = x, cy = y;
//            for (int j = 0; j < dxOh[i].length; j++) {
//                cx += dxOh[i][j];
//                cy += dyOh[i][j];
//                if (cx < 0 || cx >= N || cy < 0 || cy >= M) {
//                    isValid = false;
//                    break;
//                }
//                sum += arr[cx][cy];
//            }
//            if (isValid) answer = Math.max(answer, sum);
//        }
//    }
//
//    static void dfs(int x, int y, int sum, int cnt, boolean[][] visited) {
//        if (cnt == 4) {
//            answer = Math.max(answer, sum);
//            return;
//        }
//        for (int i = 0; i < dx.length; i++) {
//            int cx = x + dx[i];
//            int cy = y + dy[i];
//            // 범위 벗어나면 패스
//            if (cx < 0 || cx >= N || cy < 0 || cy >= M) continue;
//            // 방문했으면 패스
//            if (visited[cx][cy]) continue;
//            // 이게 백트래킹..?
//            visited[cx][cy] = true;
//            dfs(cx, cy, sum + arr[cx][cy], cnt + 1, visited);
//            visited[cx][cy] = false;
//        }
//
//    }

    static class Tetromino {
        int[][] dxs;
        int[][] dys;

        public Tetromino(int[][] dxs, int[][] dys) {
            this.dxs = dxs;
            this.dys = dys;
        }

        public void check(int x, int y) {
            for (int i = 0; i < dxs.length; i++) {
                int sum = arr[x][y]; // 초기값(현재 칸)
                int cx = x;
                int cy = y;
                boolean isValid = true;
                for (int j = 0; j < dxs[i].length; j++) {
                    cx += dxs[i][j];
                    cy += dys[i][j];
                    // 범위 벗어나면 끝
                    if (cx < 0 || cx >= N || cy < 0 || cy >= M) {
                        isValid = false;
                        break;
                    }
                    // sum 추가
                    sum += arr[cx][cy];
                }
                if (isValid)
                    answer = Math.max(answer, sum);
            }
        }
    }

    static class Skyblue extends Tetromino {
        // 우 -> 우 -> 우
        // 하 -> 하 -> 하
        public Skyblue() {
            super(new int[][]{{0, 0, 0}, {1, 1, 1}}, new int[][]{{1, 1, 1}, {0, 0, 0}});
        }
    }

    static class Yellow extends Tetromino {
        // 우 하 좌
        public Yellow() {
            super(new int[][]{{0, 1, 0}}, new int[][]{{1, 0, -1}});
        }
    }

    static class Orange extends Tetromino {
        // 우 -> 우 -> 상/하
        // 하 -> 하 -> 우
        // 우 -> 하 -> 하
        // 우 -> 상 -> 상
        // 하 -> 우 -> 우
        // 상 -> 상 -> 우
        // 상 -> 우 -> 우
        public Orange() {
            super(
                    new int[][]{{0, 0, -1}, {0, 0, 1}, {1, 1, 0}, {0, 1, 1}, {0, -1, -1}, {1, 0, 0}, {-1, -1, 0}, {-1,0,0}},
                    new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}, {1, 0, 0}, {1, 0, 0}, {0, 1, 1}, {0, 0, 1}, {0,1,1}}
            );
        }
    }

    static class Green extends Tetromino {
        // 우 -> 상/하 -> 우
        // 하 -> 좌/우 -> 하
        public Green() {
            super(
                    new int[][]{{0, -1, 0}, {0, 1, 0}, {1, 0, 1}, {1, 0, 1}},
                    new int[][]{{1, 0, 1}, {1, 0, 1}, {0, -1, 0}, {0, 1, 0}}
            );
        }
    }

    static class Purple extends Tetromino {
        // 우 -> 우 -> 왼쪽(아래/위) 대각선
        // 하 -> 하 -> (좌/우)측 상단 대각선
        public Purple() {
            super(
                    new int[][]{{0, 0, 1}, {0, 0, -1}, {1, 1, -1}, {1, 1, -1}},
                    new int[][]{{1, 1, -1}, {1, 1, -1}, {0, 0, -1}, {0, 0, 1}}
            );
        }
    }
}