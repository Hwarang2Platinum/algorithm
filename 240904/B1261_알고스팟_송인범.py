from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def bfs():
    dist = [[-1] * M for _ in range(N)]
    dist[0][0] = 0
    

    # 0-1 Problem
    deq = deque()
    deq.append((0, 0))
    
    while deq:
        x, y = deq.popleft()
        
        for i in range(4):
            ax = x + dx[i]
            ay = y + dy[i]
            
            # important go to left
            if 0 <= ax < N and 0 <= ay < M:
                if miro[ax][ay] == 0 and dist[ax][ay] == -1:
                    dist[ax][ay] = dist[x][y]
                    deq.appendleft((ax, ay))
                    
                # not important go to right
                elif miro[ax][ay] == 1 and dist[ax][ay] == -1:
                    dist[ax][ay] = dist[x][y] + 1
                    deq.append((ax, ay))
    
    return dist[N-1][M-1]

M, N = map(int, input().split())
miro = [list(map(int, input().strip())) for _ in range(N)]

print(bfs())



