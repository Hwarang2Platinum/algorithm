from collections import defaultdict

def howtogo(route, graph, path):
    global answer
    time = 0
    last = None

    for i in range(len(route) - 1):
        begin = graph[route[i]]
        end = graph[route[i + 1]]
        last = end

        while begin != end:
            if time not in path:
                path[time] = {}
                
            # 초기화시
            if begin not in path[time]:
                path[time][begin] = 0
                
            path[time][begin] += 1
            
            if path[time][begin] == 2:
                answer += 1

            x_diff = begin[0] - end[0]
            y_diff = begin[1] - end[1]

            
            # first in r
            if x_diff != 0:
                if x_diff < 0:
                    begin = (begin[0] + 1, begin[1])
                else:
                    begin = (begin[0] - 1, begin[1])
                    
            
            elif y_diff != 0:
                if y_diff < 0:
                    begin = (begin[0], begin[1] + 1)
                else:
                    begin = (begin[0], begin[1] - 1)

            time += 1

            
    # 다 처리 후 
    if time not in path:
        path[time] = {}
    
    # 갔을때 없으면 초기화
    if last not in path[time]:
        path[time][last] = 0
    
    
    path[time][last] += 1
    # 겹치면
    if path[time][last] == 2:
        answer += 1


def solution(points, routes):
    global answer
    answer = 0
    graph = {}
    path = {}

    for i in range(1, len(points) + 1):
        graph[i] = (points[i - 1][0], points[i - 1][1])

    for route in routes:
        howtogo(route, graph, path)

    return answer