from collections import deque

def solution(land):
    n, m = len(land), len(land[0])
    gudung = [[0] * m for _ in range(n) ]
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    def bfs(x, y, gudung_num):
        oil = []
        queue = deque()
        land[x][y] = -1
        gudung[x][y] = gudung_num
        queue.append((x, y))
        oil.append((x, y))
        
        while queue:
            nx, ny = queue.pop()
            
            for i in range(4):
                cx = nx + dx[i]
                cy = ny + dy[i]
                
                if cx < 0 or cx >= n or cy < 0 or cy >= m:
                    continue
                if land[cx][cy] == 1:
                    land[cx][cy] = -1
                    gudung[cx][cy] = gudung_num
                    queue.append((cx, cy))
                    oil.append((cx, cy))
        
        len_oil = len(oil)
        for oil_x, oil_y in oil:
            land[oil_x][oil_y] = len_oil
    
    gudung_num = 1
    for i in range(n):
        for j in range(m):
            if land[i][j] == 1:
                # 석유 덩이 개수 표기
                bfs(i, j, gudung_num)
                gudung_num += 1
    
    answers = [0]*m # 각 열 별 결과
    lasts = [[] for _ in range(m)] # 각 열 별 지나간 석유 덩이
    for i in range(n):
        for j in range(m):
            if land[i][j] == 0:
                continue
            if gudung[i][j] not in lasts[j]:
                answers[j] += land[i][j]
                lasts[j].append(gudung[i][j])
    
    return max(answers)