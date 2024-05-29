package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class P_타겟넘버_김인엽 {
  public static int solution(int[] numbers, int target) {
    // depth, sum
    Queue<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{0, 0});
    int length = numbers.length;
    int answer = 0;
    while(!queue.isEmpty()) {
      int[] depthSum = queue.poll();
      // 현재 depth 가 최대면 끝
      if(depthSum[0] == length) {
        // 타겟 넘버를 만들었으면 정답 추가
        if(depthSum[1] == target) answer++;
        continue;
      }
      // 더할 때
      queue.add(new int[]{depthSum[0] + 1, depthSum[1] + numbers[depthSum[0]]});
      // 뺄 때
      queue.add(new int[]{depthSum[0] + 1, depthSum[1] - numbers[depthSum[0]]});
    }

    return answer;
  }
  public static void main(String[] args) {
//    int[] numbers = {1,1,1,1,1}; int target = 3;
    int[] numbers = {4,1,2,1}; int target = 4;
    System.out.println(solution(numbers, target));
  }
}
