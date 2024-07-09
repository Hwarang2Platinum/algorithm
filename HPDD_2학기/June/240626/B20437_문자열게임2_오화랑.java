import java.io.*;
import java.util.*;

public class B20437_문자열게임2_오화랑 {
    static class Solution {
        int T, N;
        int[] count;
        ArrayList<ArrayList<Integer>> indexList;
        PriorityQueue<Integer> shortList, longList;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            this.T = Integer.parseInt(input.readLine());
            for (int t = 0; t < this.T; t++) {
                String target = input.readLine();
                this.N = Integer.parseInt(input.readLine());
                this.indexList = new ArrayList<>();
                for (int i = 0; i < 26; i++)
                    this.indexList.add(new ArrayList<>());
                this.count = new int[26];
                this.shortList = new PriorityQueue<>();
                this.longList = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

                for (int i = 0; i < target.length(); i++) {
                    char each = target.charAt(i);
                    this.indexList.get(each - 'a').add(i);
                    this.count[each - 'a']++;
                    if (this.count[each - 'a'] >= this.N) {
                        int last = this.indexList.get(each - 'a').size();
                        int dist = i - this.indexList.get(each - 'a').get(last - this.N) + 1;
                        this.shortList.add(dist);
                        this.longList.add(dist);
                    }
                }
                if (this.shortList.isEmpty()) {
                    sb.append(-1)
                            .append("\n");
                } else {
                    sb.append(this.shortList.poll())
                            .append(" ")
                            .append(this.longList.poll())
                            .append("\n");
                }
            }
            System.out.print(sb);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
