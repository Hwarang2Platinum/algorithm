import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class B14725_개미굴_조승기 {
    static class Node {
        int depth;
        HashMap<String, Node> node = new HashMap<>();
        Node(int depth) {
            this.depth = depth;
        }
        public void addChild(StringTokenizer st) {
            String word = st.nextToken();
            if (node.containsKey(word)) {
                node.get(word).addChild(st);
                return;
            }
            node.put(word, new Node(depth + 1));
            if (st.hasMoreTokens()) {
                node.get(word).addChild(st);
            }
        }

        @Override
        public String toString() {
            StringBuilder dash = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                dash.append("--");
            }
            StringBuilder sb = new StringBuilder();

            List<Map.Entry<String, Node>> entrys = node.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
            for (int i = 0; i < entrys.size(); i++) {
                sb.append(dash).append(entrys.get(i).getKey()).append("\n");
                sb.append(entrys.get(i).getValue());
            }

            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node node = new Node(0);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            node.addChild(st);
        }
        System.out.println(node);
    }
}
