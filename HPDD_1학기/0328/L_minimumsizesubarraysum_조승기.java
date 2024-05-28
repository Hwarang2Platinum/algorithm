class Solution {
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = 0;
        int N = nums.length;
        int sum = nums[0];
        int ans = 12345678;

        while (l < N && r < N) {
            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
                if (ans == 1) {
                    break;
                }
                sum -= nums[l];
                l++;
            } else {
                r++;
                if (r == N) {
                    continue;
                }
                sum += nums[r];
            }
        }
        return ans == 12345678 ? 0 : ans;
    }
}