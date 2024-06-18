import java.util.*;
import java.io.*;

public class B15684_사다리조작_조승기 {
    static boolean[][] isLinked;
    static int N, M, H;
    static ArrayList<Integer> tmp = new ArrayList<>();

    static boolean find(int K) {
        if (tmp.size() == K) {
            return down();
        }

        for (int i = tmp.isEmpty() ? 0 : tmp.get(tmp.size()-1) + 1; i < H * (N -1); i++) {
            int[] c = i2c(i);
            if (isLinked[c[0]][c[1]]) continue;

            tmp.add(i);
            if (find(K)) return true;
            tmp.remove(tmp.size() - 1);
        }

        return false;
    }

    static boolean down() {
        for (Integer i : tmp) {
            int[] c = i2c(i);
            isLinked[c[0]][c[1]] = true;
        }

        boolean isFail = false;

        for (int n = 0; n < N; n++) {
            int i = 0;
            int j = n;

            while (i < H) {
                if (j != N-1 && isLinked[i][j]) {
                    j += 1;
                } else if (j != 0 && isLinked[i][j - 1]) {
                    j -= 1;
                }
                i += 1;
            }
            if (j != n) {
                isFail = true;
                break;
            }
        }

        if (!isFail) return true;

        for (Integer i : tmp) {
            int[] c = i2c(i);
            isLinked[c[0]][c[1]] = false;
        }

        return false;
    }

    static int[] i2c(int n) {
        return new int[]{n / (N - 1), n % (N - 1)};
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        isLinked = new boolean[H][N-1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            isLinked[x][y] = true;
        }

        System.out.println(down() ? 0 : (find(1) ? 1 : (find(2) ? 2 : (find(3) ? 3 : -1))));
    }
}
