# 원 안에 들어가 있으면 무조건 + 가된다
# 즉 시작점, 끝점 모두 원하고 관리

import math

t = int(input())
for _ in range(t):
    answer = 0
    x1, y1, x2, y2 = map(int, input().split())
    n = int(input())
    for _ in range(n):
        cx, cy, r = map(int, input().split())
        isOk = math.sqrt((x1-cx) ** 2 + (y1-cy)** 2)
        isOk2 = math.sqrt((x2-cx) ** 2 + (y2-cy)** 2)
        
        if (isOk < r and isOk2 > r) or (isOk2 < r and isOk > r): answer += 1
    
    print(answer)