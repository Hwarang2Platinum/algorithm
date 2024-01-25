import java.io.*;
import java.util.*;

public class BOJ15686_CM {
    static int result = Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<Node> houseNum = new ArrayList<>();
    static ArrayList<Node> chickenNum = new ArrayList<>();
    static int N;
    static int M;
    static int[][] arr;
    public static class Node{  // 좌표 저장용
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];


        // 치킨집(2) 을 M개만 남겼을때 거리들의 합의 최소값 찾기

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st1.nextToken());

                if(arr[i][j]==1){
                    houseNum.add(new Node(i,j));
                }
                if(arr[i][j]==2){
                    chickenNum.add(new Node(i,j));
                }
            }
        }
        visited = new boolean[chickenNum.size()];
        dfs(0,0);
        System.out.println(result);
    }

    public static void dfs(int cnt, int start){
        if(cnt == M){
            int sum = 0;
            for (int i = 0; i < houseNum.size(); i++) {
                int min_distance = Integer.MAX_VALUE;
                for (int j = 0; j < chickenNum.size(); j++) {
                    if(visited[j]== true){
                        int distance = Math.abs(houseNum.get(i).x - chickenNum.get(j).x) + Math.abs(houseNum.get(i).y - chickenNum.get(j).y);
                        min_distance = Math.min(min_distance, distance);
                    }
                }
                sum += min_distance;
            }
            result = Math.min(result,sum);
            return;
        }


        for (int i = start; i < chickenNum.size(); i++) {
            if (visited[i] == false) {
                visited[i] = true;   //true인 경우 살아있는 치킨집
                dfs(cnt+1,start+1);
                visited[i] = false;
            }
        }
    }
}
