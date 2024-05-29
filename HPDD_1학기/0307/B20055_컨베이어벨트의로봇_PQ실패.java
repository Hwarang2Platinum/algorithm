import java.util.*;
import java.io.*;

public class B20055_컨베이어벨트의로봇_PQ실패 {
    static class belt {
        int location, howMuch, robotNum;
        boolean isOn;
        public belt(int location, int howMuch) {
            this.location = location;
            this.howMuch = howMuch;
            this.robotNum = -1;
            this.isOn = false;
        }
    }
    static belt[] conBelt;
    static int kCount;
    static boolean canOperate = true;
    static PriorityQueue<belt> haveRList = new PriorityQueue<>(new Comparator<belt>() {
        @Override
        public int compare(belt o1, belt o2) {
            return Integer.compare(o1.robotNum, o2.robotNum);
        }});
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int cSize = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        conBelt = new belt[2 * cSize];
        st = new StringTokenizer(input.readLine());
        for (int i = 0 ; i < 2 * cSize ; i++) conBelt[i] = new belt(i, Integer.parseInt(st.nextToken()));
        int stage = 1;
        while (true) {
            move(cSize, K, stage);
            if (!canOperate) break;
            stage++;
        }
        System.out.println(stage);
    }
    public static void move(int cSize, int K, int stage) {
        // step 1 : Belt가 움직인다.
        belt pastBelt = conBelt[2 * cSize - 1];
        belt nextBelt = conBelt[0];
        for (int i = 0 ; i < 2 * cSize ; i++) {
            if (i == 0) nextBelt = conBelt[i];
            else {
                pastBelt = nextBelt;
                nextBelt = conBelt[i];
                if (i == cSize - 1 && pastBelt.isOn) {
                    pastBelt.isOn = false;
                    pastBelt.robotNum = -1; // 해당 벨트가 가지고 있다는 로봇이 없다는 의미 -1
                }
            }
            conBelt[i] = pastBelt;
            conBelt[i].location = i;
        }

        // step 2 : 로봇이 움직인다.
        for (belt eachB : conBelt)
            if (eachB.isOn) haveRList.offer(eachB);

        while(!haveRList.isEmpty()) {
            belt currBelt = haveRList.peek();
            int currLoc = currBelt.location;
            int nextLoc = currLoc + 1 == 2 * cSize? 0 : currLoc + 1;
            if (conBelt[nextLoc].isOn || conBelt[nextLoc].howMuch == 0) haveRList.poll();
            else {
                if (nextLoc == cSize - 1) {
                    conBelt[currLoc].robotNum = -1;
                    conBelt[currLoc].isOn = false;
                    conBelt[nextLoc].howMuch--;
                }
                else {
                    conBelt[currLoc].robotNum = -1;
                    conBelt[currLoc].isOn = false;

                    conBelt[nextLoc].robotNum = currBelt.robotNum;
                    conBelt[nextLoc].isOn = true;
                    conBelt[nextLoc].howMuch--;
                }
                if (conBelt[nextLoc].howMuch == 0) kCount++;
                haveRList.poll();
            }
        }

        // step 3 : 올리는 위치에 로봇이 추가된다.
        if (conBelt[0].howMuch > 0) {
            conBelt[0].howMuch--;
            conBelt[0].robotNum = stage;
            conBelt[0].isOn = true;
            if (conBelt[0].howMuch == 0) kCount++;
        }
        // step 4 : 내구성 0 개수 체크
        if (kCount >= K) canOperate = false;
    }
}

