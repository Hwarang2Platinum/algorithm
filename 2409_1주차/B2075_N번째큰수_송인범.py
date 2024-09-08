import sys, heapq
input = sys.stdin.readline

n = int(input())
arr = [list(map(int, input().split())) for i in range(n)]

# use fixed-size Priority Heap

heap = []
for i in range(n):
    for j in range(n):
        if len(heap) < n:
            heapq.heappush(heap, arr[i][j])
        else:
            if arr[i][j] > heap[0]:
                heapq.heappushpop(heap, arr[i][j])

print(heapq.heappop(heap))