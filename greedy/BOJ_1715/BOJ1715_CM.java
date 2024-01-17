import java.io.*;
import java.util.*;


public class BOJ1715 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Long> pq = new PriorityQueue<Long>(); //숫자 낮은게 맨앞으로가는 큐
		
		for(int i=0; i<N; i++) {
			pq.add(Long.parseLong(br.readLine()));
		}
		
		long sum = 0;
		
		while(pq.size()>1) {
			long temp1 = pq.poll();
			long temp2 = pq.poll();
			sum += temp1 + temp2;
			pq.add(temp1 + temp2);
		}
		
		System.out.println(sum);
		
		
		
	}

}
