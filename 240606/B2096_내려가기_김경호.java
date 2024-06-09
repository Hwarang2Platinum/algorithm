import java.io.*;
import java.util.*;
/**
 * BOJ 2096 내려가기
 * DP문제처럼 보임
 * 메모라이제이션
 * 현재 depth의 3 요소는 각각 이전 depth에서의 최대, 최소들이 들어있다.
 * 현재 depth의 3요소중 최대, 최소를 반환하자.
 *
 * 주의사항 !! : 게임 점수 0이 가능하므로 dp의 초기값을 0으로 하면 모든 보드가 0일 경우
 * dp가 무용지물이 되어 엄청나게 돌 것이다 그래서 시간초과 남.
 */
public class Main {
    static int N;
    static int[][] minDp,maxDp,board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][3];
        maxDp = new int[N+1][3];
        minDp = new int[N+1][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxDp[i][j] = -1;
                minDp[i][j] = -1;
            }
        }
        for (int j = 0; j < 3; j++) {
            maxDp[N][j] = -1;
            minDp[N][j] = -1;
        }

        int mx = Math.max(maxDfs(N,0), Math.max(maxDfs(N,1),maxDfs(N,2)));
        int mn = Math.min(minDfs(N,0),Math.min(minDfs(N,1), minDfs(N,2)));

        System.out.println(mx + " " + mn);

    }

    public static int maxDfs(int depth, int cur){
        if(depth == 0) {
            return 0;
        }

        if(maxDp[depth][cur]!=-1){
            return maxDp[depth][cur];
        }

        int a = Integer.MIN_VALUE; int b = Integer.MIN_VALUE; int c = Integer.MIN_VALUE;
        if(cur!=2) a = maxDfs(depth-1, 0);
        b = maxDfs(depth-1, 1);
        if(cur!=0) c = maxDfs(depth-1, 2);

        return maxDp[depth][cur] = Math.max(a,Math.max(b,c)) + board[depth-1][cur];
    }

    public static int minDfs(int depth, int cur){
        if(depth == 0) {
            return 0;
        }

        if(minDp[depth][cur]!=-1){
            return minDp[depth][cur];
        }

        int a = Integer.MAX_VALUE; int b = Integer.MAX_VALUE; int c = Integer.MAX_VALUE;
        if(cur!=2) a = minDfs(depth-1, 0);
        b = minDfs(depth-1, 1);
        if(cur!=0) c = minDfs(depth-1, 2);
        return minDp[depth][cur] = Math.min(a,Math.min(b,c)) + board[depth-1][cur];
    }

}