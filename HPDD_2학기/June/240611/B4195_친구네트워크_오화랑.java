import java.io.*;
import java.util.*;

// Hwarang Think
// 어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램
// 친구 네트워크란 친구 관계만으로 이동할 수 있는 사이 ( -> 친구의 친구의 친구 느낌 )
// 첫째 줄 -> Test Case의 개수
// F : 친구 관계의 수 ( 1 ~ 100_000 )
// 친구 관계는 두 사용자의 아이디로 이루어져 있음 -> 알파벳 대문자 / 소문자로 이루어진 20 이하의 문자열
// Fred Barney (2) -> Barney Betty (3) -> Betty Willma => (4)
// Fred Barney (2) -> Betty Wilma (2) -> Barney Betty (4)
// Union Find가 적합해 보인다. ( 어떻게? -> HashMap으로 이름 -> Number를 저장하기! )

public class B4195_친구네트워크_오화랑 {
    static class User {
        int size, parentId;
        String name;

        public User(String name, int parentId, int size) {
            this.name = name;
            this.parentId = parentId;
            this.size = size;
        }
    }

    static class Solution {
        int T, F, userCount;
        HashMap<String, Integer> userList;
        ArrayList<User> userInfo;
        ArrayList<Integer> parents;

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = null;
            this.T = Integer.parseInt(input.readLine());

            String userA, userB;
            int id_A, id_B;
            for (int t = 0; t < this.T; t++) {

                this.F = Integer.parseInt(input.readLine());
                this.userList = new HashMap<>();
                this.userInfo = new ArrayList<>();
                this.userCount = 0;

                for (int f = 0; f < this.F; f++) {
                    st = new StringTokenizer(input.readLine());
                    userA = st.nextToken();
                    userB = st.nextToken();

                    if (!userList.keySet().contains(userA)) {
                        userList.put(userA, userCount);
                        userInfo.add(new User(userA, userCount, 1));
                        userCount++;
                    }
                    if (!userList.keySet().contains(userB)) {
                        userList.put(userB, userCount);
                        userInfo.add(new User(userB, userCount, 1));
                        userCount++;
                    }
                    /*
                     * if(!userList.keySet().contains(userA)) {
                     * userList.put(userA, userCount++);
                     * userInfo.add(new User(userA, userCount, 1));
                     * }
                     * => 이렇게 하면 오류가 남 -> 이유가 무엇일까?
                     */

                    id_A = userList.get(userA);
                    id_B = userList.get(userB);

                    union(id_A, id_B);
                    sb.append(userInfo.get(id_A).size).append("\n");
                }
            }
            System.out.print(sb);
        }

        void union(int id_A, int id_B) {
            int id_RootA = find(id_A);
            int id_RootB = find(id_B);
            int totalSize;

            if (id_RootA == id_RootB) {
                totalSize = userInfo.get(id_RootA).size;
                userInfo.get(id_A).size = userInfo.get(id_B).size = totalSize;
            } else {
                totalSize = userInfo.get(id_RootA).size + userInfo.get(id_RootB).size;
                userInfo.get(id_RootB).parentId = id_RootA;
                userInfo.get(id_A).size = userInfo.get(id_B).size = totalSize;
                userInfo.get(id_RootA).size = userInfo.get(id_RootB).size = totalSize;
            }
        }

        int find(int id) {
            if (id == userInfo.get(id).parentId)
                return id;
            return userInfo.get(id).parentId = find(userInfo.get(id).parentId);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution Solution = new Solution();
        Solution.run();
    }
}
