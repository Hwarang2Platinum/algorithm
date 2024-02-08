import java.io.*;
import java.util.*;

public class B3190_뱀_HR {
	
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Queue<Integer> moveLog = new ArrayDeque<>(); // 뱀의 영역 저장 
        Queue<Integer> moveList = new ArrayDeque<>(); // 뱀의 이동 Change List
        int size = Integer.parseInt(input.readLine());
        int appleNum = Integer.parseInt(input.readLine());
        int[][] map = new int [size + 1][size + 1];
        int moveCnt; int moveDir = 4;
        int[][] moveWay = { {0,1}, {1,0}, {0,-1}, {-1,0} }; // Right, Down, Left, Up
        // D는 1로 저장 -> 4 + D % 4 => 오 아 왼 위 
        // L은 -1로 저장 -> 4 + L % 4 => 오 위 왼 아

        for (int i = 0 ; i < appleNum ; i++) {
            st = new StringTokenizer(input.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]
                    = 1;
        }

        moveCnt = Integer.parseInt(input.readLine());
        for (int i = 0 ; i < moveCnt ; i++) {
            st = new StringTokenizer(input.readLine());
            moveList.offer(Integer.parseInt(st.nextToken()) + 1); 
            // x초가 끝난 뒤에 방향 전환 -> 실제 방향 전환해서 이동은 x + 1초에 이루어짐.
            switch (st.nextToken()) {
                case "L" : moveList.offer(-1); break;
                case "D" : moveList.offer(1);
            }
        }
        
        int currx = 1; int curry = 1; int currTime = 1;
        map[1][1] = 2; // 뱀이 있는 부분은 '2'
        moveLog.offer(1); moveLog.offer(1); // 뱀 영역 log 체크
        while (true) {
        	
        	// 방향 조정여부 -> 시간이 맞다면, 방향을 조정해주기
        	if (!moveList.isEmpty()) {
	        	if (currTime == moveList.peek()) {
	        		moveList.poll(); // 앞에 Move 시간 버려주기 
	        		moveDir += moveList.poll(); // Move 방향 int값을 활용해 방향 조정
	        		moveDir = moveDir < 0 ? moveDir + 4 : moveDir;
	        		moveDir = moveDir > 4 ? moveDir - 4 : moveDir;
	        	}
        	}
        	
        	// Game End 조건
        	currx += moveWay[moveDir%4][0];
        	curry += moveWay[moveDir%4][1];
        	if (currx <= 0 | curry <= 0 | currx > size | curry > size) break;
        	if (map[currx][curry] == 2) break;
        	// Game End 조건
        	
        	// 뱀 Move Story -> move
        	// 사과를 찾은게 아니라면 -> 꼬리 부분의 뱀 영역 제거
        	if (map[currx][curry] == 0)
        		map[moveLog.poll()][moveLog.poll()] = 0;
        	
        	// 기본 이동 -> 이동한 칸 Log에 넣고 2 표시
    		map[currx][curry] = 2;
    		moveLog.offer(currx); moveLog.offer(curry);
    		currTime++;
        }
        System.out.println(currTime);
    }
}

// 전략3 : 현재 바라보고 있는 방향에 대한 정보 
// 오른쪽 { 0, 1 } 0, 위쪽 { -1, 0 } 1, 왼쪽 { 0, -1 } 2, 아래쪽 { 1, 0 } 3
// 오른쪽 / 아래쪽 / 왼쪽 / 위쪽
//      4 / 4 = 0 5 /4 = 1 6 /4 = 2 7/4 = 3
// default : 오른쪽 
// D => 오 아 왼 위 0 1 2 3
// L => 오 위 왼 아 0 3 2 1 
// 방향 전환은 x초가 끝난 뒤에!

// 뱀의 시작 위치 0,0 -> 뱀 길이 1 ( 칸을 차지하는 수 -> 길이 )
// 뱀의 이동 -> 머리를 다음 칸에 위치시킴
// 사과가 있다면, 꼬리를 줄이지 않음 ( 뱀의 차지칸 + 1 => 길이 + 1)
// 사과가 없다면, 꼬리를 줄임 ( 뱀의 차지칸 - 1 => 길이 - 1)
// 벽과 자기 자신과 몸을 부딪히면 게임 종료
// 뱀은 매 초마다 이동 => 게임 종료 시점을 구하여라