import sys
from collections import deque
input = sys.stdin.readline

r, c = map(int, input().split())
board = [list(input().strip()) for _ in range(r)]
dir = [(0,1),(1,0),(0,-1),(-1,0)]


def bfs():
    global maxnum
    q = deque()
    v = [[set() for _ in range(c)] for _ in range(r)]    
    maxnum = 1
    q.append((0,0,board[0][0]))
    v[0][0].add(board[0][0])
    while q:
        x, y, what = q.popleft()
        maxnum = max(len(what), maxnum)

        for dx, dy in dir:
            ax, ay = x+ dx, y+ dy
            if ax < 0 or ax >= r or ay < 0 or ay>= c: continue
            if what+board[ax][ay] not in v[ax][ay]:
                q.append((ax,ay,what+board[ax][ay]))
                v[ax][ay].add((what+board[ax][ay]))
                
    return maxnum
maxnum = bfs()
print(maxnum)
