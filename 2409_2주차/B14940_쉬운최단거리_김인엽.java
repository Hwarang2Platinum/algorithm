import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        Pair dest = null;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 2) { // 목표지점
                    dest = new Pair(i, j);
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = num;
                }
            }
        }
        // bfs
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(dest); // 목표지점 넣기
        visited[dest.x][dest.y] = true;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while(!queue.isEmpty()) {
            Pair current = queue.poll();

            for(int i = 0; i < 4; i++) {
                int cx = current.x + dx[i];
                int cy = current.y + dy[i];
                if(cx < 0 || cx >= N || cy < 0 || cy >= M) { // 영역 검사
                    continue;
                }
                if(visited[cx][cy]) continue; // 방문한 곳 지나가기
                if(arr[cx][cy] == 1) { // 1인 곳만 지나가기
                    arr[cx][cy] = arr[current.x][current.y] + 1; // 표시
                    queue.add(new Pair(cx, cy));
                    visited[cx][cy] = true;
                }
            }
        }
        // 도달하지 못한 1인 곳은 -1로 처리
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    arr[i][j] = -1;
                }
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.close();
    }
    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}