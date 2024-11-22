import sys
input = sys.stdin.readline

temp = 0
arr = []

# 벡터의 외적을 통해 값을 구하는 CCW Algorithm
def ccw(x1, y1, x2, y2, x3, y3):
    return (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)

for i in range(3):
    x,y = map(int, input().split())
    arr.append((x,y))

answer = ccw(arr[0][0], arr[0][1], arr[1][0], arr[1][1], arr[2][0], arr[2][1])

if answer >  0: print(1)
elif answer  <  0 : print(-1)
else: print(0)