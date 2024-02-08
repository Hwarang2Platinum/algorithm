import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class B11054_가장긴바이토닉부분수열_김인엽 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] dpLeft = new int[N];
		int[] dpRight = new int[N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j])
					dpLeft[i] = Math.max(dpLeft[j] + 1, dpLeft[i]);
			}
		}
		for(int i=N-1; i>=0; i--) {
			for(int j=N-1; j>i; j--) {
				if (arr[i] > arr[j])
					dpRight[i] = Math.max(dpRight[j] + 1, dpRight[i]);
			}
		}
		
//		System.out.println(Arrays.toString(dpLeft));
//		System.out.println(Arrays.toString(dpRight));

		for (int i = 0; i < N; i++) {
			dpLeft[i] += dpRight[i];
		}

		System.out.println(Arrays.stream(dpLeft).max().orElseThrow(NoSuchElementException::new)+1);
	}

}
