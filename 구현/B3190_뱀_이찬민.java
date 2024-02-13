import java.io.*;
import java.util.*;

public class Main {
    static Queue<Position> direction = new LinkedList<>(); // 뱀의 방향 변환 정보 담는 큐
    static Deque<Position> snakeDeque = new ArrayDeque<>(); // 뱀의 정보 담는 큐
    static int snakeDir = 3; //오른쪽으로 시작
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우 순서
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        map[0][0] = -1; // 뱀이 있는 곳은 -1
        snakeDeque.offer(new Position(0, 0)); // 처음엔 뱀 머리, 꼬리 같음

        // 사과 위치 입력받기
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            map[a][b] = 1; // 사과가 있는 곳은 1
        }

        // 뱀의 방향 변환 정보 입력 받기
        int L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());

            String s = st.nextToken();
            int c;
            if(s.equals("L")) // 왼쪽으로 90도 -> 0
                c = -1;
            else
                c = 1; // 오른쪽으로 90도 -> 1

            direction.add(new Position(x, c)); //x초 뒤에 c로 회전
        }

        int time = 0; // 게임 실행 시간

        int X = direction.peek().x; // X초가 끝난뒤에
        int C = direction.poll().y; // C방향으로 90도 회전

        while(true) {
            time++;

            // 머리를 늘려 위치시킬 다음 칸
            int nx = snakeDeque.peekFirst().x + dx[snakeDir];
            int ny = snakeDeque.peekFirst().y + dy[snakeDir];

            // 벽 또는 자기자신의 몸과 부딪힌 경우
            if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1) {
                break; // 게임 끝
            }

            if(map[nx][ny] == 1) { // 사과가 있다면
                map[nx][ny] = -1;
                snakeDeque.offerFirst(new Position(nx, ny)); // 머리 새로운 좌표로 이동
            } else { // 사과가 없다면
                map[nx][ny] = -1; //지나감 표시
                snakeDeque.offerFirst(new Position(nx, ny)); // 머리 새로운 좌표로 이동

                // 꼬리가 위치한 칸 비우기
                map[snakeDeque.peekLast().x][snakeDeque.pollLast().y] = 0; //poll로 삭제
            }
            if(time == X) {
                if(C == -1) { // 왼쪽 90도 방향 전환
                    snakeDir = (snakeDir+1) % 4;
                } else { // 오른쪽 90도 방향 전환
                    snakeDir = (snakeDir+3) % 4;
                }

                if(!direction.isEmpty()) { // 큐에 방향 변환 정보가 있다면 갱신
                    X = direction.peek().x;
                    C = direction.poll().y;
                }
            }
        }

        System.out.println(time);

    }

}

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}