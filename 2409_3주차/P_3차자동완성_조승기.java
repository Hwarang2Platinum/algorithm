import java.util.*;

class Solution {
    class Node {
        public HashMap<Character, Node> childs = new HashMap<>();
        public int count;
    }
    class Trie {
        Node root;
        
        Trie() {
            root = new Node();
        }
        
        void insert(String word) {
            Node node = root;
            
            for(int i = 0; i < word.length(); i++) {
                node.count++;
                node = node.childs.computeIfAbsent(word.charAt(i), c -> new Node());
            }
            node.count++;
        }
        int query(String word) {
            Node node = root;

            int length = word.length();
            int last = length;

            for(int i = 0; i < length; i++) {
                if(node.count == 1) {
                    last = i;
                    break;
                }
                node = node.childs.get(word.charAt(i));
            }
            return last;
        }
    }
    
    
    public int solution(String[] words) {
        Trie trie = new Trie();
        for(String str: words)
            trie.insert(str);
        
        int ans = 0;
        for(String str: words)
            ans += trie.query(str);
        
        return ans;
    }
}