import sys
input = sys.stdin.readline

# 거짓말 하는 사람의 가능한 갯수를 구하는 문제
# 풀이 참고
# 아이디어가 퍼킹하네요
n = int(input())
people = list(map(int, input().split()))
up =  [0] * (n+1)
down = [0] * (n+1)
prefix_up = [0] * (n+1)
prefix_down = [0] * (n+1)

# 3명 이하가 거짓말 = 2명 이하가 거짓말 누적합 + 3명 이하가 거짓말의 수
for idx in people:
    if idx > 0 :
        up[idx]+=1
    else:
        down[-idx]+=1

# 아래 쪽 부터 생각 해보자 
prefix_down[0] = 0 # 0명 이하 
prefix_up[n] = 0 # n명 이상 

for i in range(1, n+1):
    prefix_down[i] = prefix_down[i-1] + down[i-1]

for i in range(n-1, -1, -1):
    prefix_up[i]=  prefix_up[i+1] + up[i+1]


check = []
for i in range(n+1):
    # ex) 거짓말 친사람이 3명이면 거짓말 친사람이 2, 1, 0, 4.. 등등으로 추측한 사람들이 거짓말을 친사람들
    # 그 수의 합이 3이 되어야한다. 
    if prefix_down[i] + prefix_up[i] == i:
        check.append(i)


print(len(check))
print(*check)



        
