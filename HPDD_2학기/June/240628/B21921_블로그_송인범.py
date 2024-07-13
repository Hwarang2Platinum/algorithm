import sys
input = sys.stdin.readline

n, x = map(int, input().split())
poo = list(map(int, input().split()))
prefix = sum(poo[:x])
maxnum = prefix
cnt = 1

for idx in range(x, n):
    prefix = prefix - poo[idx - x] + poo[idx]

    if prefix > maxnum :
        maxnum = prefix
        cnt = 1
    
    elif prefix == maxnum:
        cnt+=1


if maxnum == 0 :
    print("SAD")
else:
    print(maxnum)
    print(cnt)