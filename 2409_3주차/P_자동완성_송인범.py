# Trie 사용 문제 : 시간복잡도 O(M) 
# M : 문자열 길이
# 연습문제
# 트라이는 트리를 쓰는 알고리즘 형식

class Node(object):
    def __init__(self):
        self.children = dict()  
        self.count = 0
        
class Trie(object):
    def __init__(self):
        self.head = Node()
        
    def insert(self, ngram):
        curr_node = self.head  # 아무것도 없는 노드 생성 
        
        for d in ngram:
            if d not in curr_node.children:
                curr_node.children[d] = Node()  # 기존에 없던 자식이면 생성
                
            curr_node = curr_node.children[d] # 자식으로 이동
            curr_node.count +=1
            
        
    def search(self, ngram):
        curr_node = self.head
        num = 0
        
        for char in ngram:
            curr_node = curr_node.children[char]
            num +=1
            if curr_node.count == 1: break # 뒤에 없으면 종료
        
        return num  # 고유 문자의 수를 반환
    

def solution(words):
    answer = 0
    # 길이 순으로 정렬
    
    data = Trie()
    for word in words:
        data.insert(word)
        
    for word in words:
        answer += data.search(word)
        
    return answer