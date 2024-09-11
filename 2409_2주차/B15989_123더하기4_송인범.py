import sys
input = sys.stdin.readline


# 틀림
# 내 이론은 dp[i] = dp[i-2] + dp[i-3] 이었으나 차례대로 업데이트를 시켜야 했었음.
# 최적 부분 구조의 틀을 띄고 dp를 구성한다.

t = int(input())

dp = [1] * (10001)

for i in range(2, 10001):
    dp[i] += dp[i - 2]

for i in range(3, 10001):
    dp[i] += dp[i - 3]


for _ in range(t):
    n = int(input())

    print(dp[n])
    