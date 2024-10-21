import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] splits = input.split("::");
        String zeros = "0000";

        int nonZeroCnt = 0;
        List<String> ans = new ArrayList<>();
        if(!splits[0].isEmpty() && splits.length==2){
            String[] one = splits[0].split(":");
            String[] two = splits[1].split(":");
            int rest = 8 - (one.length + two.length);
            ans.addAll(Arrays.asList(one));
            for (int i = 0; i < rest; i++) {
                ans.add(zeros);
            }
            ans.addAll(Arrays.asList(two));
        }else{
            String[] one = null;
            if(splits[0].isEmpty()){
                one = splits[1].split(":");
                for (int i = 0; i < 8 - one.length; i++) {
                    ans.add(zeros);
                }
                ans.addAll(Arrays.asList(one));
            }else{
                one = splits[0].split(":");
                ans.addAll(Arrays.asList(one));
                for (int i = 0; i < 8 - one.length; i++) {
                    ans.add(zeros);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4-ans.get(i).length(); j++) {
                System.out.print("0");
            }
            if(i==7) System.out.println(ans.get(i));
            else System.out.print(ans.get(i) + ":");
        }
    }

}
