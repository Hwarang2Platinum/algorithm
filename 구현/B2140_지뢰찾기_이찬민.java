import java.io.*;
import java.util.*;
public class B2140_지뢰찾기_이찬민 {
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int N, result;
    static char[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        if (N >= 5) {
            result = (int)Math.pow(N - 4, 2); //가운데채우기
        }else result =0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i >= 1 && i < N - 1 && j >= 1 && j < N - 1) continue;

                int now = arr[i][j] - '0';

                
                for (int k = 0; k < 8; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && arr[nx][ny] == '#' && now!=0) {
                        arr[nx][ny] = '*'; //지뢰
                        now--;
                    } else if (nx >= 0 && ny >= 0 && nx < N && ny < N && arr[nx][ny] == '*' && now != 0) {
                        now--;
                    } else if (nx >= 0 && ny >= 0 && nx < N && ny < N && arr[nx][ny] == '#' && now==0) {
                        arr[nx][ny] = '@'; //지뢰아님
                    }
                }
                

            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(arr[i][j]);
//            }
//            System.out.println();
//        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              if(arr[i][j]=='*')result++;
            }
        }
        System.out.println(result);


        }
}
