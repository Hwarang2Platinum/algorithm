import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

v = int(input())
node = [[] for i in range(v+1)]

def dfs(n, v):
    for nextnode, weight in node[n]:
        if(length[nextnode] == -1):
            length[nextnode] = weight + v
            dfs(nextnode, weight + v)
            



for i in range(v):
    arr = list(map(int , input().split()))
    for e in range(1, len(arr)-2, 2):
        node[arr[0]].append((arr[e], arr[e+1]))
        node[arr[e]].append((arr[0] ,arr[e+1]))

length  = [-1] * (v+1)
length[1] = 0
dfs(1,0)

maxlen = length.index(max(length))
length  = [-1] * (v+1)
length[maxlen] = 0
dfs(maxlen, 0)
print(max(length))