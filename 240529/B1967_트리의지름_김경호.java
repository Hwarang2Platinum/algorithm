import java.io.*;
import java.util.*;

// 주의사항: 이진트리 아님....
public class Main {
    static List<int[]>[] trees;
    static int ans;

    // 왼쪽, 오른쪽 선택 => 각각 가장 긴놈 저장
    // 모든 노드 순회하며 왼쪽+오른쪽이 가장 큰 노드 선택
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        trees = new ArrayList[N+1];
        ans = 0;

        for (int i = 1; i < N+1; i++) {
            trees[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            trees[num].add(new int[]{child, weight});
        }

        dfs(1);

        System.out.println(ans);
    }

    // 내 자식들중 가장 큰놈을 반환하는 함수
    static int dfs(int num){

        // 자식들중 최대 두명을 큰놈부터 저장해서 답으로 비교하자
        int maxWeight = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(((o1, o2) -> o2-o1));

        // 현재 노드의 자식들을 탐색
        for (int[] children : trees[num]) {
            int child = children[0];
            int weight = children[1];

            // 자식노드 의 최대값과 현재 노드의 weight를 더하고 내림차순 정렬하기 위해 pq저장
            int childWeight = dfs(child);
            maxWeight = Math.max(maxWeight, childWeight + weight);

            pq.add(childWeight + weight);
        }

        // 이진 트리가 아니므로 상위 두개를 뽑아서 비교하자
        int answer = 0;
        for (int i = 0; i < 2; i++) {
            if(pq.isEmpty()) break;
            answer += pq.poll();
        }

        // 자식중 큰놈 두명을 뽑았다.. 이제 다른 부모들의 자식들과도 비교해야겠지?
        ans = Math.max(ans,answer);
        return maxWeight;
    }


}