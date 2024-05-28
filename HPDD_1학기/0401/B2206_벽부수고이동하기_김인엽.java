import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206_벽부수고이동하기_김인엽 {
    static int N, M;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        for(int i=0; i<N; i++) {
            String[] str = br.readLine().split("");
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

        boolean[][][] visited = new boolean[N][M][2]; // N과 M은 지도의 크기
        Queue<int[]> queue = new ArrayDeque<>();
        // (0, 0)에서 시작, 처음에는 벽을 부수지 않았으므로 상태는 0
        queue.offer(new int[]{0, 0, 0, 1});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int wall = current[2]; // 현재 위치에 도달하기까지 벽을 부순 상태
            int dist = current[3];

            if(dist >= answer) continue;
            // 목적지에 도달했다면 탐색 종료 또는 다음 단계로
            if (x == N - 1 && y == M - 1) {
                // 도달했을 때의 처리
                answer = dist;
                continue;
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 범위 체크

                // 다음 위치가 벽인 경우, 아직 벽을 부수지 않았다면 벽을 부수고 이동
                if (arr[nx][ny] == 1 && wall == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    queue.offer(new int[]{nx, ny, 1, dist+1});
                }
                // 다음 위치가 빈 공간이고 아직 방문하지 않았다면 이동
                else if (arr[nx][ny] == 0 && !visited[nx][ny][wall]) {
                    visited[nx][ny][wall] = true;
                    queue.offer(new int[]{nx, ny, wall, dist+1});
                }
            }
        }


        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
