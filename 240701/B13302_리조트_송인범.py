import sys
input = sys.stdin.readline

# 하루 10000원
# 3일 25000원 쿠폰1장
# 5일 37000원 쿠폰2장
# 쿠폰 3장 = 하루 

answer = 0
price = [10000, 25000, 37000]
coupone = [0, 1, 2]
n, m = map(int, input().split())
poo = list(map(int, input().split()))
dp = [n][3]

if n == 1:
    dp[1][0] = dp[1][1] = dp[1][2] = 10000

elif n == 2:
    dp
