import sys
input = sys.stdin.readline


isPrime = [True] * 1000001
isPrime[0] = isPrime[1] = False
primes = []
wrong_answer = "Goldbach's conjecture is wrong."

# simple idea
for i in range(2, 1000001):
    if isPrime[i]:

        for j in range(2*i, 1000001, i):
            isPrime[j] = False


while True:
    n = int(input())
    if(n == 0): break

    for i in range(3, n//2+1, 2):
        if isPrime[i] and isPrime[n-i]:
            print(f'{n} = {i} + {n-i}')
            break

    else:
        print(wrong_answer)

    



