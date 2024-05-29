import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891_CM {
    static int[][] data = new int[4][8];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            String line = br.readLine();
            for(int j=0; j<8; j++) {
                data[i][j] = line.charAt(j)-'0';
            }
        }

        StringTokenizer st;
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            gearOperation(idx,dir);
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    result += 1;
                } else if (i == 1) {
                    result += 2;
                } else if (i == 2) {
                    result += 4;
                } else {
                    result += 8;
                }
        }

        System.out.println(result);
    }

    static void gearOperation(int idx, int dir) {
        // 왼쪽 (0 ~ idx-1)
        leftSide(idx-1, -dir);
        // 오른쪽 (idx+1 ~ 3)
        rightSide(idx+1, -dir);
        rotation(idx,dir);
    }

    static void leftSide(int idx, int dir) {
        if(idx < 0) return;
        if(data[idx][2]==data[idx+1][6]) return;
        leftSide(idx-1, -dir);
        rotation(idx,dir);
    }

    static void rightSide(int idx, int dir) {
        if(idx > 3) return;
        if(data[idx][6] == data[idx-1][2]) return;
        rightSide(idx+1, -dir);
        rotation(idx,dir);
    }

    static void rotation(int idx, int dir) {
        if(dir==1) {
            int tmp = data[idx][7];
            for(int i=7; i>0; i--) {
                data[idx][i] = data[idx][i-1];
            }
            data[idx][0] = tmp;
        }else {
            int tmp = data[idx][0];
            for(int i=0; i<7; i++) {
                data[idx][i] = data[idx][i+1];
            }
            data[idx][7] = tmp;
        }
    }
}