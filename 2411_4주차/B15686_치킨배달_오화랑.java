
/**
 * IMP : https://www.acmicpc.net/problem/15686
 * * Re : 치킨 배달 => 한때, '순조부'를 잘했을 때 풀고자 노력했던 문제 
 * * R X C 형태 ( R 행, C 열 ) => (r, c) : 위에서 부터 r번째 칸, 왼쪽에서 부터 c번째 칸 ( r, c >= 1 )
 * * 치킨 거리 => 집과 가장 가까운 치킨집의 거리 => 도시의 치킨 거리 : Sum of Each 치킨 거리
 * * 최대 M개의 치킨 집을 골랐을 때, 도시의 치킨 거리의 최소값을 구하여라 
 * Type : 순 조 부 
 * IMP : 최대 13개 중 M 개를 선택하는 느낌 => 13C6 / 13C7이 최대가 됨 => 1716
 * 
 */

import java.io.*;
import java.util.*;

public class B15686_치킨배달_오화랑 {

    static class Home {
        int x, y;

        public Home(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Chicken {
        int x, y;
        boolean selected;

        public Chicken(int x, int y, boolean selected) {
            this.x = x;
            this.y = y;
            this.selected = selected;
        }
    }

    static class Solution {
        int N, M;
        int totalGlobalDist = Integer.MAX_VALUE;
        ArrayList<Home> homeList = new ArrayList<>();
        ArrayList<Chicken> chickenList = new ArrayList<>();

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int target;
            int chickenNum = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    target = Integer.parseInt(st.nextToken());
                    if (target == 1)
                        homeList.add(new Home(i, j));
                    else if (target == 2) {
                        chickenList.add(new Chicken(i, j, false));
                        chickenNum++;
                    }
                }
            }
            selectChicken(0, 0, chickenNum);
            System.out.println(totalGlobalDist);
        }

        /**
         * IMP : Combination으로 Chicken 집을 선택합니다. M개 선택
         * ! : 최대 M개인데 왜 M개를 선택하는가? => 최소 Dist가 M개 미만에서 결정 나도, M개 선택에서 Dist가 늘어나는 것은
         * 불가능하기 때문에 가능함.
         * 
         * @param sIndex
         * @param count
         * @param chickenNum
         */
        void selectChicken(int sIndex, int count, int chickenNum) {
            if (count == M) {
                totalGlobalDist = Math.min(totalGlobalDist, getChickenDistance());
            }

            Chicken each;
            for (int i = sIndex; i < chickenNum; i++) {
                each = chickenList.get(i);
                if (each.selected)
                    continue;
                each.selected = true;
                selectChicken(i + 1, count + 1, chickenNum);
                each.selected = false;
            }
        }

        /**
         * IMP : 선택된 Chicken 집에 대한 Dist를 구한다.
         * 
         * @return totalMin
         */
        int getChickenDistance() {
            int totalMin = 0;
            int currentMin, currentDist;
            for (Home eachHome : homeList) {
                currentMin = Integer.MAX_VALUE;
                for (Chicken eachChicken : chickenList) {
                    if (!eachChicken.selected)
                        continue;
                    currentDist = Math.abs(eachHome.x - eachChicken.x) + Math.abs(eachHome.y - eachChicken.y);
                    currentMin = Math.min(currentMin, currentDist);
                }
                totalMin += currentMin;
            }
            return totalMin;
        }
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

}