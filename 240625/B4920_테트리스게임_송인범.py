import sys
input = sys.stdin.readline
poo = [
    [(0,0), (0,1), (1,0), (1,1)], # ㅁ
    [(0,0), (0,1), (0,2), (0,3)], # ㅡ
    [(0,0), (1,0), (2,0), (3,0)], # ㅣ
    [(0,0), (0,1), (0,2), (1,0)], 
    [(1,0), (1,1), (1,2), (0,2)],
    [(0,0), (1,0), (1,1), (1,2)], # ㄴ
    [(0,0), (0,1), (0,2), (1,2)], # ㄱ
    [(0,0), (1,0), (2,0), (2,1)],
    [(2,0), (2,1), (1,1), (0,1)],
    [(0,0), (0,1), (1,0), (2,0)], 
    [(0,0), (0,1), (1,1), (2,1)],
    [(0,0), (0,1), (0,2), (1,1)], # ㅜ
    [(1,0), (1,1), (1,2), (0,1)], # ㅗ
    [(0,0), (1,0), (2,0), (1,1)], # ㅏ
    [(1,0), (0,1), (1,1), (2,1)], # ㅓ
    [(1,0), (2,0), (0,1), (1,1)],
    [(0,0), (1,0), (1,1), (2,1)],
    [(1,0), (0,1), (1,1), (0,2)],
    [(0,0), (0,1), (1,1), (1,2)]
]

def find(x, y):
    global total
    for i in range(19):
        result = 0
        for j in range(4):
            try:
                next_x = x+poo[i][j][0] # x 좌표
                next_y = y+poo[i][j][1] # y 좌표
                result += grid[next_x][next_y]
            except IndexError:
                continue
        total = max(total, result)



# 1학기 때 푼 문제랑 비슷한 유형
# 함수 정의


cnt = 1
while True:
    n = int(input())
    if n == 0: break

    total = -9999999999999
    grid = [list(map(int, input().split()))for _ in range(n)]
    for i in range (n):
        for j in range(n):
            find(i, j)

    print(f'{cnt}. {total}')
    cnt+=1

