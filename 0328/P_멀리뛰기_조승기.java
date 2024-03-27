public class P_멀리뛰기_조승기 {
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
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

}
