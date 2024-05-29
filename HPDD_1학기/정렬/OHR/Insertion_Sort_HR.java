import java.util.*;
import java.io.*;


public class Insertion_Sort_HR {
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST = new StringTokenizer(input.readLine());
        ArrayList<Integer> num_list = new ArrayList<>();

        while(ST.hasMoreTokens()){
            num_list.add(Integer.parseInt(ST.nextToken()));
        }
        System.out.println(num_list.toString());
        int insertIndex;
        int standardNum;
        for (int i = 1 ; i < num_list.size() ; i++){
        	insertIndex = i;
        	standardNum = num_list.get(i);
            for (int j = i - 1 ; j >= 0 ; j--) {
                if (num_list.get(j) < standardNum)
                    break;
                else {
                	insertIndex = j;
                }
            }
            num_list.remove(i);
            num_list.add(insertIndex, standardNum);
        }
        System.out.println(num_list.toString());
    }
}
