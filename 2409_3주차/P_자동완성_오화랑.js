/**
 * 자동완성 기능이 구현되어 있는 상태
 * go / gone / guild
 * => go : go까지 모두 입력해야 함 ( gone과 구분이 되지 않기 때문 )
 * => gone : gon까지 입력해야 함 ( go까지는 go와 구분이 되지 않기 때문 )
 * => guild : gu까지 입력해야 함 ( g까지는 다른 word와 구분이 되지 않기 때문 )
 * IMP : 총 입력해야 하는 문자를 구해야 함 ( 현재는 go, gon, gu => 7자 )
 * REQ : 학습과 검색에 사용되는 단어 N개 ( 2 <= N <= 100,000 )
 * RES : 단어들의 길이의 총합 L ( 2 <= N <= 1,000,000)
 * IMP : 문자열 관련 알고리즘을 좀 고려해야 하나? ( KMP, TRIE Data Structure ... )
 * => 아마 TRIE 자료구조를 활용해야 할 것 같다.
 * => 만약 하지 않는다면... 해당 word로 정규표현식을 때려서, 찾아지는 게 1개가 될 때 까지 해야 할 것 같다.
 * => 하지만 100,000개의 탐색 -> 단어의 길이는 평균 10자 => 1자가 늘어날 때 마다, 100,000개 탐색을 해줘야 함 => 100만 탐색 * 10만 => 1000억 탐색
 */
class TrieNode {
  constructor() {
    this.children = {};
    this.endWord = false;
    this.count = 0;
  }
}

class Trie {
  constructor() {
    this.root = new TrieNode();
  }

  insert(word) {
    let node = this.root;
    for (let each of word) {
      if (!node.children[each]) {
        node.children[each] = new TrieNode();
      }
      node.children[each].count += 1;
      node = node.children[each];
    }
    node.endWord = true;
  }

  findUniquePrefix(word) {
    let node = this.root;
    let prefix = '';
    for (let each of word) {
      prefix += each;
      node = node.children[each];
      if (node.count === 1) return prefix.length;
    }
    return prefix.length;
  }
}

function solution(words) {
  var answer = 0;
  const trie = new Trie();
  for (const eachWord of words) trie.insert(eachWord);
  for (const eachWord of words) answer += trie.findUniquePrefix(eachWord);
  return answer;
}
