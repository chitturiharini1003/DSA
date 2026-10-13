import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            long value = nums[i];
            squares[i] = value * value;
        }
        Arrays.sort(squares);
        long score = 0;
        int negativeCount = n / 2;
        for (int i = 0; i < n; i++) {
            if (i < negativeCount) {
                score -= squares[i];
            } else {
                score += squares[i];
            }
        }
        return score;
    }
}