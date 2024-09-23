/**
트라이! 답봤습니다..
**/
import java.util.*;

class Solution {
    static int answer = 0;
    
    static class Node {
        Map<Character, Node> children; // 자식 노드(문자별로)
        int count; // 이 노드를 거친 단어가 몇 개인지
        
        Node() {
            children = new HashMap<>();
        }
    }
    
    static void insert(Node cur, String s) { // 트라이 트리에 문자열 삽입
        for(char c: s.toCharArray()) { // 순차적으로 읽기
            // 해당 문자가 자식 노드에 있는지 확인 -> 없으면 새로운 노드 생성해서 넣기
            cur = cur.children.computeIfAbsent(c, l -> new Node());
            cur.count++; // 지나간 카운트 횟수 증가
        }
    }
    
    static void check(Node cur, String s) { // 각 단어 검사
        for(char c : s.toCharArray()) {
            cur = cur.children.get(c); // 트리 따라가면서 해당 문자 포함 노드로 이동 & answer 증가
            answer++;
            if(cur.count == 1) return; // 이후는 안 겹치기 때문에 1나오면 바로 종료
        }
    }
    
    public int solution(String[] words) {
        Node root = new Node();
        for(String word : words) {
            insert(root, word);
        }
        
        for(String word : words) {
            check(root, word);
        }
        return answer;
    }
}