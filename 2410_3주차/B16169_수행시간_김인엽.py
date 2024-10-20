import sys
input = sys.stdin.readline
def solve():
    for i in ranks[1]:
        total_time[i] = times[i]

    for i in range(1,n):
        for node in ranks[i]:
            for next in ranks[i+1]:
                total_time[next] = max(total_time[next],(next-node)**2 + total_time[node] + times[next])


n = int(input())

total_time = [0 for _ in range(n+1)]
times = [0]
ranks = [[] for _ in range(n+1)]

for i in range(1,n+1):
    rank,t = map(int,input().split())
    ranks[rank].append(i)
    times.append(t)

solve()

print(max(total_time))
