package March;

public class P_타겟넘버_송인범 {
   static int answer = 0;
	    
    static void dfs(int [] numbers, int target, int total, int idx){
        if(idx == numbers.length&& total == target){
            answer +=1;
            return;
        }
            
        else if(idx >= numbers.length) return;
        
        dfs(numbers, target, total+numbers[idx], idx+1);
        dfs(numbers, target, total-numbers[idx], idx+1);
        
        
    }
	
	public static void main(String[] args) {
		 dfs(new int[] {1,1,1,1,1}, 3, 0 , 0);
	        
	        
	     System.out.println(answer);
	}
}	