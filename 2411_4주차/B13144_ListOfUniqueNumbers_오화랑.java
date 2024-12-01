
/**
 * * https://www.acmicpc.net/problem/13144
 * IMP : 길이가 N인 수열이 주어질 때, 수열에서 연속한 1개 이상의 수를 선택
 * * => 같은 수가 여러 번 등장하지 않는 경우의 수
 * Type : N ( 1 ~ 100,000 )
 * 
 * IMP : Method : Two Pointer => 10%에서 "틀렸습니다" 
 * * => 하지만, 이 방법이 틀린것 같지는 않음.. 반례가 존재할 수 있나? 내가 놓친 것이 있는가? ( Index 설정 문제 ? => X )
 * ! totalCount의 자료형이 long이 아닌 int형인 상태여서, 틀리게 되었음 => 이런 부분에서 정말 틀리면 안된다...
 * Type : Two Pointer가 핵심 전략이긴 하지만, 조금 동작 시간에서 차이가 나기도 한다.. Why? ( 최적화의 여지가 남아 있나? )
 */

import java.io.*;
import java.util.*;

public class B13144_ListOfUniqueNumbers_오화랑 {

    static class Solution {
        int N;
        int[] list;
        HashSet<Integer> current = new HashSet<>();

        void run() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(input.readLine());
            list = new int[N];

            StringTokenizer st = new StringTokenizer(input.readLine());
            for (int i = 0; i < N; i++)
                list[i] = Integer.parseInt(st.nextToken());

            long totalCount = 0;
            int left, right;
            left = 0;
            right = 0;

            while (right < N) {
                int target = list[right];

                if (current.contains(target)) {
                    totalCount += current.size();
                    while (left < right) {
                        if (list[left] == target) {
                            current.remove(list[left]);
                            left++;
                            break;
                        } else {
                            current.remove(list[left]);
                            totalCount += current.size();
                        }
                        left++;
                    }
                }
                current.add(target);
                right++;
            }
            int leftSize = current.size();
            while (leftSize > 0) {
                totalCount += leftSize;
                leftSize--;
            }
            System.out.println(totalCount);
        }
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }
}
