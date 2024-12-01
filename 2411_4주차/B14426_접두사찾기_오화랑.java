
/**
 * https://www.acmicpc.net/problem/14426
 * * 접두사 : 문자열 S의 가장 앞에서부터 부분 문자열을 의미함
 * * S = "codeplus" => code, co, codepl, codeplus 등등이 문자열
 * * 총 N개의 문자열로 이루어진 집합 S가 주어짐
 * * M개의 문자열 중, 집합 S에 포함되어 있는 문자열 중 적어도 하나의 접두사 인 것의 개수
 * IMP : Naive 접근법 => 10000 X 10000 X 500 의 접근법이 들어갈 수 밖에 없음.
 * ! 1. 여기서 접두사인지 여부를 체크하는 10000은 줄일 수가 없는 영역
 * ! 2. 따라서 비교 연산의 개수를 줄여야 하는 문제임.
 * IMP : 생각할 수 있는 방법은 Map 방법 => a - z 시작에 대한 Map
 * * => 단순하게 접두사 ( 1 ~ 2 )개만 Hash하는 것은 시간 초과가 발생한다.
 * * => 탐색하는 정보를 조금 활용해야 할 것 같다. ( 탐색 정보를 기억한다..? => 관련이 없다면? )
 * 
 * IMP : Trie 자료 구조를 활용해보는 것은 어떨까? ( 문자열 탐색에 매우 빠른 자료구조 But Memory Problem.. )
 */

import java.io.*;
import java.util.*;

public class B14426_접두사찾기_오화랑 {
    // IMP : Trie 구조의 각 Node에 대한 Class
    // Type 1 : HashMap<Character, Node> => 해당 Node의 자식 Node들에 대한 정보
    // Type 2 : boolean finish => 해당 Node가 문자열의 끝인지 아닌지에 대한 정보
    static class Node {
        HashMap<Character, Node> child;
        boolean finish;

        public Node() {
            this.child = new HashMap<>();
            this.finish = false;
        }
    }

    static class Trie {
        // IMP : Trie 자료 구조 Class => 초기화되면, root Node를 생성한다.
        // ! 모든 Insert, Search 연산의 핵심은 "Node의 위치가 문자열의 위치와 같다는 것"의 전제로 이루어진다.
        Node root;

        public Trie() {
            this.root = new Node();
        }

        // IMP : Insert 연산 => 각 문자열을 Trie에 삽입한다.
        // Type 1 : 각 문자열의 각 문자에 대해, 해당 문자가 현재 Node의 자식 중에 없다면 새로운 Node를 생성한다.
        // Type 2 : 원래 자식 Node인지 새로 생성된 Node인지 여부와 관계 없이, 다음 Node로 이동하여 Type 1 연산을 반복한다.
        public void insert(String eachWord) {
            Node node = this.root;
            for (int i = 0; i < eachWord.length(); i++) {
                char each = eachWord.charAt(i);
                node.child.putIfAbsent(each, new Node());
                node = node.child.get(each);
            }
            node.finish = true;
        }

        // IMP : Search 연산 => 해당 문자열이 Trie에 존재하는지 확인한다.
        // Type 1 : 각 문자열의 각 문자에 대해, 해당 문자가 현재 Node의 자식 중에 없다면 false를 반환한다.
        // Type 2 : 모든 문자열에 대해, 해당 문자가 현재 Node의 자식 중에 있다면, 다음 Node로 이동하여 Type 1 연산을
        // 반복한다.
        public boolean search(String eachWord) {
            Node node = this.root;
            for (int i = 0; i < eachWord.length(); i++) {
                char each = eachWord.charAt(i);
                if (node.child.containsKey(each))
                    node = node.child.get(each);
                else
                    return false;
            }
            return true;
        }

        // IMP : Delete 연산 => 해당 문자열을 Trie에서 삭제한다. ( 조금 복잡하다 )
        // ! Delete 연산의 핵심은 삭제가 가능한 지 여부를 판단하는 것이다.
        public boolean delete(String eachWord) {
            return delete(this.root, eachWord, 0);
        }

        private boolean delete(Node current, String eachWord, int index) {
            // Type 1 : target 문자열의 모든 문자들을 처리한 경우
            if (index == eachWord.length()) {
                // Type 1-1 : target 문자열이 존재하지 않는 경우 => Just false 반환
                if (!current.finish)
                    return false;
                // Type 1-2 : target 문자열이 존재하는 경우 => 해당 문자열을 삭제하고, 자식 Node가 없는 경우 true 반환
                current.finish = false;
                return current.child.isEmpty();
            }

            char each = eachWord.charAt(index);
            Node next = current.child.get(each);

            // Type 2 : target 문자열의 문자가 존재하지 않는 경우 => Just false 반환
            if (next == null)
                return false;

            // Type 3 : 재귀적으로 다음 문자열에 대해 Delete 연산을 수행한다.
            boolean deleteNext = delete(next, eachWord, index + 1);

            // Type 4 : 자식 Node가 없는 경우 => 해당 Node를 삭제한다.
            if (deleteNext) {
                current.child.remove(each);
                return current.child.isEmpty() && !current.finish;
            }
            return false;
        }
    }

    static class Solution {
        int N, M;
        Trie trie = new Trie();

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int correctCount = 0;

            for (int i = 0; i < N; i++)
                trie.insert(input.readLine());

            for (int i = 0; i < M; i++) {
                if (trie.search(input.readLine()))
                    correctCount++;
            }
            System.out.println(correctCount);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}