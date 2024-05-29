import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class B21608_상어초등학교_송인범 {
    static StringTokenizer st;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int now;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];
        HashMap<Integer, Integer[]> record = new HashMap<>();
        List<Integer[]> list = new ArrayList<>();

        for (int i = 0; i < N * N; i++) {
            list.clear();
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            int f3 = Integer.parseInt(st.nextToken());
            int f4 = Integer.parseInt(st.nextToken());
            record.put(num, new Integer[]{f1, f2, f3, f4});

            for (int rows = 0; rows < N; rows++) {
                for (int cols = 0; cols < N; cols++) {
                    int cnt1 = 0;
                    int cnt2 = 0;

                    for (int j = 0; j < 4; j++) {
                        int ax = rows + dx[j];
                        int ay = cols + dy[j];

                        if (ax >= 0 && ay >= 0 && ax < N && ay < N) {
                            now = grid[ax][ay];
                            if (now == f1 || now == f2 || now == f3 || now == f4) cnt1++;
                            if (now == 0) cnt2++;
                        }
                    }
                    list.add(new Integer[]{cnt1, cnt2, rows, cols});
                }
            }
           
            Collections.sort(list, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    for (int idx = 0; idx < 4; idx++) {
                        int result = o2[idx].compareTo(o1[idx]);
                        if (result != 0) {
                            return result;
                        }
                    }
                    return 0;
                }
            });
            
            //System.out.println(Arrays.deepToString(grid));

            for (int idx = 0; idx < list.size(); idx++) {
                int x = list.get(idx)[2];
                int y = list.get(idx)[3];
                if (grid[x][y] == 0) {
                    grid[x][y] = num;
                    break;
                }
            }
        }

        int satisfaction = 0;

        for (int rows = 0; rows < N; rows++) {
            for (int cols = 0; cols < N; cols++) {
                int token = 0;
                Integer[] compare = record.get(grid[rows][cols]);

                for (int j = 0; j < 4; j++) {
                    int ax = rows + dx[j];
                    int ay = cols + dy[j];

                    if (ax >= 0 && ay >= 0 && ax < N && ay < N) {
                        int nows = grid[ax][ay];
                        if (nows == compare[0] || nows == compare[1] || nows == compare[2] || nows == compare[3]) {
                            token++;
                            
                        }
                    }
                }

                if (token == 1) {
                    satisfaction += 1;
                } else if (token == 2) {
                    satisfaction += 10;
                } else if (token == 3) {
                    satisfaction += 100;
                } else if (token == 4) {
                    satisfaction += 1000;
                }
            }
        }

        System.out.println(satisfaction);
    }
}
