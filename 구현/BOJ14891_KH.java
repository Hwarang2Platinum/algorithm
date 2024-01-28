package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	static int idx=0;
	static Deque<Integer>[] deque = new ArrayDeque[4];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int j=0;j<4;j++) {
			deque[j] = new ArrayDeque<>();
			br.readLine().chars().forEach(o->deque[idx].add(o-48));
			idx++;
		}
		
		int K = Integer.parseInt(br.readLine());
		
		
		// 각 톱니바퀴의 3시 9시 보고 같은 극인지 확인 
		// 다른 극이면 같이 돌리기, 오른쪽 왼쪽 함수 
		for(int i=0 ; i<K;i++) {
			String[] line = br.readLine().split(" ");
			int curGear = Integer.parseInt(line[0]) - 1;
			int direct = Integer.parseInt(line[1]);
			gearToLeft(-direct, curGear);
			gearToRight(-direct, curGear);
			rotate(direct, curGear );
		}
		int ans = 0;
		for(int i=0; i<4;i++) {
			if(deque[i].getFirst()==1) {
				ans += (int)Math.pow(2,i);
			}
//			System.out.println(deque[i]);
		}
		
		System.out.println(ans);
	}
	
	// 왼쪽기어의 3시와 지금기어의 9시 확인 curGear가 0이면 종료 
	public static void gearToLeft(int direction, int curGear) {
		if(curGear == 0) {
			return;
		}
		if(deque[curGear-1].toArray()[2]!=deque[curGear].toArray()[6]) {
			gearToLeft(-direction, curGear-1);
			rotate(direction, curGear-1);
//			System.out.println(curGear + "기어도 움직입니다(왼)" + deque[curGear-1]);
		}
	}
	
	// 오른쪽기어의 9시와 지금기어의 3시 확인 , curGear가 3이면 종료 
	public static void gearToRight(int direction, int curGear) {
		if(curGear == 3) {
			return;
		}
		if(deque[curGear+1].toArray()[6]!=deque[curGear].toArray()[2]) {
			gearToRight(-direction, curGear+1);
			rotate(direction, curGear+1);
//			System.out.println(curGear+2 + "기어도 움직입니다(오)" + deque[curGear+1]);
		}
	}
	
	// 1:시계방향 -1 : 반시계방향 
	public static void rotate(int direction, int curGear) {
//		System.out.println(direction+"방향으");
		//시계면 맨 뒤를맨앞으로
		if(direction>0) {
			deque[curGear].addFirst(deque[curGear].pollLast());
		}
		//반시계면 맨앞을 맨뒤로 
		else {
			deque[curGear].addLast(deque[curGear].pollFirst());
		}
	}
}
