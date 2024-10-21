
/**
 * IMP : https://www.acmicpc.net/problem/16169
 * * 모든 컴퓨터는 1번부터 N번 까지의 번호가 매겨져 있다. 모든 컴퓨터는 각자의 계급과 동작 속도를 가지고 있다. 또한 동작 속도는 모두 양의 정수
 * * I번 컴퓨터와 J번 컴퓨터 간의 전속 속도는 (I - J)^2 임.
 * IMP : 제일 낮은 계급의 컴퓨터를 제외하면 -> 동작을 하기 위해선 한 단계 낮은 계급의 모든 컴퓨터에게 정보를 전달받아야 동작함 ( 동작 시간 만큼 소요됨 )
 * IMP : 계급이 C인 컴퓨터가 동작을 마치고 종료되면 -> C+1의 계급을 가진 모든 컴퓨터에게 정보를 전달 후 종료됨
 * IMP : 모든 컴퓨터가 동작을 마치고 종료되면 끝 
 * 
 */

import java.io.*;
import java.util.*;

public class B16169_수행시간_오화랑 {

    static class CPU {
        int num, time;

        public CPU(int num, int time) {
            this.num = num;
            this.time = time;
        }

        public String toString() {
            return "num : " + this.num + ", time :" + this.time;
        }
    }

    static class Solution {
        int N, totalTime;
        HashMap<Integer, ArrayList<CPU>> cpuGrade = new HashMap<>();

        public void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            N = Integer.parseInt(input.readLine());
            int grade, time;
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(input.readLine());
                grade = Integer.parseInt(st.nextToken());
                time = Integer.parseInt(st.nextToken());
                // IMP : 간단하게 grade의 KeySet 포함 여부에 따른 분기 처리
                cpuGrade.computeIfAbsent(grade, k -> new ArrayList<>()).add(new CPU(i, time));
            }

            // IMP : Queue 초기화
            Queue<CPU> beforeQueue = new ArrayDeque<>();
            Queue<CPU> afterQueue = new ArrayDeque<>();

            // IMP : 계산
            for (int i = 1; i <= cpuGrade.keySet().size(); i++) {
                while (!afterQueue.isEmpty())
                    beforeQueue.offer(afterQueue.poll());

                int startTime, transTime;
                for (CPU after : cpuGrade.get(i)) {
                    startTime = after.time;
                    transTime = 0;
                    for (CPU before : beforeQueue) {
                        transTime = Math.max(transTime,
                                (after.num - before.num) * (after.num - before.num) + before.time);
                    }
                    afterQueue.offer(new CPU(after.num, startTime + transTime));
                }

                while (!beforeQueue.isEmpty())
                    beforeQueue.poll();
            }
            // IMP : 정답 출력
            for (CPU each : afterQueue)
                totalTime = Math.max(totalTime, each.time);
            System.out.println(totalTime);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.run();
    }
}
/**
 * grade : 1, time : 30
 * grade : 1, time : 1
 * grade : 2, time : 5
 * grade : 3, time : 9
 * grade : 3, time : 1
 * grade : 4, time : 2
 * grade : 4, time : 2
 * grade : 4, time : 2
 * grade : 5, time : 3
 */