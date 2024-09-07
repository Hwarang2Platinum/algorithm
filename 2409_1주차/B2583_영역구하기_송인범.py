import sys
from collections import deque
input = sys.stdin.readline


dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

M, N, K = map(int ,input().split())
grid = [[-1] * N for i in range(M)]

for idx in range(K):
    lx, ly, rx, ry = map(int, input().split())
    for i in range(M-ry, M-ly):
        for j in range(lx, rx):
            grid[i][j] = 1

cnt = 0
what = []

# Use BFS

for i in range(M):
    for j in range(N):
        if grid[i][j] == -1:
            q = deque()
            num = 1  
            cnt += 1
            q.append((i, j))  
            grid[i][j] = 1

            while q:
                x, y = q.popleft()

                for idx in range(4):
                    ax = x + dx[idx]
                    ay = y + dy[idx]

                    if ax < 0 or ay < 0 or ax >= M or ay >= N or grid[ax][ay] == 1:
                        continue

                    grid[ax][ay] = 1
                    num += 1
                    q.append((ax, ay))

            what.append(num)

what = sorted(what)
print(cnt)
print(*what)
