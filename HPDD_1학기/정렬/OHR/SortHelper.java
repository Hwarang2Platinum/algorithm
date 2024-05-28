import java.util.ArrayList;
import java.util.Random;

public class SortHelper {
	public static int[] RandomNumListGen1(int size) {
		ArrayList<Integer> RandomList = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0 ; i < size ; i++) {
			RandomList.add(rand.nextInt(1000));
		}
		
		return RandomList.stream().mapToInt(Integer::intValue).toArray();
	}
	
	public static ArrayList<Integer> RandomNumListGen2(int size) {
		ArrayList<Integer> RandomList = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0 ; i < size ; i++) {
			RandomList.add(rand.nextInt(1000));
		}
		
		return RandomList;
	}
}
