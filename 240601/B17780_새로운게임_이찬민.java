import java.util.*;
import java.io.*;

public class B17780_새로운게임_이찬민 {
    static int N,K;
    static ArrayList<Integer>[][] map; //말 배치된 맵
    static horse[] horses;
    static int[][] mapColor;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        mapColor = new int[N][N];
        horses = new horse[K];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
                mapColor[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>(); //전부다 어레이리스트로 만들어버림
            }
        }

        for(int i=0;i<K;i++){

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int direction = Integer.parseInt(st.nextToken());
            horses[i] = new horse(x,y,direction-1);
            map[x][y].add(i);
        }
        int result = go();
        bw.write(result + "\n");
        bw.flush();
    }

    static int go(){
        int time = 0;
        while(true){
            time++;
            if(time>1000)
                return -1;
            for(int i=0;i<K;i++){
                int x = horses[i].x;
                int y = horses[i].y;
                int dir = horses[i].direction;
                if(map[x][y].get(0) != i)	//맨 밑의 말 이동
                    continue;

                int tempX = x + dx[dir];
                int tempY = y + dy[dir];
                if(isValid(tempX,tempY) || mapColor[tempX][tempY]==2){//파란 칸
                    int tempDir = dirSwap(dir);
                    horses[i].direction = tempDir;
                    tempX = x + dx[tempDir];
                    tempY = y + dy[tempDir];

                    if(isValid(tempX,tempY) || mapColor[tempX][tempY]==2){ // 이동했는데 파란색
                        continue;
                    }else if(mapColor[tempX][tempY] == 1){	//이동했는데 빨강
                        redSpace(x,y,tempX,tempY);
                    }else
                        whiteSpace(x,y,tempX,tempY);

                }else if(mapColor[tempX][tempY] == 1){	//빨간 칸
                    redSpace(x,y,tempX,tempY);
                }else{		//하얀 칸
                    whiteSpace(x,y,tempX,tempY);
                }
                if(map[tempX][tempY].size() >= 4)	//이동한 칸에 말이 4개 이상
                    return time;
            }
        }
    }

    static boolean isValid(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    static void redSpace(int x, int y, int tempX, int tempY){
        for(int i = map[x][y].size()-1; i>=0; i--){
            int temp = map[x][y].get(i);
            map[tempX][tempY].add(temp);
            horses[temp].x = tempX;
            horses[temp].y = tempY;
        }
        map[x][y].clear();
    }

    static void whiteSpace(int x, int y, int tempX, int tempY){
        for(int i = 0; i< map[x][y].size(); i++){
            int temp = map[x][y].get(i);
            map[tempX][tempY].add(temp);
            horses[temp].x = tempX;
            horses[temp].y = tempY;
        }
        map[x][y].clear();
    }

    static int dirSwap(int direction){
        if(direction==0)
            return 1;
        else if(direction==1)
            return 0;
        else if(direction==2)
            return 3;
        else
            return 2;
    }

    static class horse {
        int x;
        int y;
        int direction;
        public horse(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}