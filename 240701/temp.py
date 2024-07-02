import sys
input = sys.stdin.readline
mod = 1000000007

def occ(n):
    if n < 2: return (2 ** n)%mod

    else:
        x = n//2
        if n % 2 == 0:
            return (occ(x)**2)% mod
        
        else:
            return (2 * occ(x) ** 2)% mod


t = int(input())
for _  in range(t):
    n = int(input())
    
    if n < 3: print(1)
    else: print(occ(n-2))