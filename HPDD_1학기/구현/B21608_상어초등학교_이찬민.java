import java.io.*;
import java.util.*;

public class B21608_상어초등학교_이찬민 {
    static class Student implements Comparable<Student>{
        int x;
        int y;
        int cnt;
        int emptyCnt;

        Student(int x, int y, int cnt, int emptyCnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Student o) {
            if(o.cnt == this.cnt) {
                if(o.emptyCnt == this.emptyCnt) {
                    if (o.x == this.x) {
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return o.emptyCnt - this.emptyCnt;
            }
            return o.cnt - this.cnt;
        }
    }
    static int N;
    static int[][] map;
    static HashSet<Integer>[] list;
    static int[] student;
    static int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        list = new HashSet[N*N+1];
        student = new int[N*N+1];

        for(int i=1; i<=N*N; i++) {
            list[i] = new HashSet<>();
        }

        for(int i=1; i<=N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            student[i] = a;

            for(int j=0; j<4; j++) {
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
            }
        }

        map[1][1] = student[1];

        simulate();
        getAnswer();
    }

    public static void getAnswer() {
        int sum = 0;
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                int cnt = 0;
                for(int i=0; i<4; i++) {
                    int nx = x + dist[i][0];
                    int ny = y + dist[i][1];
                    if(!isIn(nx, ny)) continue; // 범위 내에 있는지 확인
                    if(list[map[x][y]].contains(map[nx][ny])) cnt++; //친한친구 세기
                }
                if(cnt == 1) sum += 1;
                else if(cnt == 2) sum+= 10;
                else if(cnt == 3) sum += 100;
                else if(cnt == 4) sum += 1000;
            }
        }

        System.out.println(sum);
    }

    public static void simulate() {
        for(int s=2; s<=N*N; s++) {
            setPos(s);
        }
    }

    public static void setPos(int s) {
        PriorityQueue<Student> pq = new PriorityQueue<Student>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] != 0) continue;
                pq.add(getStudent(i, j, s));
            }
        }

        int x = pq.peek().x;
        int y = pq.peek().y;
        map[x][y] = student[s];
    }

    public static Student getStudent(int x, int y, int s) {
        int cnt = 0;
        int emptyCnt = 0;

        for(int i=0; i<4; i++) {
            int nx = x + dist[i][0];
            int ny = y + dist[i][1];

            if(!isIn(nx, ny)) continue;
            if(list[student[s]].contains(map[nx][ny])) cnt++;
            if(map[nx][ny] == 0) emptyCnt++;
        }

        return new Student(x, y, cnt, emptyCnt);
    }
    public static boolean isIn(int x, int y){
        return 0<=x && x<N && 0<=y && y<N;
    }
}