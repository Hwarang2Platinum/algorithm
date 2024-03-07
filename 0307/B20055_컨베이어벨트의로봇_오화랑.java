import java.io.*;
import java.util.*;

public class B20055_컨베이어벨트의로봇_오화랑 {
    static class belt{
        int howMuch;
        boolean isOn;
        public belt(int howMuch) {
            this.howMuch = howMuch;
            this.isOn = false;
        }
    }
    static belt[] conBelt;
    static int kCount;
    static boolean canOperate = true;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int cSize = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        conBelt = new belt[2 * cSize];
        st = new StringTokenizer(input.readLine());
        for (int i = 0 ; i < 2 * cSize ; i++) conBelt[i] = new belt(Integer.parseInt(st.nextToken()));

        int stage = 1;;
        while (true) {
            move(cSize, K);
            if (!canOperate) break;
            stage++;
        }
        System.out.print(stage);
    }
    public static void move(int cSize, int K) {
        // 1. Belt가 움직인다.
        belt pastBelt = conBelt[2 * cSize - 1];
        belt nextBelt = conBelt[0];
        for (int i = 0 ; i < 2 * cSize ; i++) {
            if (i != 0) {
                pastBelt = nextBelt;
                nextBelt = conBelt[i];
                if (i == cSize - 1 && pastBelt.isOn) pastBelt.isOn = false;
            }
            conBelt[i] = pastBelt;
        }
        // 2. Robot이 움직인다.
        for (int i = cSize - 2 ; i > 0 ; i--) {
            if(conBelt[i].isOn) {
                if (!conBelt[i + 1].isOn && conBelt[i + 1].howMuch > 0) {
                    conBelt[i + 1].howMuch--;
                    conBelt[i].isOn = false;
                    if (i < cSize - 2) conBelt[i + 1].isOn = true;
                    if (conBelt[i + 1].howMuch == 0) kCount++;
                }
            }
        }
        // 3. 올리는 위치에 로봇을 추가한다.
        if (conBelt[0].howMuch > 0) {
            conBelt[0].howMuch--;
            conBelt[0].isOn = true;
            if (conBelt[0].howMuch == 0) kCount++;
        }
        if (kCount >= K) canOperate = false;
    }
}
