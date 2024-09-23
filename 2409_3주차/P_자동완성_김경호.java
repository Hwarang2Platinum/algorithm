import java.util.*;

class Solution {

    static class Trie{
        List<Trie> children;
        int cnt;
        char c;
        String val;

        public Trie(){
            children = new ArrayList<>();
            val = "";
        }

        public Trie(char c){
            children = new ArrayList<>();
            this.c = c;
            val = "";
        }

        public char curChar(){
            return c;
        }

        public void addVal(String v){
            val = v;
        }
        public void addCnt(){
            this.cnt++;
        }

        @Override
        public String toString(){
            return c+ children.toString() +"";
        }
    }

    public int solution(String[] words) {
        int answer = 0;

        Trie trie = new Trie();

        for(int i=0;i<words.length;i++){
            String word = words[i];

            Trie cur = trie;

            l1: for(int j=0;j<word.length();j++){
                char ch = word.charAt(j);


                for(Trie next: cur.children){
                    if(next.curChar() == ch){
                        cur = next;
                        cur.addCnt();
                        continue l1;
                    }
                }
                Trie tmp = new Trie(ch);
                cur.children.add(tmp);
                cur = tmp;
                cur.addCnt();
                if(j==word.length()-1){
                    cur.addVal(word);
                }
            }

        }
        for(int i=0;i<words.length;i++){
            String word = words[i];

            Trie cur = trie;
            l1: for(int j=0;j<word.length();j++){
                char ch = word.charAt(j);
                // System.out.println(j);
                // System.out.println(cur.cnt + " "+cur.c);
                // System.out.println(cur);
                if(word.equals(cur.val)){
                    System.out.println("equal!!");
                    break;
                }
                if(cur.cnt==1){
                    break;
                }

                for(Trie next: cur.children){
                    if(next.curChar() == ch){
                        cur = next;
                        answer++;
                        continue l1;
                    }
                }
            }
        }
        return answer;
    }
}