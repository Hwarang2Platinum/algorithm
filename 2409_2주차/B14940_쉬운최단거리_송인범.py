import sys
from collections import deque
input = sys.stdin.readline

dx = [0, 0,1,-1]
dy = [1,-1, 0, 0 ]
# 0, 0 시작, BFS
deq = deque()

n, m = map(int, input().split())
maps = []
isvisit = [[False] * m for _ in range(n)]

for i in range(n):
    maps.append(list(map(int, input().split())))
    for j in range(m):
        if maps[i][j] == 2:
            deq.append((i, j))
            maps[i][j] = 0
            isvisit[i][j] = True

time = 1
while deq:

    length = len(deq) 

    for i in range(length):
        x, y = deq.popleft()        

        for j in range(4):
            ax = x+ dx[j]
            ay = y+ dy[j]

            if ax < 0 or ay < 0 or ax >= n or ay >= m or isvisit[ax][ay] or maps[ax][ay] == 0: continue
            
            isvisit[ax][ay] = True
            maps[ax][ay] = time
            deq.append((ax, ay))

        
    time+=1

for i in range(n):
    for j in range(m):
        if maps[i][j] == 1 and not isvisit[i][j]:
            maps[i][j] = -1

    print(*maps[i])