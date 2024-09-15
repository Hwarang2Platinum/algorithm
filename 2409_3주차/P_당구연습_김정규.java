package test;

public class P_당구연습 {

	public static void main(String[] args) {
		int[][] inputs = {{ 7, 7 }, { 2, 7 }, { 7, 3 }};
		int[] ans = solution(10, 10, 3, 7, inputs);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
	
    public static int[] solution(int width,
                          int height,
                          int startX,
                          int startY,
                          int[][] balls) {
        int[] answer = new int[balls.length];
        for (int i=0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            
            // 상단 반사
            int dist1 = getSquaredDist(startX, startY, targetX, 2*height - targetY);
            // 하단 반사
            int dist2 = getSquaredDist(startX, startY, targetX, -targetY);
            // 좌측 반사
            int dist3 = getSquaredDist(startX, startY, -targetX, targetY);
            // 우측 반사
            int dist4 = getSquaredDist(startX, startY, 2*width - targetX, targetY);
            if (startX == targetX && startY > targetY) {
                answer[i] = Math.min(Math.min(dist1, dist3), dist4);
                continue;
            }
            else if (startX == targetX && startY < targetY) {
                answer[i] = Math.min(Math.min(dist2, dist3), dist4);
                continue;
            }
            else if (startY == targetY && startX > targetX) {
                answer[i] = Math.min(Math.min(dist1, dist2), dist4);
                continue;
            }
            else if (startY == targetY && startX < targetX) {
                answer[i] = Math.min(Math.min(dist1, dist2), dist3);
                continue;
            }
            else {
                answer[i] = Math.min(Math.min(dist1, dist2), Math.min(dist3, dist4));
                continue;
            }
            
        }
        return answer;
    }
    
    private static int getSquaredDist(int x1, int y1, int x2, int y2) {
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

}
