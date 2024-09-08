import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9465_스티커_조승기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 0, T = Integer.parseInt(br.readLine()); t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int[][] sti = new int[N][2];
            for (int i = 0; i < N; i++) {
                sti[i][0] = Integer.parseInt(st.nextToken());
                sti[i][1] = Integer.parseInt(st1.nextToken());
            }
            if (N >= 2) {
                sti[1][0] += sti[0][1];
                sti[1][1] += sti[0][0];
            }
            for (int i = 2; i < N; i++) {
                sti[i][0] += Math.max(sti[i - 1][1], sti[i - 2][1]);
                sti[i][1] += Math.max(sti[i - 1][0], sti[i - 2][0]);
            }
            sb.append(Math.max(sti[N - 1][0], sti[N - 1][1])).append("\n");
        }
        System.out.println(sb);
    }
}
