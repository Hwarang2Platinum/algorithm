import sys
input = sys.stdin.readline

from collections import deque

dx = [0,0,1,-1]
dy = [1,-1,0,0]

# 재밌는 문제
# BFS를 활용, 존재할 때 마다 바로 BFS를 건다
# Flood-Fill이 이런건가?

r,c = map(int, input().split())
madang = [list(input().rstrip()) for i in range(r)]
isvisit = [[False] * c for i in range(r)]

# #:울타리 .:빈칸 o:양 v:늑대
sheep = 0
wolf = 0

def BFS(i, j):
    queue = deque()
    queue.append([i, j])
    isvisit[i][j] = True
    
    o = 0
    v = 0
    
    if madang[i][j] == 'o':
        o += 1
    elif madang[i][j] == 'v':
        v += 1

    while queue:
        x, y = queue.popleft()
        
        for k in range(4):
            ax = x + dx[k]
            ay = y + dy[k]
            
            if ax < 0 or ax >= r or ay < 0 or ay >= c or isvisit[ax][ay] or madang[ax][ay] == '#': continue
            
            isvisit[ax][ay] = True
            queue.append([ax, ay])

            if madang[ax][ay] == 'o':
                o += 1

            if madang[ax][ay] == 'v':
                v += 1

    global sheep, wolf
    if o > v: sheep += o
    else: wolf += v

for i in range(r):
    for j in range(c):
        if (madang[i][j] == 'o' or madang[i][j] == 'v') and not isvisit[i][j]:
            isvisit[i][j] = True
            BFS(i, j)

print(sheep, wolf)