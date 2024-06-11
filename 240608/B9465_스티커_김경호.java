import java.io.*;
import java.util.*;
/**
 * BOJ 9465 - 스티커
 * dp문제
 * 탑다운으로 해결
 * dp
 *  - 스티커의 첫번째 선택했을 때 dp[depth][0]
 *  - 스티커의 두번째 선택했을 때 dp[depth][1]
 *  - 스티커중 선택 안했을 때 dp[depth][2]
 *  dfs 함수
 *  - pointer
 *      - 현재 depth 에서 선택한 스티커 0은 첫번째, 1은 두번째, 2는 아무것도 선택 안함
 *  - 현재 depth에서 첫번째 스티커 선택했다 -> (다음스티커의 두번째 스티커 선택 , 선택x 중 큰값) + 현재 선택 스티커 점수
 *  - 현재 depth에서 두번째 스티커 선택했다 -> (다음스티커의 첫번째 스티커 선택 , 선택x 중 큰값) + 현재 선택 스티커 점수
 *  - 현재 depth에서 스티커 선택안했다 -> 다음스티커의 첫번째 스티커 선택 , 다음스티커의 두번째 스티커 선택 중 큰값
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[N+1][3];    // N부터 depth, pointer 전 까지 최대
            for (int i = 0; i < N+1; i++) {
                dp[i][0] = -1;
                dp[i][1] = -1;
                dp[i][2] = -1;
            }
            int[][] sticker = new int[N][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                sticker[i][0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                sticker[i][1] = Integer.parseInt(st.nextToken());
            }

            int ret = dfs(0, 2, sticker, dp, N);
            sb.append(ret)
                    .append("\n");
        }
        System.out.print(sb);
    }

    public static int dfs(int depth, int pointer,int[][] sticker,int[][] dp, int N){
        if(depth == N){
            if(pointer==2) return 0;
            else return sticker[depth-1][pointer];
        }
        if(dp[depth][pointer]!=-1) return dp[depth][pointer];

        if(pointer==0)
            return dp[depth][0]
                    = Math.max(dfs(depth+1, 1, sticker, dp, N) + sticker[depth-1][pointer],
                    dfs(depth+1, 2, sticker, dp, N)+ sticker[depth-1][pointer]) ;

        else if(pointer==1)
            return dp[depth][1]
                    = Math.max(dfs(depth+1, 0, sticker, dp, N) + sticker[depth-1][pointer],
                    dfs(depth+1, 2, sticker, dp, N) + sticker[depth-1][pointer]);
        else{
            return dp[depth][2]
                    =Math.max(dfs(depth+1, 1, sticker, dp, N)
                    ,Math.max(dfs(depth+1, 0, sticker, dp, N),
                    dfs(depth+1, 2, sticker, dp, N))
            );
        }
    }

}