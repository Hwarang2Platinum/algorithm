import java.util.*;
import java.io.*;


public class Selection_Sort_HR {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST = new StringTokenizer(input.readLine());
        ArrayList<Integer> num_list = new ArrayList<>();

        while(ST.hasMoreTokens()){
            num_list.add(Integer.parseInt(ST.nextToken()));
        }
        System.out.println(num_list.toString());
        int min_index;
        for (int i = 0 ; i < num_list.size() ; i++) {
            min_index = i;
            for (int j = i ; j < num_list.size() ; j++ ){
                if (num_list.get(min_index) > num_list.get(j)){
                    min_index = j;
                }
            }
            Collections.swap(num_list, i, min_index);
        }
        System.out.println(num_list.toString());
    }
}
