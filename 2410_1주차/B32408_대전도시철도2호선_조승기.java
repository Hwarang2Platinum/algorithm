import java.io.*;
import java.util.*;

public class B32409_대전도시철도2호선_조승기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] li = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            li[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            li[a].add(b);
            li[b].add(a);
        }

        int[] parent = new int[N + 1];
        boolean[] isv = new boolean[N + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        isv[1] = true;

        boolean isFind = false;

        while (!queue.isEmpty() && !isFind) {
            int u = queue.poll();

            for (int v : li[u]) {
                if (!isv[v]) {
                    isv[v] = true;
                    parent[v] = u;
                    queue.add(v);
                    if (v == N) {
                        isFind = true;
                        break;
                    }
                }
            }
        }

        boolean[] isLine1 = new boolean[N + 1];

        int node = N;
        while (node != 0) {
            isLine1[node] = true;
            node = parent[node];
        }

        Arrays.fill(isv, false);

        int K = 0;
        ArrayList<Integer> sizes = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (!isLine1[i] && !isv[i]) {
                int size = 0;
                Queue<Integer> q = new ArrayDeque<>();
                q.add(i);
                isv[i] = true;

                while (!q.isEmpty()) {
                    int u = q.poll();
                    size++;
                    for (int v : li[u]) {
                        if (!isLine1[v] && !isv[v]) {
                            isv[v] = true;
                            q.add(v);
                        }
                    }
                }

                sizes.add(size);
                K += size;
            }
        }

        long total = (long) K * (K - 1) / 2;

        long sum = 0;
        for (int size : sizes) {
            sum += (long) size * (size - 1) / 2;
        }

        long answer = total - sum;

        System.out.println(answer);
    }
}
