package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

// 15686 치킨배달
// NxN 도시 : 1은 집 2는 치킨집
// 치킨거리: 집과 가장 가까운 치킨집 사이의 거리, 각각의 집은 치킨거리를 가지고 있음
// 최대 M개의 치킨집을 골라서 가장 최소의 치킨거리를 구성

public class Main {
	static int[][] city;
	static int N, M;
	static Set<String> visited = new HashSet<String>();
	static ArrayList<String> chickens;
	static ArrayList<String> homes;
	
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N][];
		chickens = new ArrayList<String>();
		homes = new ArrayList<String>();
		
		for(int i=0;i<N;i++) {
			city[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(o->Integer.valueOf(o)).toArray();
			for(int j=0;j<N;j++) {
				if(city[i][j]==2) {
					chickens.add(i+","+j);
				}
				if(city[i][j]==1) {
					homes.add(i+","+j);
				}
			}
		}
		bt(0,0);
		
		System.out.println(ans);
//		System.out.println(Arrays.deepToString(city));
		// M개 이상의 치킨집 중 가장 최적의 치킨집 M개를 찾아야 한다.
		// K개중 M개 선택 => 거리 계산 ... 조합?
	}
	
	public static void bt(int depth,int start) {
		if(depth>=M) {
//			System.out.println(visited.toString());
			ans = Math.min(calDist(), ans);
			return;
		}
		// 치킨집들중 방문하지 않은 치킨집을 호출
		// 호출 시점에 거리 갱신
		for(int j=start;j<chickens.size();j++) {
			String chicken = chickens.get(j);
			if(!visited.contains(chicken)) {
				visited.add(chicken);
				bt(depth+1,j);
				visited.remove(chicken);
			}
		}
	}
	
	public static int calDist() {
		int distance = 0;
		Map<String, Integer> m = new HashMap<>();
		for(int i=0;i<homes.size();i++) {
			String home = homes.get(i);
			int[] rc = Arrays.stream(home.split(",")).mapToInt(o->Integer.valueOf(o)).toArray();
			for(int j=0;j<chickens.size();j++) {
				String chicken = chickens.get(j);
				if(!visited.contains(chicken)) continue;
				int[] chic_rc = Arrays.stream(chicken.split(",")).mapToInt(o->Integer.valueOf(o)).toArray();
				
				int tmp = Math.abs(rc[0]-chic_rc[0])+ Math.abs(rc[1]-chic_rc[1]);
				if(!m.containsKey(home)) {
					m.put(home,tmp);
					distance += tmp;
					continue;
				}
				
				if(m.get(home)>tmp) {
					distance -= m.get(home) - tmp;
					m.put(home,tmp);
				}
				
			}
		}
		return distance;
	}
}

