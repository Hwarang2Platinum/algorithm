def sequence(arr, idx, target, n):
    if len(arr) == target:
        print(*arr)
        return

    for i in range(idx, n + 1):
        arr.append(i)
        sequence(arr, i, target, n)
        arr.pop()

n, m = map(int, input().split())
arr = []

sequence(arr, 1, m, n)