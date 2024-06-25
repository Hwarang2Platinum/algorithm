import sys
input = sys.stdin.readline

# 1학기 때 푼 문제랑 비슷한 유형
# 함수 정의
def check1():
    tot = -int(1e9)
    for i in range(n):
        for j in range(n - 3):
            sum_val = sum(grid[i][j:j+4])
            tot = max(tot, sum_val)
    
    for i in range(n - 3):
        for j in range(n):
            sum_val = sum(grid[i+k][j] for k in range(4))
            tot = max(tot, sum_val)
    return tot

def check2():
    tot = -int(1e9)
    for i in range(n - 1):
        for j in range(n - 1):
            sum_val = grid[i][j] + grid[i+1][j] + grid[i][j+1] + grid[i+1][j+1]
            tot = max(tot, sum_val)

    return tot

def check3():
    tot = -int(1e9)
    for i in range(n - 1):
        for j in range(n - 2):
            sum1 = grid[i][j] + grid[i][j+1] + grid[i][j+2] + grid[i+1][j+2]
            sum2 = grid[i+1][j] + grid[i+1][j+1] + grid[i+1][j+2] + grid[i][j+2]
            sum3 = grid[i][j] + grid[i+1][j] + grid[i+1][j+1] + grid[i+1][j+2]
            sum4 = grid[i][j] + grid[i+1][j] + grid[i][j+1] + grid[i][j+2]
            tot = max(total, sum1, sum2, sum3, sum4)
    
    for i in range(n - 2):
        for j in range(n - 1):
            sum1 = grid[i][j] + grid[i+1][j] + grid[i+2][j] + grid[i+2][j+1]
            sum2 = grid[i][j] + grid[i][j+1] + grid[i+1][j+1] + grid[i+2][j+1]
            sum3 = grid[i+2][j] + grid[i][j+1] + grid[i+1][j+1] + grid[i+2][j+1]
            sum4 = grid[i][j] + grid[i+1][j] + grid[i+2][j] + grid[i][j+1]
            tot = max(tot, sum1, sum2, sum3, sum4)

    return tot

def check4():
    tot = -int(1e9)
    for i in range(n - 1):
        for j in range(n - 2):
            sum1 = grid[i][j] + grid[i][j+1] + grid[i+1][j+2] + grid[i+1][j+1]
            sum2 = grid[i+1][j] + grid[i+1][j+1] + grid[i][j+2] + grid[i][j+1]
            tot = max(tot, sum1, sum2)
    
    for i in range(n - 2):
        for j in range(n - 1):
            sum1 = grid[i][j] + grid[i+1][j] + grid[i+2][j+1] + grid[i+1][j+1]
            sum2 = grid[i][j+1] + grid[i+1][j+1] + grid[i+2][j] + grid[i+1][j]
            tot = max(tot, sum1, sum2)

    return tot

def check5():
    tot = -int(1e9)
    for i in range(n - 2):
        for j in range(n - 1):
            sum1 = grid[i][j] + grid[i+1][j] + grid[i+2][j] + grid[i+1][j+1]
            sum2 = grid[i][j+1] + grid[i+1][j] + grid[i+2][j+1] + grid[i+1][j+1]
            tot = max(tot, sum1, sum2)
    
    for i in range(n - 1):
        for j in range(n - 2):
            sum1 = grid[i+1][j] + grid[i+1][j+1] + grid[i][j+1] + grid[i+1][j+2]
            sum2 = grid[i][j] + grid[i][j+1] + grid[i][j+2] + grid[i+1][j+1]
            tot = max(tot, sum1, sum2)

    return tot


cnt = 0
while True:
    n = int(input())
    if n == 0: break

    total = -99999999999999
    grid = [list(map(int, input().split()))for _ in range(n)]
    result1 = check1()
    result2 = check2()
    result3 = check3()
    result4 = check4()
    result5 = check5()
    cnt+=1
    total = max(total, result1, result2, result3, result4, result5)
    print(f'{cnt}. {total}')