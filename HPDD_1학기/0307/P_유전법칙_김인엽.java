package study;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P_유전법칙_김인엽 {
    final static int RR = -1;
    final static int Rr = -2;
    final static int rr = -4;

    final static Map<Integer, String> num2String = new HashMap<>();
    static int[] parents; // 부모 모아놓기
    static int N; // 찾고싶은 세대(n)

    /**
     * 두번째 세대까지 찾는 메서드
     * @param query : int[]
     * @return 두번쨰 세대 반환 -> 1, 4면 up2Down안해도되니까!
     */
    static int down2Up(int[] query) {
        int n = query[0];
        int p = query[1];
        parents[n] = p%4==0?4:p%4; // 해당 트리에서 몇번째인지로 변경
        if(n <= 2) {
//            System.out.println("parents : " + Arrays.toString(parents));
            return p;
        }
        return down2Up(new int[]{n-1, p%4==0?p/4:p/4+1});
    }
    static void up2Down(int depth) {
        if(depth == N+1) {
            if(!(parents[N] == RR || parents[N] == rr)) parents[N] = Rr;
            return;
        }
        switch(parents[depth-1]) {
            case RR:
                parents[N] = RR;
                up2Down(N+1);
                break;
            case Rr:
                switch(parents[depth]) {
                    case 1:
                        parents[N] = RR;
                        up2Down(N+1);
                        break;
                    case 2:
                    case 3:
                        parents[depth] = Rr;
                        up2Down(depth+1);
                        break;
                    case 4:
                        parents[N] = rr;
                        up2Down(N+1);
                        break;
                }
//                parents[depth] = Rr;
//                up2Down(depth+1);
                break;
            case rr:
                parents[N] = rr;
                up2Down(N+1);
                break;
        }
    }
    static public String[] solution(int[][] queries) {
        num2String.put(1, "RR");
        num2String.put(2, "Rr");
        num2String.put(3, "Rr");
        num2String.put(4, "rr");
        String[] answer = new String[queries.length];
        for(int i=0; i<queries.length; i++) {
            N = queries[i][0];
            // 부모 모아놓기(인덱스 = 세대)
            parents = new int[queries[i][0]+1];
            // 첫 세대면 무조건 Rr
            if(queries[i][0] == 1) {
                answer[i] = "Rr";
                continue;
            } else if(queries[i][0] == 2) {
                answer[i] = num2String.get(queries[i][1]);
                continue;
            }
            // 두번째 세대값 찾기
            switch(down2Up(queries[i])) {
                // 1이면 무조건 RR
                case 1:
                    answer[i] = "RR";
                    break;
                // 2, 3이면 찾아보기
                case 2:
                case 3:
                    parents[2] = Rr;
                    up2Down(3);
                    answer[i] = num2String.get(parents[queries[i][0]] * (-1));
                    break;
                // 4면 무조건 rr
                case 4:
                    answer[i] = "rr";
                    break;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int[][][] queries = {
                {{3, 5}},
                {{3,8},{2,2}},
                {{3,1},{2,3},{3,9}},
                {{4,26}}
        };
        for(int[][] query : queries) {
            System.out.println("===================================");
            System.out.println(Arrays.toString(solution(query)));
        }
    }
}
