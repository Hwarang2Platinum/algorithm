import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    w = str(input().rstrip())
    k = int(input())
    counting = [[] for _ in range(27)]
    result = []

    for i in range(len(w)):
        num  = ord(w[i]) - ord('a')
        print(num)
        counting[num].append(i) # index를 더한다.
        if len(counting[num]) >= k:
            result.append(w[counting[num][-k]: counting[num][-1]+1])

    if len(result) == 0: print(-1)
    else : 
        result.sort(key=lambda x : len(x))
        print(len(result[0]), len(result[-1]))
