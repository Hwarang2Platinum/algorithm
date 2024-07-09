import sys
input = sys.stdin.readline

# 학생 M, 시간의 갯수 : N
# using imos Algorithm 
# Time Complex : O(N + 2Mlog2M)

N = int(input())
imos = [0] * 1000001
for _ in range(N):
    s,e = map(int, input().split())
    imos[s]+=1
    imos[e+1]-=1

# 누적합
for i in range(1, 1000001):
    imos[i] += imos[i-1]
    
Q = int(input())
qlist = list(map(int, input().split()))
for q in qlist:
    print(imos[q])