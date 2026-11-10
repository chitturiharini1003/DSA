class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            long minCost = Long.MAX_VALUE;
            for (int j = Math.max(0, i - 3); j < i; j++) {
                long cost = dp[j] + costs[i - 1] + (long) (i - j) * (i - j);
                minCost = Math.min(minCost, cost);
            }
            dp[i] = minCost;
        }
        return (int) dp[n];
    }
}