import java.io.*;
import java.util.*;

// !! Hwarang Problem !!

// 의외로 문제를 처음부터 정확하게 이해했다면, 조금 더 수월하게 풀 수 있는 문제가 아니었나라는 생각이 든다.
// 그림을 보지 않고, 문제를 최대한 이해하려고 하는 마음에 실수가 조금 더 나온 것 같다.
// 1. 방향을 제대로 파악하지 못했던 문제
// 2. 처음에는 1번 턴이 끝나면, Map을 다시 탐색해서 출발 가능한 기물을 선택해서 그것만 반복을 돌 수 있도록 하려고 함 (최적화도 고려하면서)
// => 이렇게 하면 기물들의 상태 변화에 따라, 중간에 움직이지 못하게 되는 기물들이 움직이게 되어 문제가 발생햇었음.
// => 이걸 디버깅하는데, 너무 큰 시간을 쏟게됨. + 아예 접근 방식도 달라지게 되었음.
// 지금 Code는 기물 상태를 관리할 때, List로 계속 관리를 하고 있음.
// => 하나의 Turn이 끝나고, Map을 다시 탐색하면서 Bottom에 있는 기물을 찾아내는 방법은, PQ를 사용해서 기물 번호가 낮은 것을 넣었음
// 이 접근법은 접근 방식도 틀리고 시간적으로도 불리했음.

public class B17780_새로운게임_오화랑 {
    static class pair { // 각 ArrayDeque에 넣을 기물 번호와 방향 정보 Class
        int each, way;

        public pair(int each, int way) {
            this.each = each;
            this.way = way;
        }
    }

    static class piece { // 이동 함수를 돌리기 위한 각 기물에 대한 정보 Class
        int x, y, each, way;
        boolean bot;

        public piece(int x, int y, int each, int way, boolean bot) {
            this.x = x;
            this.y = y;
            this.each = each;
            this.way = way;
            this.bot = bot;
        }
    }

    static class Box { // 각 칸에 어떤 정보가 들어가 있는지에 대한 Class
        int color, way;
        ArrayDeque<pair> eachList;

        public Box(int color, int way, ArrayDeque<pair> eachList) {
            this.color = color;
            this.way = way;
            this.eachList = eachList;
        }
    }

    static Box[][] Map;
    static ArrayList<piece> pieceList;
    static int[][] moveList = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
    static boolean made;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int size = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        Map = new Box[size + 1][size + 1];
        pieceList = new ArrayList<>(N); // !!! 중요 -> 기물 상태함수

        for (int i = 1; i <= size; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 1; j <= size; j++) {
                Map[i][j] = new Box(Integer.parseInt(st.nextToken()), 0, new ArrayDeque<>());
            }
        }

        int x, y, way;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            way = Integer.parseInt(st.nextToken());
            Map[x][y].way = way;
            Map[x][y].eachList.offer(new pair(i, way));
            pieceList.add(new piece(x, y, i, way, true));
        }

        int currX, currY, currWay;
        int nextX, nextY;
        int count = 0;
        while (!made) { // 4개 이상이 만들어질 때까지 반복
            if (count == 1001) { // Count가 1000 이상이면 모두 -1로 처리
                count = -1;
                break;
            }
            for (piece temp : pieceList) { // 각 기물에 대한 반복
                if (!temp.bot) // 기물이 맨 아래에 위치하고 있지 않다면, 움직이지 못한다.
                    continue;
                currX = temp.x;
                currY = temp.y;
                currWay = temp.way;
                nextX = currX + moveList[currWay - 1][0];
                nextY = currY + moveList[currWay - 1][1];
                if (nextX <= 0 || nextY <= 0 || nextX > size || nextY > size || Map[nextX][nextY].color == 2) { // 기물이
                                                                                                                // 반대로
                                                                                                                // 보는 조건
                    Map[currX][currY].eachList.peek().way = temp.way = currWay = changeWay(currWay);
                    nextX += 2 * moveList[currWay - 1][0];
                    nextY += 2 * moveList[currWay - 1][1];
                    if (nextX <= 0 || nextY <= 0 || nextX > size || nextY > size || Map[nextX][nextY].color == 2) {
                        // 반대로 가도 이동하지 못한다면, 방향을 바꾼 상태를 유지하고 continue;
                        continue;
                    } else if (Map[nextX][nextY].color == 1) { // 방향을 바꾼 상태에서 빨간 박스로 이동
                        go(currX, currY, nextX, nextY, 1);
                    } else if (Map[nextX][nextY].color == 0) { // 방향을 바꾼 상태에서 하얀 박스로 이동
                        go(currX, currY, nextX, nextY, 0);
                    }
                } else if (Map[nextX][nextY].color == 1) {
                    go(currX, currY, nextX, nextY, 1);
                } else if (Map[nextX][nextY].color == 0) {
                    go(currX, currY, nextX, nextY, 0);
                }
            }
            count++;
        }
        System.out.println(count);
    }

    public static int changeWay(int currWay) {
        if (currWay == 1)
            return 2;
        else if (currWay == 2)
            return 1;
        else if (currWay == 3)
            return 4;
        else
            return 3;
    }

    // 다른 칸으로 넘어가면서, 기물들이 넘어가는 함수
    public static void go(int currX, int currY, int nextX, int nextY, int mode) {
        ArrayDeque<pair> currQ = Map[currX][currY].eachList;
        ArrayDeque<pair> nextQ = Map[nextX][nextY].eachList;
        pair temp;
        if (mode == 1) {
            while (!currQ.isEmpty()) {
                // ArrayDeque를 활용해서, 양쪽 방향으로 넘어가는 방식을 택했다. 거꾸로 들어가야 하는 빨간 박스는
                // ArrayDeque의 끝 부분에서 나와서, 다음 Queue에 들어간다.
                // 이때 중요한 점은 PieceList -> 기물들의 상태를 갱신해야 한다는 점이다!
                temp = currQ.pollLast();
                pieceList.get(temp.each - 1).x = nextX;
                pieceList.get(temp.each - 1).y = nextY;
                pieceList.get(temp.each - 1).bot = false;
                nextQ.offer(temp);
            }
        } else {
            while (!currQ.isEmpty()) {
                // ArrayDeque의 앞 부분에서 나와서, 다음 Queue에 들어간다.
                temp = currQ.poll();
                pieceList.get(temp.each - 1).x = nextX;
                pieceList.get(temp.each - 1).y = nextY;
                pieceList.get(temp.each - 1).bot = false;
                nextQ.offer(temp);
            }
        }
        Map[currX][currY].way = 0;
        Map[nextX][nextY].way = nextQ.peek().way;
        pieceList.get(nextQ.peek().each - 1).bot = true;
        if (nextQ.size() >= 4)
            made = true;
    }
}
