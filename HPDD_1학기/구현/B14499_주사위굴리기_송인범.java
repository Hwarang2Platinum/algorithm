import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14499_주사위굴리기_송인범 {
    static int[][] map; 
    static int[] dice; 
    static int x, y, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M]; 

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[] {0,0,0,0,0,0,0}; 

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            move(num);
        }

    }

    static void move(int n) {
        int[] temp = dice.clone(); 
        
        if (n == 1 && y + 1 < M) { // 동
            y+=1;
            dice[1] = temp[4];
            dice[4] = temp[6];
            dice[6] = temp[3];
            dice[3] = temp[1];
        } else if (n == 2 && y - 1 >= 0) { // 서
            y-=1;
            dice[1] = temp[3];
            dice[3] = temp[6];
            dice[6] = temp[4];
            dice[4] = temp[1];
        } else if (n == 3 && x - 1 >= 0) { // 북
            x-=1;
            dice[1] = temp[5];
            dice[5] = temp[6];
            dice[6] = temp[2];
            dice[2] = temp[1];
        } else if (n == 4 && x + 1 < N) { // 남
            x+=1;
            dice[1] = temp[2];
            dice[2] = temp[6];
            dice[6] = temp[5];
            dice[5] = temp[1];
        } else {
            return; 
        }

        if (map[x][y] == 0) { 
            map[x][y] = dice[6]; 
        } else { 
            dice[6] = map[x][y]; 
            map[x][y] = 0; 
        }

        System.out.println(dice[1]); 
    }
}
