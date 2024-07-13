# n개 도시, m개 버스
# 3중 for 문을 이용한 플로이드 - 워셜 
import sys
input = sys.stdin.readline

inf = int(1e9)
n = int(input())
m = int(input())
node = [[inf]*(n+1) for _ in range(n+1)]

for i in range(m):
    start, end, value = map(int, input().split())
    if(node[start][end]!=inf): value = min(value, node[start][end])
    node[start][end] = value


for i in range(1, n+1):
    node[i][i] = 0 # 자신끼리의 거리는 0

for k in range(1, n+1):
    for i in range(1, n+1):
        for j in range(1, n+1):
            node[i][j] = min(node[i][j], node[i][k] +  node[k][j])

for i in range(1, n+1):
    for j in range(1, n+1):
        if node[i][j] == inf :
            print("0", end=" ")
        else :
            print(node[i][j], end=" ")
    print()


