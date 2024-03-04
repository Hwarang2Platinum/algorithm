package March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B20056_마법사상어와파이어볼_송인범 {
	static int result = 0, N;
	// 0 1 2 3 4 5 6 7
	static int [] dx = {-1,-1,0,1,1,1,0,-1};
	static int [] dy = {0,1,1,1,0,-1,-1,-1};
	static List<Fireball>[][] space; 
	static ArrayList<Fireball> list = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		space = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				space[i][j] = new ArrayList<>(); // 초기화 
		}
		
		
		for(int idx = 0; idx<M; idx++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Fireball(r-1, c-1, m, s, d));
		}
		// k번 이동
		for(int idx = 0; idx<K; idx++) {
			
			
			for(Fireball current: list) {					
				
				// 거리 만큼 이동
				int ax = (current.r +N + dx[current.d]*(current.s%N))%N;
				int ay = (current.c +N + dy[current.d]*(current.s%N))%N;
				// 합치기 위해 이동
				current.r = ax;
				current.c = ay;
				space[ax][ay].add(current);
				
				
			}
			
			// 나누기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(space[i][j].size()<2) {
						space[i][j].clear();
						continue;
					}
					// 2개이상일때
					else {
						//질량 , 속도, 모든 방향 check
						int speed = 0, mass = 0, even = 0, odd = 0;
						int how = space[i][j].size();
						for(Fireball cur: space[i][j]) {
							speed+=cur.s;
							mass+=cur.m;
							if(cur.d % 2==0)even++;
							else odd++;
							list.remove(cur);
						}
						space[i][j].clear();
						mass/=5;
						if(mass==0)continue; // 나누기 실패
						speed/=how;
						// 새로 추가 필요
						if(even == how || odd == how) {
							for(int what = 0; what<8; what+=2)
								list.add(new Fireball(i, j, mass, speed, what));
						}
						else {
							
							for(int what = 1; what<8; what+=2)
								list.add(new Fireball(i, j, mass, speed, what));
						}
						
						
					}
				}
			}
			
		}
		
		for(Fireball cur: list) {
			result+=cur.m;
		}
		System.out.println(result);
		

	}
	
	// 불꽃 정리
	static class Fireball{
		int r;
		int c;
		int m;
		int s;
		int d;
		public Fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

}
