package test;

public class P_3차자동완성 {

	public static void main(String[] args) {
		String[] words = {"go", "gone", "guild"};
		int ans = solution(words);
		System.out.println(ans);
	}

	 public static int solution(String[] words) {
	        int ans = 0;
	        Trie trie = new Trie();
	        for (String word : words) trie.insert(word);
	        for (String word : words) ans += trie.getMinimalPrefixLength(word);
	        return ans;
	    }
	}

	class TrieNode {
	    TrieNode[] children;
	    int count;

	    public TrieNode() {
	        children = new TrieNode[26]; // 알파벳 소문자는 26개
	        count = 0;
	    }
	}

	class Trie {
	    private TrieNode root;

	    public Trie() {
	        root = new TrieNode();
	    }

	    public void insert(String word) {
	        TrieNode node = root;

	        for (int i = 0; i < word.length(); i++) {
	            int idx = word.charAt(i) - 'a';
	            if (node.children[idx] == null) {
	                node.children[idx] = new TrieNode();
	            }
	            node = node.children[idx];
	            node.count++;
	        }
	    }

	    public int getMinimalPrefixLength(String word) {
	        TrieNode node = root;
	        int length = 0;
	        for (int i = 0; i < word.length(); i++) {
	            int idx = word.charAt(i) - 'a';
	            node = node.children[idx];
	            length++;

	            if (node.count == 1) {
	                break;
	            }
	        }
	        return length;
	    }
}
	
// 딕셔너리 마렵네
