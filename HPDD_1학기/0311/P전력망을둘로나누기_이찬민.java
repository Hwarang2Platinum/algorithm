import java.io.*;
import java.util.*;
public class P전력망을둘로나누기_이찬민 {
    static boolean[] visited;
    static ArrayList<Integer>[] arr;
    public int solution(int n, int[][] wires) { //9	[[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]] 이중 하나 끊어
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            visited = new boolean[n+1];

            arr = new ArrayList[n+1];
            for (int x = 1; x < n + 1; x++) {
                arr[x] = new ArrayList<>(); //x번이랑 어디랑 연결되어있는지
            }

            for (int j = 0; j < wires.length; j++) {
                if(i==j)continue;
                arr[wires[j][0]].add(wires[j][1]);
                arr[wires[j][1]].add(wires[j][0]);
            }


            int cnt= bfs(arr, 1);

            int tmp = Math.abs(cnt - (n-cnt));
            System.out.println(tmp);
            answer = Math.min(answer, tmp);
        }

        return answer;
    }
    public int bfs(ArrayList<Integer>[] arr, int start){
        if(!visited[start]){
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            dq.add(start);
            visited[start] = true;
            int count =1;

            while (!dq.isEmpty()) {
                int cur = dq.poll();
                for (int i = 0; i < arr[cur].size(); i++) {
                    int next = arr[cur].get(i);
                    if (!visited[next]) {
                        visited[next] = true;
                        dq.add(next);
                        count++;
                    }
                }
            }

            return count;

        }

        return 0;
    }
}
