import sys
input = sys.stdin.readline

# 점수 따먹기 게임 개발
# n x m (-10000 <= x <= 10000)
# 이차 누적합
answer = -9999999999
n, m = map(int , input().split())
block = [list(map(int, input().split()))for _  in range(n)]
prefix = [[0] * (m+1) for _ in range(n+1)]

# prefix sum
for i in range(1, n+1):
    for j in range(1, m+1):
        prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + block[i-1][j-1]

# 연속된 부분 수열 계산 
for i in range(1, n+1):
    for j in range(1, m+1):
        for k in range(i, n+1):
            for l in range(j, m+1):
                answer = max(answer, prefix[k][l] - prefix[k][j-1] -  prefix[i-1][l] + prefix[i-1][j-1])

print(answer)