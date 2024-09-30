import sys
input = sys.stdin.readline
# 비교가 불가능한 경우를 찾는다. 이전에 풀었던 유형(키 순서)
# 노드를 최대로 설정하고 최대가 아니라면 비교 결과를 알 수 있는 거리라 생각 (Floyd - Warshall)
MAX_NUM = int(1e5)
N = int(input())
M = int(input())

# 6 x 6 크기의 배열
items = [[MAX_NUM]* (N+1) for i in range(N+1)]
for init in range(1, N+1):
    items[init][init] = 1

for i in range(M):
    a,b = map(int, input().split())
    items[a][b] = 1

# Floyd - Warshall 작업
for k in range(1, N+1):
    for i in range(1, N+1):
        for j in range(1, N+1):
            if(items[i][k] + items[k][j] == 2): items[i][j] = 1

for i in range(1, N+1):
    cnt = 0
    for j in range(1, N+1):
        if items[i][j] != 100000 or items[j][i] != 100000:
            cnt+=1

    print(N-cnt)
