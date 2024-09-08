import sys, math, heapq
input = sys.stdin.readline

def distance(x1, y1, x2, y2):
    return math.sqrt((x1-x2)**2 + (y1-y2)**2)

poo = [tuple(map(float, input().split())) for _ in range(2)]

for _ in range(int(input())):
    poo.append(tuple(map(float, input().split())))

# 0.001 초 미만은 정답 그러면 복잡하게 할 필요가 없다
# 대포는 50m, 2초 

length = len(poo)
inf = int(1e11)

graph = [[] for _ in range(length)]

for i in range(length - 1):
    for j in range(i + 1,  length):
        d = distance(poo[i][0], poo[i][1], poo[j][0], poo[j][1]) / 5
        graph[i].append((j, d))
        graph[j].append((i, d))

for i in range(2, length): 
    for j in range(length):
        if i == j:
            continue

        d = 2 + abs(50 - distance(poo[i][0], poo[i][1], poo[j][0], poo[j][1])) / 5
        graph[i].append((j, d))

distance = [inf] * length
distance[0] = 0
pq = [(0, 0)] # 시작점

while pq:
    d, n = heapq.heappop(pq)
 
    if d > distance[n]:
        continue
    
    for nextn, weight in graph[n]:
        dist = d + weight
        
        if dist < distance[nextn]:
            distance[nextn] = dist
            heapq.heappush(pq, (dist, nextn))

print(f'{distance[1]}')