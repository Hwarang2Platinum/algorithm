import sys
import heapq

input = sys.stdin.readline
inf = int(1e9)
n,m,x = map(int , input().split())  
graph = [[] for i in range(n+1)]
result = []

for i in range(m):
    a,b,c = map(int,input().split())
    graph[a].append((b,c))

def dijkstra(start):
    dist = [inf] * (n+1)  
    q = []
    heapq.heappush(q, (0, start))
    dist[start] = 0
    while q:
        val, now = heapq.heappop(q)
        if dist[now] < val:
            continue
        for i in graph[now]:
            cost = val + i[1]
            if cost < dist[i[0]]:
                dist[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))

    return dist

for i in range(1, n+1):
    go = dijkstra(i)[x]
    back = dijkstra(x)[i]
    result.append(go + back)

print(max(result))