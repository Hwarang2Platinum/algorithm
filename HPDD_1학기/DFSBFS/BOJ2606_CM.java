import java.io.*;
import java.util.*;


public class BOJ2606_CM {
    static int[][] arr;
    static boolean[] visit;
    static int N;
    static int M;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());// 컴퓨터의 수
        M = Integer.parseInt(br.readLine());// 간선의 수

        arr = new int[101][101];
        visit = new boolean[101];

        for(int i = 1; i < M + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 1;  //일단 입력 다 받음
        }

        dfs(1); //1번 컴퓨터부터 감염

        System.out.println(cnt-1); // 1번 컴퓨터 빼고 개수


    }

    public static void dfs(int start) {
        visit[start] = true;
        cnt++;

        for(int i=1; i<N+1; i++){
            if (arr[start][i] == 1 && visit[i] == false) {
                dfs(i);
            }
        }
    }

}
