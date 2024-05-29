import java.io.*;
import java.util.*;


public class B21610_마법사상어와비바라기_오화랑 {
    static class cloud {
        int currx, curry;
        public cloud(int currx, int curry) {
            this.currx = currx;
            this.curry = curry;
        }
    }
    static int waterCount;
    static int[][] Map;
    static boolean[][] selected;
    static Queue<cloud> clouds = new ArrayDeque<>();
    static ArrayList<Integer> haveToAdd = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int size = Integer.parseInt(st.nextToken());
        int optNum = Integer.parseInt(st.nextToken());
        Map = new int[size][size];
        for (int i = 0 ; i < size ; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0 ; j < size ; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clouds.offer(new cloud(size - 1, 0));
        clouds.offer(new cloud(size - 1, 1));
        clouds.offer(new cloud(size - 2, 0));
        clouds.offer(new cloud(size - 2, 1));

        for (int i = 0 ; i < optNum ; i++) {
            st = new StringTokenizer(input.readLine());
            int eachDir = Integer.parseInt(st.nextToken());
            int eachSpeed = Integer.parseInt(st.nextToken());
            haveToAdd = new ArrayList<>();
            selected = new boolean[size][size];
            waterCount = 0;
            moveCloud(eachDir, eachSpeed, size);
        }
        System.out.println(waterCount);
    }
    public static void moveCloud(int dir, int speed, int size) {
        int[][] moveM = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

        // step1 : cloud move -> rain
        speed %= size;
        int currX, currY, nextX, nextY;
        int cloudNum = clouds.size();
        int cloudCount = 0;
        while (cloudNum > cloudCount) {
            cloud temp = clouds.poll();
            currX = temp.currx;
            currY = temp.curry;
            nextX = currX + speed * moveM[dir - 1][0];
            nextY = currY + speed * moveM[dir - 1][1];
            if (nextX < 0) nextX += size;
            if (nextY < 0) nextY += size;
            if (nextX >= size) nextX -= size;
            if (nextY >= size) nextY -= size;
            Map[nextX][nextY]++;
            selected[nextX][nextY] = true;
            clouds.offer(new cloud(nextX,nextY));
            cloudCount++;
        }
        // step2 : waterCount
        int nearX, nearY, nearCount;
        cloudNum = clouds.size();
        cloudCount = 0;
        while (cloudNum > cloudCount) {
            cloud temp = clouds.poll();
            nearCount = 0;
            for (int i = 1 ; i <= 7 ; i += 2) {
                nearX = temp.currx + moveM[i][0];
                nearY = temp.curry + moveM[i][1];
                if (nearX < 0 || nearY < 0 || nearX >= size || nearY >= size) continue;
                nearCount += Map[nearX][nearY] > 0 ? 1 : 0;
            }
            haveToAdd.add(nearCount);
            clouds.offer(temp);
            cloudCount++;
        }
        // step3 : waterCopy
        cloudCount = 0;
        while (!clouds.isEmpty()) {
            cloud temp = clouds.poll();
            Map[temp.currx][temp.curry] += haveToAdd.get(cloudCount++);
        }
        // step4 : make Next Clouds;
        for (int i = 0 ; i < size ; i++) {
            for (int j = 0 ; j < size ; j++) {
                if (Map[i][j] >= 2 && !selected[i][j]) {
                    Map[i][j] -= 2;
                    clouds.offer(new cloud(i,j));
                }
                waterCount += Map[i][j];
            }
        }
    }
}
