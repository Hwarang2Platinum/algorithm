import java.io.*;
import java.util.Arrays;

public class BOJ_Greedy_2012_HR {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int numStud = Integer.parseInt(input.readLine());
        int[] predictedRank_list = new int[numStud];
        for (int stud = 0 ; stud < numStud ; stud++)
            predictedRank_list[stud] = Integer.parseInt(input.readLine());
        Arrays.sort(predictedRank_list); // void형
        long maxDiff = 0;
        int currRank = 1;
        for (int predictedRank : predictedRank_list) {
            maxDiff += Math.abs(predictedRank - currRank);
            currRank++;
        }
        System.out.println(maxDiff);
    }
}