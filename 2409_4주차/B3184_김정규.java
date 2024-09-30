package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3184 {

    static int row, col;
    static String[] field;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int sheep;  // o
    static int wolf; // v

    public static void main(String[] args)  throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        field = new String[row];
        
        for (int i = 0; i < row; i++)
            field[i] = br.readLine();
        
        visited = new boolean[row][col];
        
        int wo = 0;
        int sh = 0;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = field[i].charAt(j);
                if (!visited[i][j] && (c != '#')) {
                    sheep = 0;
                    wolf = 0;
                    dfs(i, j);
                    if(sheep > wolf) wolf = 0;
                    else sheep = 0;
                    wo += wolf;
                    sh += sheep;
                }
            }
        }
        System.out.println(sh + " " + wo);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        char cc = field[x].charAt(y);
        
        if(cc == 'v') wolf++;
        if(cc == 'o') sheep++;

        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];
            if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
            if(visited[nx][ny]) continue;
            char c = field[nx].charAt(ny);
            if(c == '#') continue;
            
            dfs(nx, ny);
        }
    }
}
