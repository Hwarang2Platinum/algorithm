import java.io.*;
import java.util.*;
public class P의상_이찬민 {

    static HashMap<String, Integer> have;
    static HashMap<String, String> wear;
    static int answer; //최소 1개는 골라야함
    public int solution(String[][] clothes) { //이름, 종류순
        answer = 1;
        have = new HashMap<>();
        wear = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            if(have.get(clothes[i][1])==null){
                have.put(clothes[i][1],1);//종류, 갯수순
                System.out.println(have.get(clothes[i][1])+"처음");
                continue;
            }
            if(have.get(clothes[i][1])!=null){ //이미 있다면
                int temp = have.get(clothes[i][1]);
                have.put(clothes[i][1],temp+1); //1개 추가
                System.out.println(have.get(clothes[i][1])+"추가");
            }
        }

        subSet();
        answer -= 1;
        return answer;
    }


    public void subSet(){
        for (String key : have.keySet()) {
            System.out.println(have.get(key));
            answer *= have.get(key)+1; //안입는거 + 종류별 옷이 몇벌?

        }
    }
}
