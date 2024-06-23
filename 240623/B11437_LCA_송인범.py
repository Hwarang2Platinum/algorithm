import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

# depth 설정 (쉬운 버전)
# 부모 기입
# 두 노드 값 비교 

def dfs(now, depth):
    if isVisited[now]:
        return

    isVisited[now] = True
    depths[now] = depth
    for n in graph[now]:
        if not isVisited[n]:
            parents[n] = now
            dfs(n, depth+1)

    return
        


def lca(a,b):
    while depths[a] != depths[b]:
        if depths[a] > depths[b]:
            a = parents[a]
        else:
            b = parents[b] # 서로 부모를 불러온다 , 트리가 완전 이진트리가 아니기 때문

    while a != b:
        a = parents[a]
        b = parents[b]

    return a

    
N = int(input())
graph = [[] for _ in range(N+1)]
depths = [0]*(N+1)
parents = [0]*(N+1)
isVisited = [False]*(N+1)
for _ in range(N-1):
    a, b = map(int , input().split())
    graph[a].append(b)
    graph[b].append(a)

dfs(1,0)
M = int(input())
for _ in range(M):
    a, b = map(int, input().split())
    print(lca(a,b))