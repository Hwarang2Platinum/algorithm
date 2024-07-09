import java.io.*;
import java.util.*;

// Hwarng Think
// 직선 도로 하나를 따라 여러 아파트 단지를 지음
// 아파트 단지 주민을 위해 도로 위 한 지점에 학교 1개를 신설
// 단지 간 이동은 반드시 통학 버스로만 가능함 ( 통학 버스 1대 )
// 아파트 단지와 위치는 도로 위의 좌표로 주어짐, 아파트 단지마다 학생들의 수도 주어짐
// 아침에 학교에서 출발하여 각 아파트 단지에 있는 학생을 태우고 다시 돌아옴
// 정원을 초과하여 학생을 태울 수 없음, 모든 학생을 등교시킬 때 까지 해당 과정을 반복
// 모든 학생을 등교시키는데, 필요한 통학 버스의 총 이동 거리의 최솟값을 계산하는 프로그램 작성
// N : 아파트 단지의 수 ( 2 ~ 30,000 )
// K : 통학버스의 정원 ( 1 ~ 2000 )
// S : 학교 위치
// 아파트 단지의 위치와 학생 수 ( 1 ~ 2000 )가 주어지며 ( 학교와 아파트 단지의 좌표는 0 ~ 100,000 )
// 최소 이동거리는 1_000_000_000 초과하는 경우는 없음
// 가장 괜찮은 방법은 "가장 멀리있으면서 정원에 맞춰 태울 수 있는 학생 군이 가장 우선"

public class B2512_통학버스_오화랑 {
    static class house {
        int loc, stud;

        public house(int loc, int stud) {
            this.loc = loc;
            this.stud = stud;
        }
    }

    static class Solution {
        int N, K, S, totalMove;
        PriorityQueue<house> leftHouseList;
        PriorityQueue<house> rightHouseList;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());

            this.N = Integer.parseInt(st.nextToken());
            this.K = Integer.parseInt(st.nextToken());
            this.S = Integer.parseInt(st.nextToken());
            leftHouseList = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.loc, o2.loc));
            rightHouseList = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.loc, o1.loc));

            int loc, stud;
            for (int i = 0; i < this.N; i++) {
                st = new StringTokenizer(input.readLine());
                loc = Integer.parseInt(st.nextToken());
                stud = Integer.parseInt(st.nextToken());

                if (loc < this.S)
                    leftHouseList.offer(new house(loc, stud));
                else
                    rightHouseList.offer(new house(loc, stud));
            }
            totalMove += clearing(leftHouseList);
            totalMove += clearing(rightHouseList);
            System.out.println(totalMove);
        }

        int clearing(PriorityQueue<house> PQ) {
            int moved = 0;

            int leftSeat = this.K;
            int current = this.S;
            house temp;
            while (!PQ.isEmpty()) {
                temp = PQ.poll();

                moved += Math.abs(current - temp.loc);
                current = temp.loc;

                if (temp.stud <= leftSeat) {
                    leftSeat -= temp.stud;
                } else {
                    moved += Math.abs(current - this.S);
                    temp.stud -= leftSeat;
                    current = this.S;
                    leftSeat = this.K;
                    PQ.offer(temp);
                }
            }
            moved += Math.abs(current - this.S);
            return moved;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
