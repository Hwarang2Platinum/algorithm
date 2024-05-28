import java.util.*;
class Solution { //답지봄 그냥 수학머리가없슴
    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];

        String[] arr = new String[]{"RR","Rr","Rr","rr"};

        int idx=0;
        for(int[] query:queries){

            int level=query[0];
            int count = query[1]-1;

            Stack<Integer> stack =new Stack<>();

            if(level==1){
                answer[idx]="Rr";
                idx++;
                continue;
            }
            if(level==2){
                answer[idx]=arr[count];
                idx++;
                continue;
            }

            while(level!=1){
                int elses=count%4;
                count/=4;

                level-=1;
                stack.push(elses);

            }

            while(!stack.isEmpty()){

                String current= arr[stack.pop()];

                if(current.equals("RR")){
                    answer[idx]=current;
                    break;
                }
                else if(current.equals("rr")){
                    answer[idx]=current;
                    break;
                }


                answer[idx]=current;
            }



            idx++;

        }

        return answer;
    }
}