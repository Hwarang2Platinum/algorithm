import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1043_거짓말_김인엽 {
    static int N, M; // 사람 수, 파티 수
    static List<Integer>[] people; // 사람별 파티 갖고있는거
    static boolean[] isKnowTrue; // 사람별 진실을 알고있는 사람 여부
    static List<Integer>[] parties; // 파티별 사람 갖고있는거
    static boolean[] isPartyTrue; // 파티별 뻥쳐도 되는 곳
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        people = new ArrayList[N+1];
        isKnowTrue = new boolean[N+1];
        for(int i=1; i <= N; i++) {
            people[i] = new ArrayList<>();
        }
        M = Integer.parseInt(st.nextToken());
        parties = new ArrayList[M];
        for(int i=0; i < M; i++) {
            parties[i] = new ArrayList<>();
        }
        isPartyTrue = new boolean[M];

        st = new StringTokenizer(br.readLine());
        int trueCnt = Integer.parseInt(st.nextToken());
        for(int i=0; i<trueCnt; i++) {
            int trueIdx = Integer.parseInt(st.nextToken());
            isKnowTrue[trueIdx] = true;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j=0; j<num; j++) {
                Integer participant = Integer.parseInt(st.nextToken());
                people[participant].add(i); // 사람에 파티 넣기
                parties[i].add(participant); // 파티에 사람 넣기
            }
        }

        // truePeople 이 0 이면 무조건 M이 정답
        if(trueCnt == 0) {
            System.out.println(M);
            return;
        }
        // truePeople 이 있는 곳은 무조건 진실을 말해야함 -> 걔가 들어있는 그룹은 다 진실을 말해야함
        for(int i=1; i<=N; i++) {
            if(isKnowTrue[i]) { // 진실을 알고있는 사람이 있으면, 걔네 파티를 싹다 거짓말 불가능하게
                for(int party : people[i]) {
                    checkKnowTrue(party);
                }
            }
        }
        int answer = M;
        for(int i =0; i<isPartyTrue.length; i++) {
            if(isPartyTrue[i]) answer--;
        }
        System.out.println(answer);
    }

    public static void checkKnowTrue(int party) {
        isPartyTrue[party] = true;
        for(int person : parties[party]) {
            if(isKnowTrue[person]) continue;
            checkPartyTrue(person);
        }
    }

    public static void checkPartyTrue(int person) {
        isKnowTrue[person] = true;
        for(int party : people[person]) {
            if(isPartyTrue[party]) continue;
            checkKnowTrue(party);
        }
    }
}
