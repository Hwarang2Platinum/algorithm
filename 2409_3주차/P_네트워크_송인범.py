# Union-Find or BFS?
# 분류에는 BFS로 되어있기 때문에 유니온 파인드로 접근한다.
# 기억해야할 것 : union, find 함수 구현

def find(x):
    if parent[x] != x: 
        parent[x] = find(parent[x])

    return parent[x]
    
def union(a, b):
    a = find(a)
    b = find(b)
    if a!=b:
        if a < b : parent[b] = a
        else : parent[a] = b
    
def solution(n, computers):
    global parent
    answer = 0
    parent = [i for i in range(n)]
        
    # UNION 수행
    for i in range(n):
        for j in range(i+1, n):
            if computers[i][j] == 1: # 1이면 UNION 수행
                union(i, j)
                
    for i in range(n):
        find(i)
    
    answer = len(set(parent))
    
    
    return answer