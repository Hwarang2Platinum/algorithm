import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer>[] queue = new ArrayDeque[N];
        for (int i = 0; i < N; i++) {
            queue[i] = new ArrayDeque();
        }

        // 각각의 열들을 큐로 만든다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                queue[j].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 맨 마지막 행을 pq에 넣는다 정렬은 역방향 (최대힙)
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(((o1, o2) -> o2[1]-o1[1]));
        for (int i = 0; i < N; i++) {
            pq.add(new int[]{i,queue[i].pollLast()});
        }

        int cnt = 0;
        int ans = 0;
        // 매 반복마다 pq에서 최대값을 뺀다. 최대값에 대한 인덱스의 큐로 다시 채워준다 (비어있으면 그냥 넘어감)
        while(cnt!=N){
            int[] tmp = pq.poll();
            ans=tmp[1];
            cnt++;
//            System.out.println(Arrays.toString(tmp));
            if(queue[tmp[0]].isEmpty()) continue;
            pq.add(new int[]{tmp[0], queue[tmp[0]].pollLast()});
        }
        System.out.println(ans);

    }

}