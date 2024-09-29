def solution(m, n, startX, startY, balls):
    answer = []
    start = [startX, startY]
    for i in range(len(balls)):
        result = 1000001
        end = [balls[i][0], balls[i][1]]
        
        # 계산 문제 
        # 일타 싸피 참고
        if start[0]!=end[0] or start[1] > end[1]: 
            result = min(result, abs(start[0]- end[0])**2 + abs(2 * n- start[1]-end[1])**2)  
            
        if start[0]!=end[0] or start[1] < end[1]: # 우로 보낼때 
            result = min(result,abs(start[0] - end[0])**2 + abs(start[1] + end[1])**2)   
            
        if start[1]!=end[1] or start[0] < end[0]:  
            result = min(result,abs(start[0] + end[0])**2 + abs(start[1]-end[1])**2)    
            
        if start[1]!=end[1] or start[0] > end[0]: 
            result = min(result,abs(2*m-start[0]-end[0])**2 + abs(start[1]-end[1])**2)
        answer.append(result)
    
    return answer