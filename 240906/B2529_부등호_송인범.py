import sys
input = sys.stdin.readline

N = int(input())
seq = input().split()

maxseq = ''
minseq = ''


def ischecking(idx1, idx2, op): 
    if op == '<':
        return idx1 < idx2
    
    else:
        return idx1 > idx2


# cnt is length of now
# now is string 
def backtracking(cnt, now, isvisit):
    global maxseq, minseq

    # return condition
    if cnt == N+1:
        maxseq = max(maxseq, now)

        if len(minseq) > 0:
             minseq = min(minseq, now)

        else:
             minseq  = now
        return
    
    for i in range(10):
        if not isvisit[i]:
            if cnt == 0 or ischecking(now[-1], str(i), seq[cnt-1]):
                isvisit[i] = True
                backtracking(cnt+1, now + str(i), isvisit)
                isvisit[i] = False
    

isvisit = [False] * 10
backtracking(0, "", isvisit)

print(maxseq)
print(minseq)

