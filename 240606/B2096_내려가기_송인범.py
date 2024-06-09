import sys, copy
input = sys.stdin.readline

n = int(input())
dp_max = [list(map(int, input().split())),[0]*3]
dp_min = [copy.deepcopy(dp_max[0]), [0] * 3]


# 직전 3개만 본다?
for i in range(1, n):
    x1, x2, x3 = map(int, input().split())
    
    # 최대값 dp 연산
    dp_max[1][0] = x1 + max(dp_max[0][0], dp_max[0][1])
    dp_max[1][1] = x2 + max(dp_max[0][0], dp_max[0][1], dp_max[0][2])
    dp_max[1][2] = x3 + max(dp_max[0][1], dp_max[0][2])
    
    # 최소값 dp 연산
    dp_min[1][0] = x1 + min(dp_min[0][0], dp_min[0][1])
    dp_min[1][1] = x2 + min(dp_min[0][0], dp_min[0][1], dp_min[0][2])
    dp_min[1][2] = x3 + min(dp_min[0][1], dp_min[0][2])
    
    dp_max[0] = copy.deepcopy(dp_max[1])
    dp_min[0] = copy.deepcopy(dp_min[1])
    
print(max(dp_max[0]), min(dp_min[0]))