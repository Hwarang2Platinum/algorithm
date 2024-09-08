// 구현 문제
// 길은 2N [가로, 세로]
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14890_경사로 {
    static int [][] map;
    static int cnt, n, L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n =Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 경사로 조건
        // 1) 높은 곳 -> 낮은 칸 차이 1
        // 2) 낮은 칸에 경사로를 놓고 L개의 칸이 연속적이며 경사로의 바닥에 닿아진다
        cnt = 0;
        for(int i=0; i<n; i++){
            if(checkRaw(i)) cnt++;
            if(checkCol(i)) cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean checkCol(int idx) {
        boolean [] isvisted = new boolean[n];
        for(int i=1; i<n; i++){
            if (map[idx][i] == map[idx][i-1]) continue;

            else if (map[idx][i] ==map[idx][i-1]-1) {
                for(int j=0; j<L; j++){
                    if(i+j >= n) return false; // 초과시

                    if(map[idx][i] != map[idx][i+j]){
                        return false;
                    }
                    isvisted[i+j] = true;
                }
            } else if (map[idx][i] == map[idx][i-1]+1) {
                for(int j=1; j<=L; j++){ // 뒤에 것이 더 클때
                    if(i - j < 0 || isvisted[i-j]) return false;
                    if(map[idx][i-1]!=map[idx][i-j]) return false;
                    isvisted[i-j]=true;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    private static boolean checkRaw(int idx) {
        boolean [] isvisted = new boolean[n];
        for(int i=1; i<n; i++){
            if (map[i][idx] == map[i-1][idx]) continue;

            else if (map[i][idx] == map[i-1][idx]-1) {
                for(int j=0; j<L; j++){
                    if(i+j >= n) return false; // 초과시

                    if(map[i][idx] != map[i+j][idx]){
                        return false;
                    }
                    isvisted[i+j] = true;
                }
            } else if (map[i][idx] == map[i-1][idx]+1) {
                for(int j=1; j<=L; j++){ // 뒤에 것이 더 클때
                    if(i - j < 0 || isvisted[i-j]) return false;
                    if(map[i-1][idx]!=map[i-j][idx]) return false;
                    isvisted[i-j]=true;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }


}
