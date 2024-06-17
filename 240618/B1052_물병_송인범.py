import sys
input = sys.stdin.readline

n, k = map(int , input().split())
# 물병은 구매 가능
# 그리디 하게 생각을 접근해보자 k개를 넘지 않는다. 
# 그리고 모든 갯수를 통해 정답이 되려면 2의 제곱승이 나와야한다. 4 -> 2 -> 1 , 8 -> 4 -> 2 -> 1 등 
# 즉 1이 하나가 나머지가 다 0이 되어야한다. 그러나 k개 보정이 들어간다.
one = bin(n).count('1')
cnt = 0
temp = n 
# k보다 같거나 작으면 보정 후 고칠 필요가 없다. 하나 그냥 뺀거랑 마찬가지
if one <= k:
    print(0)
    exit()

else:
    while True:
      if one <= k:
            break
      temp +=1
      one = bin(temp).count('1')
        
    cnt = temp - n
    print(cnt)