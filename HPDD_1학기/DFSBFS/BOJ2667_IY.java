import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//7
//0110100
//0110101
//1110101
//0000111
//0100000
//0111110
//0111000

public class BOJ2667_IY {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] graph;
    static ArrayList<Integer> answer;

    final static int[] dx = {0,0,-1,1};
    final static int[] dy = {1,-1,0,0};

    static int cnt;
    static int n;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        cnt = 0;
        answer = new ArrayList<>();

        for(int i=0; i<n; i++) {
            String[] tmp = br.readLine().split("");
            for(int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                // 0이 아니면 탐색
                if(graph[i][j] != 0) {
                    dfs(i, j);
                    answer.add(cnt);
                    cnt = 0;
                }
            }
        }
        // 총 단지수
        System.out.println(answer.size());
        // 정렬
        answer.sort(Comparator.naturalOrder());
        // 하나씩 출력
        for (int tmpAnswer : answer) {
            System.out.println(tmpAnswer);
        }
    }

    public static void dfs(int x, int y) {
        graph[x][y] = 0;
        cnt++;
        // 상하좌우 확인
        for(int i=0; i<dx.length; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            // 범위를 벗어난다면 넘어가기
            if(cx >= n || cx < 0 || cy >= n || cy < 0) continue;
            // 집이 있다면 cnt 올리고 재귀!
            if(graph[cx][cy] > 0) {
                dfs(cx, cy);
            }
        }
    }
}
