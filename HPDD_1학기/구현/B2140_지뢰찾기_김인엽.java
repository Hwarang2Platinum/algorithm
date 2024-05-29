import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 	11656	84
public class B2140_지뢰찾기_김인엽 {
  static int N;
  static char[][] arr; // 한칸이 뜻하는 지뢰 최대가 3개니 5부터 사용. 5: 지뢰. 6 : 지뢰X
  
  static int[] dx = {-1,-1,-1,0,0,1,1,1};
  static int[] dy = {-1,0,1,-1,1,-1,0,1}; 
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new char[N][N];

    for(int i=0; i<N; i++) {
      String strs = br.readLine();
      for(int j=0; j<N; j++) {
        arr[i][j] = strs.charAt(j);
      }
    }
    
    int answer = 0;
    for(int i=0; i<N; i++) {
    	for(int j=0; j<N; j++) {
    		if(Character.isDigit(arr[i][j])) {
    			check(i, j, (int)arr[i][j] - '0');
    		}
    	}
    }
    
    for(int i=0; i<N; i++) {
    	for(int j=0; j<N; j++) {
    		if(arr[i][j] == '*' || arr[i][j] == '#') answer++;
    	}
    }
    
    System.out.println(answer);
  }

  private static void check(int x, int y, int cur) {
	  for(int i=0; i<dx.length; i++) {
		  int cx = x + dx[i];
		  int cy = y + dy[i];
		  // 벗어나면
		  if(cx < 0 || cx >= N || cy < 0 || cy >= N) continue;
		  // 지뢰를 넣을 수 있다면
		  if(arr[cx][cy] == '#' && cur != 0) {
			  cur--;
			  arr[cx][cy] = '*';
		  } else if(arr[cx][cy] == '*' && cur != 0 ) { // 이미 지뢰가 있다면
			  cur--;
		  } else if(arr[cx][cy] == '#' && cur == 0) {
			  arr[cx][cy] = '-';
		  }
	  }
  }
}