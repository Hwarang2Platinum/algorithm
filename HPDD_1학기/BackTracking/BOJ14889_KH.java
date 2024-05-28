import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] stats;
	static boolean[] visited;
	static int[] startTeam;
	static int N,min;
	static int[] linkTeam;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		startTeam = new int[N/2];
		linkTeam = new int[N/2];
		stats = new int[N][];
		for(int i=0;i<N;i++) {
			stats[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(o->Integer.parseInt(o)).toArray();
		}
		min = Integer.MAX_VALUE;
		
		bt(0,0);
		
		System.out.println(min);
	}
	
	public static void bt(int depth, int cur) {
		if(depth==N/2) {
			cal();
			return;
		}
		// 스타트팀에 n/2 명 넣고 링크팀에 n/2넣어야된다.
		for(int i=cur+1;i<N;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			startTeam[depth] = i;
			bt(depth+1, i);
			visited[i] = false;
		}
	}

	public static void cal() {
		int startScore = 0;
		int linkScore = 0;
		int idx = 0;
		
		// 스타트 멤버 아닌애들만 링크 팀에 추가
		for(int i=0;i<N;i++) {
			if(!visited[i]) linkTeam[idx++] = i;
		}
		
		for(int startMember1: startTeam) {
			for(int startMember2: startTeam) {
				if(startMember1==startMember2) continue;
				startScore += stats[startMember1][startMember2];
			}
		}
		for(int linkMember1: linkTeam) {
			for(int linkMember2: linkTeam) {
				if(linkMember1==linkMember2) continue;
				linkScore += stats[linkMember1][linkMember2];
			}
		}
//		System.out.println("startTeam:" + Arrays.toString(startTeam));
//		System.out.println("linkTeam:" + Arrays.toString(linkTeam));
		min = Math.min(min, Math.abs(linkScore- startScore));
	}
}
