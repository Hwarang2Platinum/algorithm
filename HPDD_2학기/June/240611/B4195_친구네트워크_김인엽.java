import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 유니온 파인드
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int test_case=0; test_case < T; test_case++) {
            int F = Integer.parseInt(br.readLine());

            // 부모 기록
            Map<String, String> parent = new HashMap<>();
            // 사이즈 기록(정답)
            Map<String, Integer> size = new HashMap<>();

            StringTokenizer st;
            for(int i=0; i<F; i++) {
                st = new StringTokenizer(br.readLine());
                String user1 = st.nextToken();
                String user2 = st.nextToken();

                if(!parent.containsKey(user1)) {
                    parent.put(user1, user1);
                    size.put(user1, 1);
                }

                if(!parent.containsKey(user2)) {
                    parent.put(user2, user2);
                    size.put(user2, 1);
                }

                int newSize = union(parent, size, user1, user2);
                bw.write(newSize + "\n");
                bw.flush();
            }
        }
        bw.close();
    }
    // 파인드
    static String find(Map<String, String> parent, String user) {
        if(parent.get(user).equals(user)) return user;
        return find(parent, parent.get(user));
    }
    // 유니온
    static int union(Map<String, String> parent, Map<String, Integer> size, String a, String b) {
        String aRoot = find(parent, a);
        String bRoot = find(parent, b);
        if(aRoot.equals(bRoot)) return size.get(aRoot);

        if(aRoot.compareTo(bRoot) >= 0) {
            // 부모 업데이트
            parent.put(aRoot, bRoot);
            // 사이즈도 업데이트
            size.put(bRoot, size.get(aRoot) + size.get(bRoot));
            return size.get(bRoot);
        } else {
            parent.put(bRoot, aRoot);
            size.put(aRoot, size.get(aRoot) + size.get(bRoot));
            return size.get(aRoot);
        }
    }
}
