import java.util.*;
import java.io.*;

public class Bubble_Sort_HR {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer ST = new StringTokenizer(input.readLine());
        ArrayList<Integer> num_list = new ArrayList<>();

        while(ST.hasMoreTokens()){
            num_list.add(Integer.parseInt(ST.nextToken()));
        }
        System.out.println(num_list.toString());
        int max_index = num_list.size() - 1;

        for (int i = max_index ; i > 0  ; i--){
            for (int j = 0 ; j < i ; j++) {
                if (num_list.get(j) > num_list.get(j+1))
                    Collections.swap(num_list, j, j+1);
            }
        }
        System.out.println(num_list.toString());
    }
}
