class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        int[] bitCount = new int[31];
        
        // Count the frequency of each bit set across all numbers
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }
        
        long answer = 0;
        
        // Greedily construct the k largest possible numbers
        for (int i = 0; i < k; i++) {
            long current = 0;
            for (int bit = 0; bit < 31; bit++) {
                if (bitCount[bit] > 0) {
                    current |= (1L << bit);
                    bitCount[bit]--;
                }
            }
            answer = (answer + current * current) % MOD;
        }
        
        return (int) answer;
    }
}