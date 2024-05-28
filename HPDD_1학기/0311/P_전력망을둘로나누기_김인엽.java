package Programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class P_전력망을둘로나누기_김인엽 {
  static List<Integer>[] adjList;
  public static int bfs(int deletedFrom, int deletedTo, int n) {
    // 1번부터 탐색해서 몇개와 이어져있는지 확인
    Queue<Integer> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[n+1];
    queue.add(1);
    visited[1] = true;
    int answer = 0;
    while(!queue.isEmpty()) {
      int cur = queue.poll();
      answer++;

      for(int link : adjList[cur]) {
        if(visited[link]) continue;
        // 지워진거라면 무시
        if((link == deletedFrom && cur == deletedTo) || (link == deletedTo && cur == deletedFrom)) {
          continue;
        }

        queue.add(link);
        visited[link] = true;
      }
    }
    return Math.abs(answer - (n - answer));
  }
  public static int solution(int n, int[][] wires) {
    int answer = Integer.MAX_VALUE;
    adjList = new ArrayList[n+1];
    for(int i=0; i<n+1; i++) {
      adjList[i] = new ArrayList<>();
    }
    for(int[] wire: wires) {
      int from = wire[0];
      int to = wire[1];
      // 양방향
      adjList[from].add(to);
      adjList[to].add(from);
    }

    // 제일 연결된게 많은 애한테서 하나씩 끊어서 검사해보기
    for(int[] wire : wires) {
      answer = Math.min(bfs(wire[0], wire[1], n), answer);
    }
    return answer;
  }
  public static void main(String[] args) {
    int n = 9; int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
//    int n = 4; int[][] wires = {{1,2},{2,3},{3,4}};
//    int n = 7; int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
    System.out.println(solution(n, wires));
  }
}
