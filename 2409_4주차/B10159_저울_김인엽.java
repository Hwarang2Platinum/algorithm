import java.util.*;
import java.io.*;

public class Main {

    /**
     *
     */
    static List<Integer>[] biggerAdj; // 큰 관계만 갖는거
    static List<Integer>[] smallerAdj; // 작은 관계만 갖는거
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N  = Integer.parseInt(br.readLine());
        biggerAdj = new ArrayList[N+1];
        smallerAdj = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) {
            biggerAdj[i] = new ArrayList<>();
            smallerAdj[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int bigger = Integer.parseInt(st.nextToken());
            int smaller = Integer.parseInt(st.nextToken());
            biggerAdj[smaller].add(bigger); // 큰거 넣기
            smallerAdj[bigger].add(smaller); // 작은거 넣기
        }

        for(int i = 1; i < N+1; i++) {
            int canKnow = search(i, smallerAdj) + search(i, biggerAdj) + 1; // 큰거, 작은거, 나
            bw.write((N - canKnow) + "\n");
        }
        bw.close();
    }

    static int search(int v, List<Integer>[] adjList) {
        int cnt = 0;
        boolean[] visited = new boolean[N+1];
        visited[v] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next : adjList[cur]) {
                if(visited[next]) continue;
                queue.add(next);
                visited[next] = true;
                cnt++;
            }
        }
        return cnt;
    }

}
