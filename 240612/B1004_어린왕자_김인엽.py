from math import sqrt
import sys
input = sys.stdin.readline

T = int(input())

# 두 점 사이 거리
def calc_dist(x1, y1, x2, y2):
    return sqrt((x1-x2)**2 + (y1-y2)**2)

for _ in range(T):
    x1, y1, x2, y2 = map(int, input().split())
    n = int(input())

    circles = []
    for i in range(n):
        cx, cy, r = map(int, input().split())
        circles.append((cx, cy, r))

    result = 0
    for circle in circles:
        x, y, r = circle
        # 원의 중심과 시작점, 끝점 거리 보고
        # 1. 사이 거리가 반지름보다 크면, 원 바깥에 있으니 알빠노
        # 2. 사이 거리가 반지름 안에 있으면, 원 안에 있으니 카운트 + 1
        # 2.1 만약 둘 다 반지름 안에 있으면, 같은 원이니 카운트 + 1 안 함

        # 시작점과 거리
        start_dist = calc_dist(x1, y1, x, y)
        # 끝점과 거리
        end_dist = calc_dist(x2, y2, x, y)

        if start_dist < r and end_dist < r:
            continue
        elif start_dist < r or end_dist < r:
            result+=1
    print(result)