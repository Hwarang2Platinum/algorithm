import java.util.*;
import java.io.*;

public class Main {

    /**
     * 1325. 효율적인 해킹 -> BFS!
     * @param args
     * @throws IOException
     */
    static int[] answers;
    static List<Integer>[] adjList;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접 리스트
        adjList = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // b -> a로 갈 수 있게
            adjList[b].add(a);
        }

        answers = new int[N+1];
        for(int i = 1; i < N+1; i++) {
            check(i);
        }

        int maxValue = Arrays.stream(answers).max().orElse(0);
        for(int i = 1; i<N+1; i++) {
            if(answers[i] == maxValue) {
                bw.write(i + " ");
            }
        }
        bw.close();
    }

    public static void check(int index) {
        int answer = 0;
        boolean[] isVisited = new boolean[N+1];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(index);
        isVisited[index] = true;

        while(!queue.isEmpty()) {
            int v = queue.poll();

            for(Integer node : adjList[v]) {
                if(answers[node] != 0) { // 이미 계산한 개수가 있다면 사용
                    answer += answers[node];
                    continue;
                }
                if(isVisited[node]) continue;
                queue.add(node);
                isVisited[node] = true;
                answer++;
            }
        }

        answers[index] = answer;
    }
}
