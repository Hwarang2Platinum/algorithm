import sys
from collections import deque
input =  sys.stdin.readline

n , m , k = map(int, input().split())
arr =  [ [0] * m for i in range(n)]
answer =  0
dx = [0,0,1,-1]
dy = [1,-1,0,0]

def bfs(x, y):
    arr[x][y]  = 0
    q = deque()
    q.append((x,y))
    cnt = 0

    while q:
        cnt += 1
        xx, yy = q.popleft()
        
        for i in range(4):
            ax = xx+dx[i]
            ay = yy+dy[i]

            if ax < 0  or ay < 0 or ax >= n or ay >= m or arr[ax][ay] == 0: continue
            arr[ax][ay] = 0
            q.append((ax,ay))

    return cnt
        

for i in range(k):
    x, y = map(int, input().split())
    arr[x - 1][y - 1] = 1


for i in range(n):
    for j in range(m):
        if arr[i][j] == 1:
            answer = max(answer , bfs(i, j))


print(answer)