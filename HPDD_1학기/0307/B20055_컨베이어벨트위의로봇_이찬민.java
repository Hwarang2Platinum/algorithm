import java.io.*;
import java.util.*;
public class B20055_컨베이어벨트위의로봇_이찬민 {



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] belt = new int[n * 2 + 1];
        boolean[] robots = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n * 2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());

        }

        int round = 1;

        while(true) {

            int temp = belt[n * 2];

            for (int i = n * 2; i > 1; i--) {
                belt[i] = belt[i - 1];
            }

            belt[1] = temp;

            for (int i = n; i > 1; i--) {
                robots[i] = robots[i - 1];
            }

            robots[1] = false;

            if(robots[n]) {
                robots[n] = false;
            }

            for (int i = n-1; i > 0; i--) { //n-1부터 1까지
                if (robots[i] && !robots[i + 1] && belt[i + 1] >= 1) {
                    belt[i + 1]--;
                    robots[i] = false;
                    robots[i + 1] = true;
                }
            }

            if(robots[n]) { //내림
                robots[n] = false;
            }

            if(belt[1] > 0) { //로봇 올림
                robots[1] = true;
                belt[1]--;
            }

            int cnt = 0;
            for (int i = 1; i <= n * 2; i++) {
                if (belt[i] == 0) {
                    cnt++;
                }

                if (cnt >= k) {
                    System.out.println(round);
                    return;
                }
            }

            round++;
        }

    }
}
