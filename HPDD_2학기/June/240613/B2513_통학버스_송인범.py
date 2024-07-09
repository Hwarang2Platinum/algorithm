import sys, heapq
input = sys.stdin.readline
# n: 아파트 갯수 k: 버스 정원 s: 학교 위치
# 시간 복잡도 때문인가? 우선순위 큐를 다 사용한다..
# 파이썬을 사용하는 코테에서는 thread-safe와는 관련이 없기 때문에 heapq를 사용

# 정원수를 체킹해주면서 값을 계산
def counting(arr):
    score = 0
    tmp = 0
    now = 0
    while arr:
        loc, people = heapq.heappop(arr) 
        # 가장 먼곳부터 체킹..
        tmp = max(-loc, tmp)        
        if now + people <= k:
            now += people
        
        else: # 다 못실을 때 
            score += tmp * 2
            heapq.heappush(arr, (loc, people-(k-now)))
            tmp = now = 0

    # 마지막에 돌아오는 거 
    if tmp : 
        score += tmp * 2
        tmp = now = 0

    return score


left_info = []
right_info = []
n, k, s = map(int , input().split())
for i in range(n):
    location, people = map(int , input().split())
    # 생각 -> 좌, 우로 정렬하고 최대한 큰거 부터 해결? 작은거는 합쳐서 해결
    if location < s: heapq.heappush(left_info,((location - s, people)))
    else: heapq.heappush(right_info, (s - location, people)) # 작은 수부터 정렬

answer = counting(left_info)+counting(right_info)
print(answer)


