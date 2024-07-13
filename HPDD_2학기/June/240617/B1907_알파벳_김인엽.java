import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int R, C; // 행 열
    static char[][] board; // 보드
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1 ,0};

    static int answer;
    static Map<Character, Integer> alphabet2Index = new HashMap<>();
    public static void main(String[] args) throws IOException {
        // 알파벳 -> 인덱스
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 26; i++) {
            alphabet2Index.put(alphabets.charAt(i), i);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        // 보드 받기
        for (int i = 0; i < R; i++) {
            String string = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = string.charAt(j);
            }
        }

        // 알파벳 별 지났는지 여부
        boolean[] visited = new boolean[26];
        visited[alphabet2Index.get(board[0][0])] = true;
        dfs(0, 0, visited, 1);

        System.out.println(answer);
    }

    static void dfs(int x, int y, boolean[] visited, int cnt) {
        answer = Math.max(answer, cnt);
        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            // 범위 체크
            if(cx < 0 || cx >= R || cy < 0 || cy >= C) continue;
            // 같은 알파벳 확인
            if(visited[alphabet2Index.get(board[cx][cy])]) continue;
            visited[alphabet2Index.get(board[cx][cy])] = true;
            dfs(cx, cy, visited, cnt+1);
            visited[alphabet2Index.get(board[cx][cy])] = false;
        }
    }
}
