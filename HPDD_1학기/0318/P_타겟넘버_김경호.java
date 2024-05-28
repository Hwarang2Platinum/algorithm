class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        subset(0,0,numbers,target);
        return answer;
    }
    public static void subset(int depth, int accum, int[] numbers, int target){
        if(depth == numbers.length){
            if(accum==target) answer++;
            return;
        }
        subset(depth+1, accum + numbers[depth],numbers,target);
        subset(depth+1, accum - numbers[depth],numbers,target);
    }
}