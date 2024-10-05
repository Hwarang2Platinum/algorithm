from itertools import combinations
import sys
input = sys.stdin.readline

# 시간 초과 코드..
# 조합 부분에서 문제가 생기는 것 같다.
n = int(input())
node = [i for i in range(1, n+1)]
cnt = 0

def DFS(graph, path, start, end, isvisit):
    if start ==  end : return path
    isvisit[start] = True

    for idx in graph[start]:
        if not isvisit[idx]:
            what = DFS(graph, path + [idx], idx, end, isvisit)
            if what: return what
    
    return None
    

# 간선은 n-1개
# 1호선 1 -> N 경로
graph = [[]*(n+1) for i in range(n+1)]
for _ in range(n-1):
    u, v = map(int, input().split())  
    graph[u].append(v) 
    graph[v].append(u)

isvisit = [False] * (n+1)
real = DFS(graph, [1], 1, n, isvisit)
real = set(real)

candidate =  [idx for idx in range(1, n+1) if idx not in real]

lists = list(combinations(candidate, 2))

for s,e in lists:
    isvisit = [False] * (n+1)
    path = DFS(graph, [s], s, e, isvisit)
    if path:
        if any(idx in real for idx in path):
            cnt+=1

print(cnt)