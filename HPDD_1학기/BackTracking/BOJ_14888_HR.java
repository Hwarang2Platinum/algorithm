import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14888_HR {
    static int[] numArr;
    static int[] operators;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> results;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int numCnt = Integer.parseInt(input.readLine());
        numArr = new int[numCnt];
        operators = new int[4];
        results = new LinkedList<>();

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < numCnt; i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < 4; i++)
            operators[i] = Integer.parseInt(st.nextToken());

        cal(1, numArr[0]);
        Collections.sort(results);
        System.out.printf("%d%n%d", results.get(results.size()-1), results.get(0));
    }

    public static void cal(int cnt, int resultSoFar) {
        if (cnt == numArr.length) {
            results.add(resultSoFar);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                switch (i) {
                    case 0:
                        cal(cnt + 1, resultSoFar + numArr[cnt]);
                        break;
                    case 1:
                        cal(cnt + 1, resultSoFar - numArr[cnt]);
                        break;
                    case 2:
                        cal(cnt + 1, resultSoFar * numArr[cnt]);
                        break;
                    case 3:
                        cal(cnt + 1, resultSoFar / numArr[cnt]);
                        break;
                }
                operators[i]++;
            }
        }
    }
}
