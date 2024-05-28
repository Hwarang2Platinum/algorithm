import java.io.*;
import java.util.*;

public class BOJ14889_CM {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N];
        chooseTeam(0,0);
        System.out.println(minDiff);
    }

    private static void chooseTeam(int depth, int start) {
        if (depth == N / 2) {
            checkScore();
            return;
        }
        for (int i = start; i < N; i++) {
            if(visited[i]==false) {
                visited[i] = true;
                chooseTeam(depth + 1, i+1); // start+1 로 두번째 매개변수를 주면 시간초과
                visited[i] = false;                     // 혹은 두번째 매개변수를 없애고 0부터 다 탐색해도 초과
            }
        }
    }

    private static void checkScore() {
        int scoreA=0;
        int scoreB=0;
        for (int i = 0; i < N-1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] == true && visited[j] == true) {
                    scoreA += arr[i][j];
                    scoreA += arr[j][i];
                }
                if (visited[i] == false && visited[j] == false) {
                    scoreB += arr[i][j];
                    scoreB += arr[j][i];
                }
            }
        }
        int diff = Math.abs(scoreA - scoreB);
        minDiff = Math.min(minDiff, diff);
    }

}
