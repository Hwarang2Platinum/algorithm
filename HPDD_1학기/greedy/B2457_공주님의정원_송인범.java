package feburary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B2457_공주님의정원_송인범 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 정답 0으로 초기화
        int answer = 0;
        int N = Integer.parseInt(br.readLine());
        List<Project> li = new ArrayList<>();

        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int a3 = Integer.parseInt(st.nextToken());
            int a4 = Integer.parseInt(st.nextToken());
            // 시작일 , 종료일 : 간편화
            int start =  a1*100+a2;
            int end =  a3*100+a4;
            li.add(new Project(start, end));
        }
        // 정렬
        Collections.sort(li);

        int idx = 0;
        int now = 0;
        int target = 1201;
        int current = 301; // 시작일 계속 업데이트
        
        while (current < target) {
            boolean isFind = false;
            
            for(int i = idx; i<N; i++) {
            	if(li.get(i).start > current) {
            		// 뽑은 것이 시작일 보다 클 수 가 없다.
            		break;
            	}
            	
            	if(now < li.get(i).end) {
            		isFind = true;
            		now = li.get(i).end;
            		// 이후부터 찾아야 된다.
            		idx++;
            	}
            }
            
            if(isFind) {
            	current = now;
            	++answer;
            }
            else {
            	break;
            }
           
        }
        if(now < target)System.out.println(0);
        else System.out.println(answer);
      
    }

    public static class Project implements Comparable<Project> {
    	int start;
    	int end;

       

        public Project(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
        public int compareTo(Project o) {
			if(this.start == o.start)return Integer.compare(o.end, this.end);
            return Integer.compare(this.start, o.start);
        }
    }
}