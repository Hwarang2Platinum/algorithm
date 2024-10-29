import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int answer;
    static int[] arr;
    static boolean[] visited;
    static boolean[] teamed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 0; test_case < T; test_case++) {
            n = Integer.parseInt(br.readLine());

            arr = new int[n+1];
            visited = new boolean[n+1];
            teamed = new boolean[n+1];

            answer = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i <= n ; i++) {
                if(teamed[i]) continue;
                dfs(i);
            }

            bw.write(n - answer + "\n");
            bw.flush();
        }
        bw.close();
    }

    static void dfs(int index) {
        if(teamed[index]) return; // 이미 팀이 있으면 끝

        if(visited[index]) { // 이미 방문 했다면
            teamed[index] = true; // 팀 편성 완료
            answer++;
        }
        visited[index] = true;
        dfs(arr[index]);
        visited[index] = false;
        teamed[index] = true;
    }
}
