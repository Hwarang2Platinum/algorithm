import sys
input = sys.stdin.readline

poo = input().rstrip()

def zoac(words, idx):
    global result

    if words == "":
        return
    
    x = min(words)
    x_idx = words.index(x)

    # 문자 저장
    result[idx+x_idx] = x
    print(''.join(result))

    # 무조건 뒤부터 실행, 위에서 제일 작은것을 찾았기 때문
    zoac(words[x_idx+1 :], x_idx+ idx +1)
    zoac(words[:x_idx], idx)


# 재귀를 사용해라
# 제일 작은 거 출력
result = [''] * len(poo)
zoac(poo, 0)