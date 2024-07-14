import sys
input = sys.stdin.readline

def isok(maxscore, m):
    cnt = 1
    cnt_min = cnt_max = poo[0]

    for num in poo[1:]:
        cnt_min = min(cnt_min, num)
        cnt_max = max(cnt_max, num)

        if cnt_max - cnt_min > maxscore:
            cnt+=1
            cnt_min = cnt_max  = num


    if cnt > m: return False


    return True

n,m = map(int, input().split())
poo =  list(map(int, input().split()))

left, right = 0, max(poo)-min(poo)

while left < right :
    mid = (left+right) // 2

    if isok(mid, m):
        right = mid

    else:
        left = mid+1

print(left)