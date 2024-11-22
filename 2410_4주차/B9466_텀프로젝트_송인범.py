import sys
input = sys.stdin.readline
# 재귀사이즈 크기 늘려야함.
sys.setrecursionlimit(10**6)

def Backtracking(now):
    global cnt
    # 우선 현재 체크
    visited[now] = True
    cycle.append(now)
    
    # 방문 기록 존재 시
    if visited[lst[now]] == True:
        if lst[now] in cycle:
            cnt -= len(cycle[cycle.index(lst[now]):])
        return
    
    else:
        Backtracking(lst[now])

n = int(input())
for _ in range(n):
    num = int(input())
    cnt = num
    lst = [0] + list(map(int, input().split()))
    # 백트래킹 문제 
    # 우선 탐색?
    visited = [False] * (num+1)
    # 다 끝났는지?
    for i in range(1, num+1):
        if not visited[i] : 
            cycle = []
            Backtracking(i)

    print(cnt)


