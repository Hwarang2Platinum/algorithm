import sys
input = sys.stdin.readline

n, m = map(int, input().split())
poo = list(map(int, input().split()))

cnt = 0
total = 0
end = 0 

for start in range(n):
    while end < n and total < m:
        total += poo[end]
        end +=1

    if total == m :
        cnt+=1
    
    # 초과나 같을때 start를 빼준다. 
    total -= poo[start]

print(cnt)