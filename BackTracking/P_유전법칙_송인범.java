package March;

public class P_유전법칙_송인범 {
	static String [] wando = new String []{"RR", "Rr", "rr"};
	static String[] solution(int[][] queries) {
			String[] answer = new String[queries.length];
			
			for(int i=0; i<queries.length; i++) {
				if(queries[i][0]=='1')answer[i] = "RR";
				else {
					answer[i] = recursive(queries[i][0], queries[i][1]);
				}
			}
	      
	        return answer;
	    }
	
	static String recursive(int depth, int seq) {
		 if (depth == 1) {
	            return "Rr";
	        }
	        String parent = recursive(depth - 1, (seq - 1) / 4 + 1);
	        if (parent.equals("RR") || parent.equals("rr")) {
	            return parent;
	        }
	        if (seq % 4 == 0) {
	            return "rr";
	        } else if (seq % 4 == 1) {
	            return "RR";
	        } else {
	            return "Rr";
	        }
		
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{3,5}}));
	}
	
	
	

}
