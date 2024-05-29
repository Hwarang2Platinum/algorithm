import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] belts = new int[2*N];
        boolean[] robots = new boolean[2 * N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++){
            belts[i] = Integer.parseInt(st.nextToken());
        }

        int dur = 0;
        int level = 1;
        int ans = 0;
        while(dur<K){
            /*
             슬라이딩 윈도우
             start=>현재 위의 컨베이어 벨트의 시작점 -1 (한칸 시계방향으로 돌리기 새롭게 시작점이 될 애)
             end=>위의 컨베이어 벨트중 마지막 -1(한칸 시계방향으로 돌리기 때문에 마지막이 될 애)
             @ 0보다 작아지면 배열 끝으로 옮겨주기.@
             */
            int start = 2*N - level;
            int end = (start - N - 1) <0? (2*N + (start-N-1)): (start-N-1);

            // N만큼(위쪽 컨베이어 수만큼 end부터 start까지 돈다)
            for(int i=0;i<N;i++){

                // start지점까지 index(end)가 오면 이전에는 로봇이 없으므로 이전 로봇을 옮겨주는 작업 필요 x
                // 만약 내구성이 0보다 크면 start지점에 로봇을 올려준다.
                if(i==N-1 && belts[start]>0){
                    robots[start] = true;
                    if(--belts[start]==0) dur++;
                    continue;
                }

                // end지점( 맨 처음으로 탐색함) 에 로봇이 있으면 빼준다.
                if(i==0 && robots[end]){
                    robots[end] = false;
                }

                // 로봇 옮기기
                // 현재 인덱스 -1 을 prev로 놓고 prev->현재인덱스(end)로 로봇을 옮겨준다
                // 옮기고 내구성 -1하기
                int prev = end==0?(2*N-1):(end-1);
                if(!robots[end]&&belts[end]>0&&robots[prev]){
                    // 만약 현재 end가 컨베이어벨트의 마지막이면 옮겨봤자 바로 내려야하기 떄문에 true안함
                    if(i!=0) robots[end] = true;
                    robots[prev] = false;
                    if(--belts[end]==0) dur++;
                }

                // end를 하나 줄여준다. 탐색지점을 하나씩 왼쪽으로 간다고 생각하기
                end = end==0 ? (2*N-1) : (end-1);
            }
//            System.out.println(Arrays.toString(belts));
//            System.out.println(Arrays.toString(robots));

            // level 이 2*N+1이 되면 위에서 start가 음수가 되므로 level 재조정
            if(++level==2*N + 1){
                ans+=2*N;
                level = 1;
            }
        }
        System.out.println(ans+level-1);
    }
}