import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int[] month = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null ;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 월로 내림차순, 월이 같으면 일로 내림차순
				return o2[2] == o1[2] ? o2[3] - o1[3]:o2[2] - o1[2];
			}
		});
		int[][] projects = new int[N][5];
		for(int i =0 ;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int startMonth = Integer.parseInt(st.nextToken());
			int startDay = Integer.parseInt(st.nextToken());
			int endMonth = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());
			projects[i] = new int[] {startMonth, startDay, endMonth, endDay, i};
		}
		
		// 1. pq poll
		// 2. end보다 작은 start들을 모두 pq에 넣음
		// 1번 2번 반복하다가 end가 12월이면 종료
		// (pq는 end에 대해서 내림차순 정렬)
		
		int ans = 0; int lastMont = 0;
		boolean[] visited = new boolean[N+1]; 
		pq.add(new int[] {0,0,3,1,N});
		while(true) {
			int[] cur = pq.poll();
//			System.out.println(Arrays.toString(cur));
			if(cur!=null) lastMont = cur[2];
			if(cur==null || cur[2]>=12) break;
			
			visited[cur[4]] = true;
			ans++;
			for(int i=0;i<N;i++) {
				if(visited[i]) continue;
				
				if((projects[i][0] == cur[2] && projects[i][1] <= cur[3])
						||(projects[i][0] < cur[2]) ) {
//					System.out.println(Arrays.toString(projects[i]));
					pq.add(projects[i]);
				}
			}
		}
		System.out.println(lastMont == 12?ans:0);
		
	}
	
}