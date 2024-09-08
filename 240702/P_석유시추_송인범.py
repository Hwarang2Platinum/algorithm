from collections import deque

def solution(land):
    dx = [0, -1, 1, 0]
    dy = [1, 0, 0, -1]
    rows = len(land)
    cols = len(land[0])
    result = [0 for _ in range(cols)]
    visited = [[False] * cols for _ in range(rows)]
    
    for idx in range(cols):
        for j in range(rows):
            total = 0
            if land[j][idx] == 1 and not visited[j][idx]:
                mincol = maxcol = idx
                q = deque([(j, idx)])
                visited[j][idx] = True
                
                while q:
                    x, y = q.popleft()
                    total += 1
                    
                    for i in range(4):
                        ax = x + dx[i]
                        ay = y + dy[i]
                        
                        if ax < 0 or ay < 0 or ax >= rows or ay >= cols or visited[ax][ay] or land[ax][ay] == 0:
                            continue
                        
                        visited[ax][ay] = True
                        mincol = min(ay, mincol)
                        maxcol = max(ay, maxcol)
                        q.append((ax, ay))
                
                for i in range(mincol, maxcol+1):
                    result[i] += total
                        
    answer = max(result)
    
    return answer