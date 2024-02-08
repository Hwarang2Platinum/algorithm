import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3190_뱀_송인범 {
    static int end;
    static StringTokenizer st;
    static int dx[] = {0, 1,  0, -1};
    static int dy[] = {1, 0, -1, 0};
    
    // 시간 위치 채킹
    static class rotation{
        int time;
        char where;
        public rotation(int time, char where) {
            super();
            this.time = time;
            this.where = where;
        }
    }
    
    // using to Snake
    static class Node{
    	int x;
    	int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =Integer.parseInt(br.readLine());
        int [][] Dummy = new int [n+1][n+1];
        int K =Integer.parseInt(br.readLine());
        end = 0;
        Queue<rotation> time = new ArrayDeque<>();
        
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            Dummy [row][col] = -1;
        }
        int L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);        
            time.add(new rotation(X,C));
        }
        
        int headX=1, headY=1;
        int idx = 0;
        
        Queue<Node> snake = new ArrayDeque<>();
        snake.offer(new Node(headX, headY));  // 머리 길이만 추가, 꼬리는 후 로직 처리
        Dummy[headX][headY]=1;
        		
        while(true) {
            ++end;
            int ax = headX + dx[idx];
            int ay = headY + dy[idx];
            
            // 나를 만날 때
            if(ax < 1 || ay <1 || ax > n || ay > n || Dummy[ax][ay] == 1) {
                break;
            }
            
            // 이동가능조건
            // -1이면 poll을 안한다
            if(Dummy[ax][ay]==0) {
                Node node = snake.poll();
                Dummy[node.x][node.y]=0;
            }
            
            
            Dummy[ax][ay]=1;
            snake.offer(new Node(ax,ay));
            headX =ax;
            headY =ay;
            
            // 이동 조건
            if(!time.isEmpty() && time.peek().time == end) {
                rotation now = time.poll();
                if (now.where =='L') {
                    idx = (idx+3)%4;
                    
                }else {
                    idx = (idx+1)%4;   
                }
            }
        }
    
        System.out.println(end);
    }
}
