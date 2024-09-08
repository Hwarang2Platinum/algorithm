import sys
input = sys.stdin.readline

# pypy는 실행 가능
# python3 로는 실행 불가, 시간 복잡도 4444ms 4초 아닌가?


def isOk():
    for col in range(n):
        target = col
        for raw in range(h):
            if isVisited[raw][target]:
                target+=1
            elif target > 0 and isVisited[raw][target-1]:
                target -=1
        if target != col: return False
    
    return True

def backtracking(cnt, x, y):
    global result
    if cnt > 3 or result <= cnt: return
    
    elif isOk():
        result = min(result, cnt)
        return
    
    # 백트래킹 구현, 가로선 놓기
    for idx in range(x, h):
        target = y if idx == x else 0 # 한 칸 뛰면 첫번째 부터 가능
        for idx2 in range(target, n-1):
            if  isVisited[idx][idx2] or isVisited[idx][idx2+1]: continue
            if idx2 > 0 and isVisited[idx][idx2-1]: continue # 기준점 왼쪽 오른쪽 탐색 
            isVisited[idx][idx2] = True
            backtracking(cnt+1, idx, idx2+2) 
            isVisited[idx][idx2] = False


n, m, h = map(int, input().split())
isVisited = [[False] * n for _ in range(h)] 
for _ in range(m):
    a,b = map(int , input().split())
    isVisited[a - 1][b - 1]  = True

if m == 0:
    print(0)

else :
    # 로직
    # 되는지 체킹
    # 안되면 백 트래킹(dfs)를 통해 위치 추가
    result = int(1e9)
    backtracking(0, 0 ,0) 
    if result == int(1e9): print(-1)
    else: print(result)


