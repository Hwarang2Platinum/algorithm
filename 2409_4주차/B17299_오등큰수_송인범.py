import sys
input = sys.stdin.readline

# 각 원소에 오등큰수를 구한다.
# 오등큰수는 수열에서 오른쪽만 확인
# 등장 횟수가 가장 큰 왼쪽에 있는 수 
N = int(input())
arr = list(map(int, input().split()))

# 모든 수의 카운팅이 필요하다.
nums = dict()
for i in range(N):
    if arr[i] in nums:
        nums[arr[i]] += 1
    else:
        nums[arr[i]] = 1

result = [-1] * N
stack = []

for i in range(N):
    while stack and nums[arr[stack[-1]]] < nums[arr[i]]:
        index = stack.pop() # stack  값을 찾는다.
        result[index] = arr[i]
        
    stack.append(i)

print(*result)
