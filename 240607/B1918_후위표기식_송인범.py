import sys
input = sys.stdin.readline
temp = input().strip()
lst = []
result = ''

for char in temp:
    if char.isupper():
        result += char

    elif char == '(':
        lst.append(char)
    
    elif char == '*' or char == '/':
        while len(lst) > 0 and (lst[-1] == '*' or lst[-1] == '/'):
            result += lst.pop()
        lst.append(char)

    elif char == '-' or char == '+':
        while len(lst) > 0 and (lst[-1] != '('):
            result += lst.pop()
        lst.append(char)

    else:
        while lst and lst[-1] != '(':
            result += lst.pop()
        if lst and lst[-1] == '(':
            lst.pop()           

while lst:
    result += lst.pop()

print(result)