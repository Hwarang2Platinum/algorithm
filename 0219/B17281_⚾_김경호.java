import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,S,answer=0;
	static int[][] players;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		players = new int[N][9];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bt(0,new int[9], new boolean[9]);
		System.out.println(answer);
	}
	
	// result 는 타순 
	static void bt(int depth, int[] result, boolean[] visited) {
		if(depth == 9) {
//			System.out.println(Arrays.toString(result));
			answer = Math.max(answer, simulation(result));
			return;
		}
		if(depth==3) {
			result[depth] = 0;
			bt(depth+1, result, visited);
			return;
		}
		for(int i=1; i<9;i++) {
			if(visited[i]) continue;
			result[depth] = i;
			visited[i] = true;
			bt(depth+1, result, visited);
			visited[i] = false;
		}
	}

	private static int simulation(int[] result) {
		// TODO Auto-generated method stub
		int inning = 0;
		int out = 0;
		int playerIdx =0;;
		int idx = 0;
		int score = 0;
		
		Queue<Integer> q = new ArrayDeque<>();
		
		while(inning<N) {
			playerIdx = result[(idx++)%9];
			int r = players[inning][playerIdx];
			
			if(r==0) out++;
			else {
				for(int i=0;i<r;i++) {
					if(i==0) q.add(1);
					else q.add(0);
					if(q.size()>3) {
						if(q.poll()==1) score++;  
					}
				}
			}
			
			if(out==3) {
				inning++;
				q.clear();
				out = 0;
			}
		}
		return score;
	}
}
 