import java.io.*;
import java.util.*;
/**
 * NxN 체스판 K개의 말
 * 1번말부터 K번말 순서대로
 * 말은 겹칠 수 있으며 맨아래 말만 이동 가능
 * 이동하려는 칸이
 *      1. 0->흰색칸이면 그칸에 있는 말 위로 올라간다.
 *      2. 1->빨간색칸이면 그 칸에 있는 말에 올라가돼, 순서가 반대로 된다.
 *      3. 2->파란색 or 나가면
 *
 * Arraydeque에서 맨 앞이 맨 아래로 생각해서 풀기
 */
public class Main {
    static class Mal {
        int num;
        int direction;

        public Mal(int num, int direction) {
            this.num = num;
            this.direction = direction;
        }
    }
    static int[][] directions = {{0,1},{0,-1},{-1,0},{1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayDeque<Mal>[][] mal = new ArrayDeque[N][N];
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                mal[i][j] = new ArrayDeque<Mal>();
            }
        }
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            mal[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]
                    .add(new Mal(i,Integer.parseInt(st.nextToken())-1));
        }

        for (int t = 0; t < 1000; t++) {
            A:for (int k = 1; k <= K; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(mal[i][j].isEmpty()) continue;
                        Mal head = mal[i][j].getFirst();

                        // 맨 아래가 K가아니면 즉 자기순번이 아니면 걍 넘김
                        if(head.num==k){
                            int[] dir = directions[head.direction];
                            int nextR = i+dir[0];
                            int nextC = j+dir[1];
                            // 나가거나 파란색인 경우
                            if(nextR>=N || nextR<0 || nextC>=N || nextC<0
                                || board[nextR][nextC] == 2
                            ){
                                head.direction = head.direction==0?1: head.direction==1?0: head.direction==2?3:2;
                                dir = directions[head.direction];
                                nextR = i+dir[0];
                                nextC = j+dir[1];
                            }
                            // 또 파란색이 나온 경우
                            if(nextR>=N || nextR<0 || nextC>=N || nextC<0
                                    || board[nextR][nextC] == 2
                            ){
                                head.direction = head.direction==0?1: head.direction==1?0: head.direction==2?3:2;
                                continue A;
                            }

                            // 흰색의 경우
                            if(board[nextR][nextC] == 0){
                                while(!mal[i][j].isEmpty())
                                    mal[nextR][nextC].addLast(mal[i][j].pollFirst());

                            // 빨간색인 경우
                            } else if (board[nextR][nextC] == 1) {
                                while(!mal[i][j].isEmpty())
                                    mal[nextR][nextC].addLast(mal[i][j].pollLast());
                            }

                            if(mal[nextR][nextC].size()>=4) {
                                System.out.println(t+1);
                                return;
                            }
                            continue A;
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }

}