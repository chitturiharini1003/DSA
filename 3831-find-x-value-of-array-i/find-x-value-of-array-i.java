class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at the
        // previous position whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            int value = num % k;

            // Start a new subarray containing only nums[i]
            next[value]++;

            // Extend every previous subarray
            for (int r = 0; r < k; r++) {
                int newRemainder = (r * value) % k;
                next[newRemainder] += dp[r];
            }

            // Add all subarrays ending at this position
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}