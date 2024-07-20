import sys
input = sys.stdin.readline

def check(trie, num):
    node = trie
    for x in num:
        if node.get("is_end"): return False

        if x not in node:
            node[x] = {"is_end" : False}
        
        node = node[x]
        print(node)

    node["is_end"] = True

    return len(node) == 1



t = int(input())
for _ in range(t):
    n = int(input())

    phone_dict = []
    for i in range(n):
        prefix = input().strip()
        phone_dict.append(prefix)

    flag = True
    trie = {"isEnd": False}
    for idx in phone_dict:
        if not check(trie, idx):
            flag = False
        
    if flag: print("YES")
    else: print("NO")
