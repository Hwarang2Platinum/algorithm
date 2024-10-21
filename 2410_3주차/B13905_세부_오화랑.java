import java.io.*;
import java.util.*;

/**
 * IMP : https://www.acmicpc.net/problem/13905
 * 섬은 바다 위를 떠다지는 집들과, 집들을 연결하는 다리로 이루어져 있음
 * * 각 다리마다, 다리 위를 지나갈 수 있는 무게 제한이 존재함
 * * 최대한의 금 빼빼로를 들고 가려고 함. => 집들의 번호와 다리의 무게 제한을 알아 냄.
 * * 금빼빼로 하나의 무게는 1, 숭이의 몸무게는 고려하지 않음
 * IMP : 집의 개수 2 <= N <= 100,000 , 다리의 수 1 <= M <= 300,000
 * * 출발위치 S, 혜빈의 위치 E ( S != E )
 * * 숭이의 출발 위치에서 가져갈 수 있는 최대 개수를 구하라
 * ! 일단, 수학적으로 보았을 때, Cycle을 돈다고 최대값이 커지지는 못함. 이미 지나간 경로는 반영이 되버림
 * * 각 Node는 본인이 어떤, possW로 와졌는 지를 기억을 해줘야 한다.
 * 
 * IMP : Priority Queue에 계속 넣으면서 간다?
 * 
 */

public class B13905_세부_오화랑 {
    static class Node {
        int num, possW;

        public Node(int num, int possW) {
            this.num = num;
            this.possW = possW;
        }
    }

    static class Solution {
        int N, M, S, E, Result;
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        ArrayList<Integer> weightVisited = new ArrayList<>();
        PriorityQueue<Node> PQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.possW, o1.possW));

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            // IMP : N, M 구하기
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            // IMP : S, E 구하기
            st = new StringTokenizer(input.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            // IMP : Graph Init
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
                weightVisited.add(0);
            }
            // IMP : Graph 채우기
            int from, to, possW;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(input.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                possW = Integer.parseInt(st.nextToken());
                graph.get(from).add(new Node(to, possW));
                graph.get(to).add(new Node(from, possW));
            }
            // IMP : 최대 빼빼로 경로 계산하기 => Poss경로를 계속 PQ에 넣으면서 가기
            Node current;
            int nextW;
            PQ.add(new Node(S, 1_000_000));
            weightVisited.set(S, 1_000_000);
            while (!PQ.isEmpty()) {
                current = PQ.poll();
                if (current.num == E) {
                    Result = current.possW;
                    break;
                }
                for (Node next : graph.get(current.num)) {
                    nextW = current.possW > next.possW ? next.possW : current.possW;
                    if (nextW > weightVisited.get(next.num)) {
                        PQ.add(new Node(next.num, nextW));
                        weightVisited.set(next.num, nextW);
                    }
                }
            }
            System.out.println(Result);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.run();
    }
}
