package March;

public class P_조이스틱_송인범 {

    public static int solution(String name) {
        int answer = 0;
        int cnt = name.length();
        int[] arr = new int[name.length()];
        int now = 0; // 인덱스

        for (int idx = 0; idx < name.length(); idx++) {
            int num = (int) name.charAt(idx) - 65; // A가 65이니까
            arr[idx] = num;
        }

        while (cnt > 0) {
            // 현재 위치의 알파벳 조작
            answer += Math.min(arr[now], 26 - arr[now]);
            arr[now] = 0;
            cnt--;
          
            int sum = 0;
            for(int i=0; i<arr.length; i++) {sum+=arr[i];}
            if(sum==0)break;
            // 다음 이동할 위치 찾기
            int left = 1, right = 1;
            while (arr[(now + right) % arr.length] == 0) {
                right++;
            }
            while (arr[(now - left + arr.length) % arr.length] == 0) {
                left++;
            }
            
            if (right <= left) {
                answer += right;
                now = (now + right) % arr.length;
            } else {
                answer += left;
                now = (now - left + arr.length) % arr.length;
            }
            
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("BBBBAAAABA"));
    }
}
