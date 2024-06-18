import sys
input = sys.stdin.readline

def binary_find(now)    :
    if len(famous_place)==0:
        return -1

    if now in famous_place:
        return 0
    
    front = min((x - now) % n for x in famous_place)
    back = min((now - x) % n for x in famous_place)
    return (min(front, back))

n, q = map(int, input().split())
A = list(map(int, input().split())) # i번째 구역이 명소면 1, 아니면 0 그리고 1로 되서 명소가 아니면 명소 지정, 명소면 지정이 풀린다.
famous_place = set(num+1 for num in A if num == 1)
print(famous_place)

now = 1 # 1번 구역
for idx in range(q): # 도현이는 1번 구역에서 출발
    x = list(map(int, input().split()))
    if x[0] == 1:
        if x[1] not in famous_place:
            famous_place.add(x[1])
        else:
            famous_place.remove(x[1])
    elif x[0] == 2:
        # 시계 방향으로 x 만큼 이동
        now = now + (x[1]%n)
        if (now > n): now %= n

    elif x[0] == 3:
        # 이분 탐색 시작
        what = binary_find(now)
        print("정답은 %d" %what)


