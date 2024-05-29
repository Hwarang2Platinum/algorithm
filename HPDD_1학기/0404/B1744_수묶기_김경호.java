import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> posPQ = new PriorityQueue<>((o1, o2) -> o2-o1);
        PriorityQueue<Integer> negPQ = new PriorityQueue<>((o1, o2) -> o1-o2);

        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            if(val>0){
                posPQ.add(val);
            }else{
                negPQ.add(val);
            }
        }
        int ans = 0;
        while(!posPQ.isEmpty()){
            int firstVal = posPQ.poll();
            if(!posPQ.isEmpty()){
                int secondVal = posPQ.poll();
                firstVal = Math.max(firstVal*secondVal, firstVal+secondVal);
            }
            ans+=firstVal;
        }
        while (!negPQ.isEmpty()){
            int firstVal = negPQ.poll();
            if(!negPQ.isEmpty()) {
                int secondVal = negPQ.poll();
                firstVal = Math.max(firstVal*secondVal, firstVal+secondVal);
            }
            ans+=firstVal;
        }
        System.out.println(ans);
    }
}
