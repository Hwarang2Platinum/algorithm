import java.util.*;
import java.io.*;

public class Main {

    /**
     * Ai가 A에서 등장한 횟수를 F(Ai)라 했을 때,
     * Ai의 오등큰수는 오른쪽에 있으면서, 수열 A에서 등장한 횟수가 F(Ai)보다 큰 수 중에서 가장 왼쪽 수
     * 없으면 -1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 입력 배열
        int[] result = new int[N]; // 정답
        Arrays.fill(result, -1); // -1로 처음 초기화

        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> freq = new HashMap<>(); // 값 : 개수
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            freq.put(num, freq.getOrDefault(num, 0) + 1); // map 에 넣기
        }

        Stack<Integer> stack = new Stack<>(); // 각 인덱스 저장

        for(int i = 0; i < N; i++) {
            // 스택이 비어있지 않고, 현재 숫자의 빈도가 스택 최상단 숫자 빈도보다 클 때
            while(!stack.isEmpty() && freq.get(arr[stack.peek()]) < freq.get(arr[i])) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        for(int i = 0; i < N; i++) {
            bw.write(result[i] + " ");
        }
        bw.close();
    }

}
