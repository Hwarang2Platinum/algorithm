# 순열 문제
# 끝에를 삭제하고 더해주는 방식으로 진행
def sequence(arr, idx, target, n):
    if len(arr) == target:
        print(*arr)
        return

    for i in range(idx, n + 1):
        arr.append(i)
        sequence(arr, idx, target, n)
        arr.pop()

n, m = map(int, input().split())
arr = []

sequence(arr, 1, m, n)