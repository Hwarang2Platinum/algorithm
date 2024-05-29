package study;

public class P_합승택시요금_김인엽 {
    static int answer = Integer.MAX_VALUE;
    static int[][] arr;
    static int[][] distance;
    final static int INF = 200_000_000;
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        arr = new int[n+1][n+1];
        distance = new int[n+1][n+1];
        // 인접배열 만들기
        for(int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int weight = fare[2];
            // 양방향
            arr[from][to] = weight;
            arr[to][from] = weight;
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(i == j) distance[i][j] = 0;
                else if(arr[i][j] > 0) distance[i][j] = arr[i][j];
                else distance[i][j] = INF;
            }
        }
        // floid warshall
        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < n+1; j++) {
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }

        search(s, a, b, n, 0);

        return answer;
    }

    private static void search(int v, int a, int b, int n, int sum) {
        // 이미 더 크면 끝
        if(sum + distance[v][a] + distance[v][b] > answer) return;
        // 정답 업데이트
        answer = distance[v][a] + distance[v][b] + sum;

        // 갈수있는 모든 노드에 대해 탐색
        for(int i=1; i<n+1; i++) {
            // 본인 노드는 패스
            if(v == i) continue;
            // 가봤자 업뎃 못하면 패스
            if(sum + distance[v][i] > answer) continue;
            search(i, a, b, n, sum+distance[v][i]);
        }
    }


    public static void main(String[] args) {
//    int n = 6; int s = 4; int a = 6; int b = 2; int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        int n = 7; int s = 3; int a = 4; int b = 1; int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
//    int n = 6; int s = 4; int a = 5; int b = 6; int[][] fares = {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}};
        System.out.println(solution(n, s, a, b, fares));
    }
}
