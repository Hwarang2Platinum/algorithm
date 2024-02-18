package feburary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2140_지뢰찾기_송인범 {
	 static int[] dx = {1, 1, 1, -1, -1, -1, 0, 0};
	 static int[] dy = {0, -1, 1, 0, 1, -1, 1, -1};

	// greedy하게 접근하는 것을 생각한다
	// 처음 방법은 폭탄 가능성의 요소를 모두 구한후 갯수가 0인 block 근처의 폭탄 가능성을 제외하는 방식
	// 예외를 찾다가.. 해결 힌트를 봐버리고 말았다.
	// #을 중심으로 주변에 0이 없을시 greedy하게 폭탄이 있을거라 검증하는 방식이다.
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        int bomb = 0;

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int flag = 0;
                if (board[i][j] == '#') {
                    for (int k = 0; k < 8; k++) {
                        int ax = i + dx[k];
                        int ay = j + dy[k];
                        if (ax >= 0 && ax < N && ay >= 0 && ay < N && board[ax][ay] == '0') {
                            flag = 1;
                            break; // 주변에 지뢰가 있으면 flag를 1로 설정하고 반복문 종료
                        }
                    }

                    if (flag == 0) {
                        for (int k = 0; k < 8; k++) {
                            int ax = i + dx[k];
                            int ay = j + dy[k];
                            if (ax >= 0 && ax < N && ay >= 0 && ay < N && board[ax][ay] != '#') {
                                int numericValue = Character.getNumericValue(board[ax][ay]);
                                board[ax][ay] = (char) ('0' + numericValue - 1);     
                            }
                        }
                        bomb++;
                    }
                }
            }
        }

        System.out.println(bomb);
    }
}
