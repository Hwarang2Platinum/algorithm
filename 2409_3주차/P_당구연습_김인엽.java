class Solution {
    static int n, m, curX, curY;
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        for (int i = 0; i < balls.length; i++) {
			int targetX = balls[i][0];
			int targetY = balls[i][1];

			int curlen, len = Integer.MAX_VALUE;

			// 좌(타겟의 수평으로 오른쪽에 있을 때 제외)
			if (!(startY == targetY && startX >= targetX)) {
				curlen = getDistance(startX, startY, targetX * (-1), targetY);
				len = Math.min(curlen, len);
			}

			// 우(타겟의 수평으로 왼쪽에 있을 때 제외)
			if (!(startY == targetY && startX <= targetX)) {
				curlen = getDistance(startX, startY, m + (m - targetX), targetY);
				len = Math.min(curlen, len);
			}

			// 상(타겟의 아래에 있을 때 제외)
			if (!(startX == targetX && startY <= targetY)) {
				curlen = getDistance(startX, startY, targetX, n + (n - targetY));
				len = Math.min(curlen, len);
			}

			// 하(타겟의 위에 있을 때 제외)
			if (!(startX == targetX && startY >= targetY)) {
				curlen = getDistance(startX, startY, targetX, targetY * (-1));
				len = Math.min(curlen, len);
			}
			
			answer[i] = len;
		}
        
        return answer;
    }
    
    
    public int getDistance(int sx, int sy, int tx, int ty) {
		return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
	}
}
