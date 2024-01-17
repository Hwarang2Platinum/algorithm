import java.io.*;
import java.util.*;

public class BOJ1260_CM {

    static int N;
    static int M;
    static int V;
    static int[][] arr;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken()); //입력받기

        arr = new int[1001][1001]; // 인덱스 0은 갖다 버리고 1부터 안헷갈리게 시작
        visited = new boolean[1001]; //배열 만들기

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[b][a] = 1;  // 1 이면 a 에서 b 로 연결되어있다는 뜻
        }

        dfs(V);
        System.out.println();
        visited = new boolean[1001]; //다시 초기화
        bfs(V);
    }

    static int cnt=0;
    public static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        for (int i = 1; i < N + 1; i++) {
            if (arr[start][i] == 1 && visited[i] == false) {
                dfs(i);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        System.out.print(start + " "); //시작 전 조건 만들어두고

        while (q.isEmpty() == false) {  // 큐가 빌때까지
            int front = q.poll();
            for (int i = 1; i < N + 1; i++) {
                if (arr[front][i] == 1 && visited[i] == false) { //연결 되어잇고 안가본 곳으로!
                    q.add(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }

    }
}
