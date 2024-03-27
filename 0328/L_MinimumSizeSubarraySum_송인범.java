package March;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class L_MinimumSizeSubarraySum_송인범 {
	public static void main(String[] args) throws IOException{

		
		
	}

}


class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int total = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int now = 0;

        while (right < nums.length) {
            now += nums[right];
            
            while (now >= target) {
                total = Math.min(total, right - left + 1);
                now -= nums[left];
                left++;
            }

            right++;
        }

        if(total == Integer.MAX_VALUE) total = 0;

        return total;
    }
}