class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int total = 0;
        int n=nums.length;
        int[] dp = new int[n+1];

        for(int i=0;i<n;i++){
            dp[i+1] = dp[i] + nums[i];
        }
        System.out.println(Arrays.toString(dp));

        int ans = 100001;
        for(int i=0;i<n;i++){
            int l,r,mid;
            l=i+1; r=n;
            // target보다 큰값중에 가장 작은값
            while(l<r){
                mid = (l+r)/2;
                // System.out.println(l+" "+r+" "+ mid);
                if(dp[mid] - dp[i] >= target){
                    r = mid;
                }else{
                    l = mid+1;
                }
            }
            System.out.println(l+" "+i);
            if(dp[l]-dp[i]>=target)
                ans = Math.min(ans,l-i);
        }

        return ans==100001?0:ans;
    }
}