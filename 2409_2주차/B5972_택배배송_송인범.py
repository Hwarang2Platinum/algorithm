import sys, heapq
input = sys.stdin.readline

# 다익스트라 밖에 없다. 시간 1초 
# 현서 : 1, 찬홍이 : N

INF = int(1e8)

n, m  = map(int , input().split())
graph =  [[] for i in range(n+1)]
dist = [INF] * (n+1)

def Dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    dist[start] = 0

    while q:
        distance, now = heapq.heappop(q)

        if dist[now] < distance :  continue

        for next in graph[now]:
            cost = distance + next[1]

            if cost < dist[next[0]]:
                dist[next[0]] = cost
                heapq.heappush(q, (cost, next[0]))


for i in range(m):
    a,b,c = map(int, input().split())
    graph[a].append((b,c))
    graph[b].append((a,c))

Dijkstra(1)

print(dist[n])