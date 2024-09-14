import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] belts = new int[N];
        Map<Integer,Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            belts[i] = Integer.parseInt(st.nextToken());
            if(!map.containsKey(belts[i])) map.put(belts[i],0);
            if(i >= N-k) {
                map.put(belts[i], map.get(belts[i])+1);
                if(map.get(belts[i])==1) cnt+=1;
            }
        }
        if(!map.containsKey(c)) map.put(c,0);
        if(map.get(c)==0) {
            cnt+=1;
        }
        int ans = cnt;
        for (int i = 0; i < N-1; i++) {

            // k번째 전 초밥의 카운트 빼주기 (c가 0개가 되면 쿠폰을 다시 적용시켜준다)
            if(i>=k){
                map.put(belts[i-k],map.get(belts[i-k])-1);

                if(belts[i-k]==c) cnt += map.get(belts[i-k])==0? 1:0;
                cnt -= map.get(belts[i-k])==0? 1:0;
            }else{
                map.put(belts[N-k+i],map.get(belts[N-k+i])-1);

                if(belts[N-k+i]==c) cnt += map.get(belts[N-k+i])==0? 1:0;
                cnt -= map.get(belts[N-k+i])==0? 1:0;
            }

            // 현재 초밥이 새로운 초밥이면 카운트 더해주기 (쿠폰(c)이면 쿠폰을 빼준다)
            map.put(belts[i],map.get(belts[i])+1);
            if(belts[i]==c) cnt -= map.get(belts[i])==1? 1:0;
            cnt += map.get(belts[i])==1? 1:0;
            ans = Math.max(cnt, ans);
        }
        System.out.println(ans);
    }
}
