import sys
input = sys.stdin.readline


def DFS(now, graph, visit):
    if visit[now]: return
    visit[now] = True
    for neighbor in graph[now]:
        DFS(neighbor, graph, visit)
    return

n, m = map(int, input().split())  
graph = [[] for _ in range(n+1)]  
r_graph = [[] for _ in range(n+1)]  

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)  
    r_graph[b].append(a)

S, T = map(int, input().split())  

fromS = [False] * (n+1)
fromS[T] = True  
DFS(S, graph, fromS)

fromT = [False] * (n+1)
fromT[S] = True  
DFS(T, graph, fromT)

toS = [False] * (n+1)
DFS(S, r_graph, toS)

toT = [False] * (n+1)
DFS(T, r_graph, toT)

count = 0
for i in range(1, n+1):
    if fromS[i] and fromT[i] and toS[i] and toT[i]:     
        count += 1

print(count - 2)  # 집, 회사 제외