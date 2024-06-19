import sys
input = sys.stdin.readline

# 힌트 참조

def find(n, parent):
    if n != parent[n]:
        parent[n] = find(parent[n], parent)
    return parent[n]

def union(x, y, parent):
    x = find(x, parent)
    y = find(y, parent)
    if x < y:
        parent[x] = y
    else:
        parent[y] = x

n, m = map(int, input().split())
edge = [tuple(map(int,input().split())) for _ in range(m+1)]
best, worst = n, 0

best_parent = [i for i in range(n+1)]
worst_parent = [i for i in range(n+1)]


for a, b, w in edge:
    if w == 1:
        a = find(a, best_parent)
        b = find(b, best_parent)    
        if a != b: # 하나는 오르막이고 하나는 내리막 속성
            best -=1
            union(a, b, best_parent)
    else:
        a = find(a, worst_parent)
        b = find(b, worst_parent)
        if a != b:
            worst += 1
            union(a, b, worst_parent)


print(worst**2 - best**2)