import java.io.*;
import java.util.*;
public class B17821_야구공_이찬민 {

    static int N, inning, score, out, max_score, get_score,cnt;
    static int[][] arr;
    static boolean[] loo; // 0-> 1루 1->2루 2->3루 3-> 홈
    static int[] players = {1,2,3,4,5,6,7,8};
    static int[] tasuck;
    static boolean[] visited = new boolean[8];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()); // i이닝 j번선수 스코어
            }
        }
        max_score=0;
        tasuck = new int[8];
        permutation(0);
        System.out.println(max_score);
    }

    public static void permutation(int depth) {
        if (depth == 8) {
            inning = 0;
            play();
            max_score=Math.max(max_score,get_score);
            return;

        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                tasuck[depth] = players[i];
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void play() {
        cnt =0;
        out =0;
        get_score=0;
        loo = new boolean[4];
        while (inning<N) { // N이면 종료
            if (cnt == 8) {
                cnt = 0;
            }
            if (cnt == 3) {
                attack(arr[inning][0]);
                if(inning>=N)break;

                attack(arr[inning][tasuck[cnt]]);

            } else {
                attack(arr[inning][tasuck[cnt]]);

            }
        }
    }

    public static void attack(int num) {
//        System.out.print(num +" ");
        score=0;
        if (num == 0) {
            out++;
            if (out == 3) {
                for (int j = 0; j < 4; j++) {
                    loo[j] = false;
                } //123루 초기화
                inning++;
                out=0;
            }
        }
        if (num == 1) { //안타
            for (int i = 2; i >= 0; i--) {
                if (loo[i] = true) {
                    loo[i + 1] = true;
                    loo[i] = false;
                    if (loo[3]) {
                        score++;
                        loo[3]=false;
                    }
                }
            }
            loo[0] = true;
        }
        if (num == 2) {//2루타
            for (int a=0; a<2; a++) {
                for (int i = 2; i >= 0; i--) {
                    if (loo[i] = true) {
                        loo[i + 1] = true;
                        loo[i] = false;
                        if (loo[3]) {
                            score++;
                            loo[3]=false;
                        }
                    }
                }
            }
            loo[1] = true;
        }
        if (num == 3) {//3루타
            for (int a=0; a<3; a++) {
                for (int i = 2; i >= 0; i--) {
                    if (loo[i] = true) {
                        loo[i + 1] = true;
                        loo[i] = false;
                        if (loo[3]) {
                            score++;
                            loo[3]=false;
                        }
                    }
                }
            }
            loo[2] = true;

        }
        if (num == 4) { // 홈런
            for (int a=0; a<3; a++) {
                for (int i = 2; i >= 0; i--) {
                    if (loo[i] = true) {
                        loo[i + 1] = true;
                        loo[i] = false;
                        if (loo[3]) {
                            score++;
                            loo[3]=false;
                        }
                    }
                }
            }
            score++;
        }
//        System.out.print(score +" ");
        get_score += score;
        cnt++;

    }
}
