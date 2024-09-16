import sys
from collections import defaultdict
input = sys.stdin.readline

# 같은 초밥 2개 이상 존재
# 1) 회전 초밥 k개 연속 먹을 시 할인 정액 가격 제공
# 2) 쿠폰 발행 무료로 제공, 없으면 새로 제공

n, d, k, c = map(int, input().split())
arr = []
for i in range(n):
    arr.append(int(input()))

result = 0
# 처음부터 끝까지 모든 경우를 다 구하자.
# 근데 중요한 것은 원형으로 연결됬기 때문에 예외 처리가 필요.
# 새롭게 배운 것은 기본 값을 0 으로 설정하는 defaultdict 사용하기
nums = defaultdict(int)


for idx in range(k):
    nums[arr[idx]]+=1

result = len(nums) + (0 if c in nums else 1)


for i in range(1, n):
    nums[arr[i-1]] -=1
    
    if nums[arr[i-1]] == 0: del nums[arr[i-1]]
    # 리사이클 방지
    what = arr[(i +k -1) % n]
    nums[what] +=1

    current = len(nums) + (0 if c in nums else 1)

    result = max(result, current)

print(result)