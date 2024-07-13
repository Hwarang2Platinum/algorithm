import sys
input = sys.stdin.readline
INF = int(1e7)


# 벨만포드
# 시간 초과 계속 뜸, 모든 노드들을 다 뒤지려고 함
def bf(start, n):
    dist = [INF] * (n + 1)
    dist[start] = 0

    for idx in range(n):
        for u, v, w in route:
           if dist[u] + w < dist[v]:
                if idx == n - 1: 
                    return True
                    # 마지막 확인
                dist[v] = dist[u] + w
    return False

tc = int(input())
for _ in range(tc):
    n, m, w = map(int, input().split())
    # 지점 수: n, 도로 수: m, 웜홀 수: w
    route = []

    for _ in range(m):
        s, e, t = map(int, input().split())
        route.append((s, e, t))
        route.append((e, s, t))

    for _ in range(w):
        s, e, t = map(int, input().split())
        route.append((s, e, -t))

    flag = bf(1, n)

    print("YES" if flag else "NO")