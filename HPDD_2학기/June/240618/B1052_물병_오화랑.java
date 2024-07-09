import java.io.*;
import java.util.*;

public class B1052_물병_오화랑 {
    static class Solution {
        int N, K, B;
        Stack<Integer> madeList = new Stack<>();

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            this.N = Integer.parseInt(st.nextToken());
            this.K = Integer.parseInt(st.nextToken());

            if (this.N <= this.K) {
                this.B = this.K - this.N;
            } else {
                getLeastMake();
                if (madeList.size() <= this.K)
                    this.B = 0;
                else
                    makeTargetNum();
            }
            System.out.println(this.B);
        }

        void getLeastMake() {
            int current, made;
            current = this.N;
            while (true) {
                if (current <= 1) {
                    break;
                }
                made = (int) Math.pow(2, log(current));
                madeList.push(made);
                current -= made;
            }
            if (current == 1)
                madeList.push(current);
        }

        void makeTargetNum() {
            int current, next;
            while (true) {
                current = madeList.pop();
                next = madeList.pop();
                this.B += (next - current);
                next *= 2;
                madeList.push(next);
                if (madeList.size() == this.K)
                    break;
            }
        }

        int log(int current) {
            return (int) (Math.log(current) / Math.log(2));
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
