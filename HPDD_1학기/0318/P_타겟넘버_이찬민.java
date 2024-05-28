import java.util.*;
class P_타겟넘버_이찬민 {

    static int sum,result,len,staticTarget,temp;

    public int solution(int[] numbers, int target) {
        result =0;
        temp=0;
        len = numbers.length;
        staticTarget = target;
        Go(0,0,numbers);
        System.out.println(temp);
        return result;
    }

    public static void Go(int depth,int sum ,int[] numbers){
        if(depth == len){
            temp++;
            if(sum == staticTarget){
                result++;
                System.out.println(sum);
                return;
            }
            else{
                return;
            }
        }

        Go(depth+1,sum+numbers[depth],numbers);
        Go(depth+1,sum-numbers[depth],numbers);



    }
}