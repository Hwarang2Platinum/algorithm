import sys

input = sys.stdin.readline
max_oc = 1e18
epsilon = 1e-7 # 제일 핵심 -> 0.0000001 

# 백준 마스터 분 꺼 참고
# 제약 조건
# 남은 시간, 위치, 속도: 1부터 10억까지
# 남은 시간은 소수점 넷째자리까지의 실수

n, t = map(float, input().split())
n = int(n)

loc = list(map(int, input().split()))
speed = list(map(int, input().split()))

left, right = -max_oc, max_oc

for i in range(int(n)):
    left = max(left, loc[i] - speed[i] * t) 
    right = min(right, loc[i] + speed[i] * t)

if left < right + epsilon: # 실수 계산 보정 작업
    print(1)
else:
    print(0)