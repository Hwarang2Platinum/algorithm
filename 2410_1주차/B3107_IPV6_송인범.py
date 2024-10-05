import sys
input = sys.stdin.readline

temp = input().rstrip()
idx = 0

if '::' in temp:
    cnt = len(temp.split(':'))
    temp = temp.replace('::', ':' * (10 - cnt))


arr = temp.split(':')
for i in range(len(arr)):
    if len(arr[i])!= 4:
        arr[i] = '0' * (4-len(arr[i])) + arr[i]

new_arr = ':'.join(arr)
print(new_arr)