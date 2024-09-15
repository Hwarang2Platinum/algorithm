package test;

public class P_네트워크 {

    static int answer = 0;
    static boolean[] visited;

	public static void main(String[] args) {
		int[][] pcs = {{1,1,0}, {1,1,0}, {0,0,1}};
		int ans = solution(3, pcs);
		System.out.println(ans);
	}

    public static int solution(int n, int[][] pcs) {
        visited = new boolean[pcs.length];
        for (int i = 0; i < pcs.length; i++){
            if (!visited[i]){
                answer++;   
                dfs(i, pcs);    
            }   
        }
        return answer;
    }


    private static void dfs(int num, int[][] pcs) {
        visited[num] = true;
        for (int i = 0; i < pcs.length; i++){
            if (!visited[i] && pcs[num][i] == 1) dfs(i, pcs);
        }
    }
}
