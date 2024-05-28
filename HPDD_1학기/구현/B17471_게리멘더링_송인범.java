import java.io.*;
import java.util.*;
//14784kb 124ms
public class B17471_게리멘더링_송인범 {
	static List<List<Integer>>value;
	static int minNum = 999_999_999, N = 0;
	static boolean[] check;
	static int [] people;
	public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	N = Integer.parseInt(br.readLine());
	people = new int[N];
	StringTokenizer st = new StringTokenizer(br.readLine());
	// 각 구역 0번 , 1번 .. N번 구역까지 인구 수 입력 받는다
	for(int i=0; i<N; i++)people[i] = Integer.parseInt(st.nextToken());
	// 나머지를 저장할거다.
	value = new ArrayList<List<Integer>>();
	// 초기화
	for(int i=0; i<N; i++) {
		value.add(new ArrayList<Integer>());
	}
	// 값 저장
	for(int i=0; i<N; i++) {
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		for(int k=0; k<num; k++) {
			int neighbor = Integer.parseInt(st.nextToken());
			value.get(i).add(neighbor-1);
		}
		
	}
	// 부분집합을 고려하자
	// 공집합, 전체 집합 제외
	check = new boolean[N];
	subset(0,0);
	if(minNum == 999_999_999)System.out.println(-1);
	else System.out.println(minNum);
	}
	
	
	public static void subset(int x, int sc) {
	    if (x == N) {
	        if (sc > 0 && sc < N) {
	            List<Integer> Aside = new ArrayList<>();
	            List<Integer> Bside = new ArrayList<>();
	            
	            for (int j = 0; j < N; j++) {
	                if (check[j]) {
	                    Aside.add(j);
	                } else {
	                    Bside.add(j);
	                }
	            }
	            boolean status1 = bfs(Aside);
	            boolean status2 = bfs(Bside);
	            
	            if (status1 && status2) {
	                int num = 0;
	                for (int j = 0; j < N; j++) {
	                    if (check[j]) {
	                        num += people[j];
	                    } else {
	                        num -= people[j];
	                    }
	                }
	               
	                minNum = Math.min(minNum, Math.abs(num));
	            }
	        }
	        return;
	    }

	    
	    check[x] = true;
	    subset(x + 1, sc + 1);

	    
	    check[x] = false;
	    subset(x + 1, sc);
		
		
	}
	
	public static boolean bfs(List<Integer>side) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean [] visited = new boolean[N];
		q.add(side.get(0));
		visited[side.get(0)]=true;
		int checking = side.size()-1;
		while(!q.isEmpty()) {
			int current = q.poll();
			for(int i=0; i<value.get(current).size(); i++) {
				int target =value.get(current).get(i);
				if(side.contains(target)&&!visited[target]) {
					visited[target] = true;
					q.add(target);
					checking--;
				}	
			}
		}
		//맨처음에 checking을 빼줘야한다.
		if (checking==0)return true;
		else return false;
	}
}