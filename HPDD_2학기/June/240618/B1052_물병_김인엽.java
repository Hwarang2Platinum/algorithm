import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 그리디
 * 답 봤습니다.
 * - N = 2^n 이면 물병 안 사도 됨
 * - ex) n = 13, k = 2일 때, 8, 4, 1 -> k보다 큼
 *       3병을 더 사면, n = 16 -> 1병으로 싹 담을 수 있음
 * 1. N이 K보다 이하라면, 그냥 다 들고갈 수 있음
 * 2. N이 K 보다 크면, 일부 합쳐 가야됨
 *      => 2^n 형태로 만들기(1번 만족할 때까지)
 * n = 13 -> 1개 추가 구매 후 2로 나누기 => 7
 * n = 7 -> 물병 개수가 홀수이므로 1개 추가 구매 후 2로 나누기 => 3
 * n = 3 : 2
 * 즉 세개를 더 사면 n = 13, k = 2를 만족
 * if(N%2 == 1):
 *  n = n/2
 *  cnt++
 *  n++
 *
 *  => 이걸 비트마스킹을 이용하면
 *  cnt += n&(-n)
 *  n += n&(-n)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = 0;
        // N의 이진 표현에서 1의 개수 반환
        while(Integer.bitCount(N) > K) {
            // N의 최하위 비트 1을 추출
            answer += N & (-N);
            // N의 최하위 비트를 오른쪽으로 이동
            N += N & (-N);
        }
        System.out.println(answer);
    }
}
