# 연결되어 있는 친구가 몇명인지 체크하는 문제
# 하나로 연결을 시켜야 될때 : 유니온 파인드
# 노드가 숫자가 아닌 문자열이기 때문에 딕셔너리를 활용한다.
# 크기를 위한 딕셔너리 하나 생성

import sys
input = sys.stdin.readline

def find(x):
    if network[x] != x:
        return find(network[x])
    return x

def union(a,b):
    a = find(a)
    b = find(b)
    if a == b:
        return network_size[a]

    if a < b:
        network[b] = a
        network_size[a] += network_size[b]
        return network_size[a]
    else:
        network[a] = b
        network_size[b] += network_size[a]
        return network_size[b]
    
tc = int(input())
for _ in range(tc):
    f = int(input())
    network = dict()
    network_size = dict()
    for i in range(f):
        one, two = input().split()

        if one not in network:
            network[one] = one
            network_size[one] = 1

        if two not in network:
            network[two] = two
            network_size[two] = 1

        print(union(one, two))        